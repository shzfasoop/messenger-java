package com.kgaft.michatmessengerserver.Service;

import com.kgaft.michatmessengerserver.Database.Repository.UsersRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Service
public class JWTTokenProvider {
    @Value("${jwt.token.secret}")
    private String jwtSecret;
    @Value("${jwt.token.expired}")
    private long validityTime;
    @Autowired
    private UsersRepository users;

    @PostConstruct
    protected void init(){
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }
    public String createToken(String userLogin){
        Claims tokenDetails = Jwts.claims().setSubject(userLogin);
        Date creationDate = new Date(System.currentTimeMillis());
        Date validity = new Date(creationDate.getTime()+validityTime);
        return Jwts.builder().setClaims(tokenDetails).setIssuedAt(creationDate).setExpiration(validity).signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
    }

    public String getUserLogin(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean authorizeToken(String token){
        try{
            Jws<Claims> tokenInfo = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            Date expiration = tokenInfo.getBody().getExpiration();
            Date now = new Date(System.currentTimeMillis());
            return expiration.after(now) && users.existsByLogin(getUserLogin(token));
        }catch (Exception e){
            return false;
        }
    }
    private String getTokenFromQuery(String query){
        return query.split("Authorization=")[1].split("&")[0];
    }
}
