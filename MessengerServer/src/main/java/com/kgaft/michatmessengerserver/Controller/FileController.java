package com.kgaft.michatmessengerserver.Controller;

import com.kgaft.michatmessengerserver.Database.Entity.File;
import com.kgaft.michatmessengerserver.Database.Entity.FileEncrypted;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Service.AuthorizationService;
import com.kgaft.michatmessengerserver.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@RestController
public class FileController {
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private FileService fileService;
    @PostMapping("/uploadFile")
    public File uploadFile(MultipartFile file, @RequestParam String token, @RequestParam String fileName){
        User user = authorizationService.getUserByToken(token);
        if(user!=null){
            return fileService.saveFile(file, user, fileName);
        }
        return null;
    }
    @GetMapping("/getFileInfo")
    public File getFileInfo(@RequestParam String token, @RequestParam long fileId){
        if(authorizationService.authorizeToken(token)){
            return fileService.getFile(fileId);
        }
        return null;
    }
    @GetMapping("/getFileEncryptedInfo")
    public FileEncrypted getFileEncryptedInfo(@RequestParam String token, @RequestParam long fileId){
        if(authorizationService.authorizeToken(token)){
            return fileService.getFileEncrypted(fileId);
        }
        return null;
    }
    @PostMapping("/uploadEncryptedFile")
    public FileEncrypted uploadEncryptedFile(MultipartFile file, @RequestParam String token, @RequestParam String fileName, @RequestParam long chatId){
        User user = authorizationService.getUserByToken(token);
        if(user!=null){
            return fileService.saveEncryptedFile(file, user, fileName, chatId);
        }
        return null;
    }
    @GetMapping("/getFileContent")
    public void getFileContent(@RequestParam String token, @RequestParam long fileId, HttpServletResponse response) throws IOException, SQLException {
        User user = authorizationService.getUserByToken(token);
        if(user!=null){
            File file = fileService.getFile(fileId);
            fileService.writeFileContent(file.getFileContentId(), response.getOutputStream());

        }
    }
    @GetMapping("/getFileEncryptedContent")
    public void getFileEncryptedContent(@RequestParam String token, @RequestParam long fileId, HttpServletResponse response) throws IOException, SQLException {
        User user = authorizationService.getUserByToken(token);
        if(user!=null){
            FileEncrypted file = fileService.getFileEncrypted(fileId);
            fileService.writeFileContent(file.getFileContentId(), response.getOutputStream());

        }
    }
}
