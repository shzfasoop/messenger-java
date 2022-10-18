package com.kgaft.michatmessengerserver.Service;

import com.kgaft.michatmessengerserver.Database.Entity.FileContent;
import com.kgaft.michatmessengerserver.Database.Entity.FileEncrypted;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Database.JDBC.FileDAO;
import com.kgaft.michatmessengerserver.Database.Repository.ChatRepository;
import com.kgaft.michatmessengerserver.Database.Repository.FileEncryptedRepository;
import com.kgaft.michatmessengerserver.Database.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Random;

@Service
public class FileService {
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private FileEncryptedRepository fileEncryptedRepository;

    public FileEncrypted saveEncryptedFile(MultipartFile multipartFile, User owner, String fileName, long chatId) {
        try {
            File file = saveTempMultiPartFile(multipartFile);
            try {
                FileContent fileContent = new FileContent();
                fileContent.setContent(new FileInputStream(file));
                long fileContentId = fileDAO.saveFile(fileContent);
                FileEncrypted fileEncrypted = new FileEncrypted();
                fileEncrypted.setFileName(fileName);
                fileEncrypted.setFileContentId(fileContentId);
                fileEncrypted.setOwner(owner);
                fileEncrypted.setUploadedDate(System.currentTimeMillis());
                fileEncrypted.setChatChatId(chatId);
                file.delete();
                fileEncrypted = fileEncryptedRepository.save(fileEncrypted);

                return fileEncrypted;
            } catch (Exception e) {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public com.kgaft.michatmessengerserver.Database.Entity.File saveFile(MultipartFile multipartFile, User owner, String fileName) {
        try {
            File file = saveTempMultiPartFile(multipartFile);
            try {
                FileContent fileContent = new FileContent();
                fileContent.setContent(new FileInputStream(file));
                long fileContentId = fileDAO.saveFile(fileContent);
                com.kgaft.michatmessengerserver.Database.Entity.File fileDb = new com.kgaft.michatmessengerserver.Database.Entity.File();
                fileDb.setFileName(fileName);
                fileDb.setOwner(owner);
                fileDb.setUploadedDate(System.currentTimeMillis());
                fileDb.setFileContentId(fileContentId);
                file.delete();
                return fileRepository.save(fileDb);
            } catch (Exception e) {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public com.kgaft.michatmessengerserver.Database.Entity.File getFile(long fileId){
        return fileRepository.findById(fileId).get();
    }
    public FileEncrypted getFileEncrypted(long fileEncryptedId){
        return fileEncryptedRepository.findById(fileEncryptedId).get();
    }
    public void writeFileContent(long fileContentId, OutputStream outputStream) throws SQLException, IOException {
        FileContent fileContent = fileDAO.findContentById(fileContentId);
        int read;
        byte[] buffer = new byte[4*1024];
        while((read=fileContent.getContent().read(buffer, 0, buffer.length))!=-1){
            outputStream.write(buffer, 0, read);
        }
        outputStream.flush();
        fileContent.getContent().close();
    }
    private File saveTempMultiPartFile(MultipartFile multipartFile) throws IOException {
        File file = new File(generateFileName());
        multipartFile.transferTo(file);
        return file;
    }

    private String generateFileName() {
        String tempDir = System.getProperty("java.io.tmpdir");
        Random random = new Random();
        File file = new File(tempDir + "/" + random.nextLong() + ".tmp");
        while (file.exists()) {
            file = new File(tempDir + "/" + random.nextLong() + ".tmp");
        }
        return file.getAbsolutePath();
    }
}
