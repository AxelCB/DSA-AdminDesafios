package ar.edu.unlp.dsa.utils.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by axelcb on 15/3/17.
 */
@Service
public class StorageServiceFileSystemImpl implements StorageService {

    //@Value("ar.edu.unlp.dsa.storage.rootPath")
    private String roothPath = "/Volumes/Home macOS/Users/axelcb/Documents/Facultad/DSA/uploads/";

    private MessageDigest md5Hasher;

    public StorageServiceFileSystemImpl() {
        try {
            this.md5Hasher = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resource loadAsResource(String fileName) {
        String hashedFileName = this.hashStringMD5(fileName);
        Resource file = new FileSystemResource(this.getRoothPath()+hashedFileName);
        return file;
    }

    @Override
    public String store(MultipartFile multipartfile) throws IOException {
        String hashedFileName = this.hashStringMD5(multipartfile.getOriginalFilename());
        multipartfile.transferTo(new File(this.getRoothPath()+hashedFileName));
        return this.getRoothPath()+hashedFileName;
    }

    private String hashStringMD5(String stringToHash){
        md5Hasher.reset();
        md5Hasher.update(stringToHash.getBytes());
        byte[] digest = md5Hasher.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
// Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
        }
        return hashtext;
    }

    public String getRoothPath() {
        return roothPath;
    }

    public void setRoothPath(String roothPath) {
        this.roothPath = roothPath;
    }
}
