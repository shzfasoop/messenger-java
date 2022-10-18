package com.kgaft.michatmessengerserver.Database.JDBC;

import com.kgaft.michatmessengerserver.Database.Entity.FileContent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Random;

@Component
@Scope(scopeName = "singleton")
public class FileDAO {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String login;
    @Value("${spring.datasource.password}")
    private String password;
    public static final String TABLE_NAME = "files_content";

    private Connection connection;
    @PostConstruct
    private void initConnection(){
        try {
            this.connection = DriverManager.getConnection(url, login, password);
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void createTable(){
        try {
            connection.createStatement().execute("CREATE TABLE "+TABLE_NAME+"(" +
                    "fileId BIGINT," +
                    "fileContent BYTEA," +
                    "PRIMARY KEY (fileId));");
        } catch (SQLException e) {

        }
    }
    public long saveFile(FileContent fileContent) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO "+TABLE_NAME+" (fileid, filecontent) VALUES(?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
        long fileId = generateFileId();
        pstmt.setLong(1, fileId);
        pstmt.setBinaryStream(2, fileContent.getContent());
        pstmt.execute();
        return fileId;
    }
    public FileContent findContentById(long contentId) throws SQLException {
        ResultSet results = connection.createStatement().executeQuery("SELECT * FROM "+TABLE_NAME+" WHERE fileId = "+contentId+";");
        if(results.next()){
            FileContent fileContent = new FileContent();
            fileContent.setFileId(results.getLong("fileId"));
            fileContent.setContent(results.getBinaryStream("fileContent"));
            return fileContent;
        }
        return null;
    }
    private long generateFileId() {
        Random random = new Random();
        long id = random.nextLong();
        while(isFileWithIdExists(id)) id = random.nextLong();
        return id;
    }
    private boolean isFileWithIdExists(long fileId) {
        try{
            ResultSet results = connection.createStatement().executeQuery("SELECT fileId FROM "+TABLE_NAME+" WHERE fileId="+fileId+";");
            return results.next();
        }catch (Exception e){
            return true;
        }

    }

}
