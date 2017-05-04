package ar.edu.unlp.dsa.utils.storage;

import ar.edu.unlp.dsa.ApplicationConfigurationConstants;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Date;

/**
 * Created by axelcb on 15/3/17.
 */
@Service
public class StorageServiceFileSystemImpl implements StorageService {

    @Autowired
    private ApplicationConfigurationConstants configurations;

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
        Resource file = new FileSystemResource(this.getConfigurations().getStorageRootPath()+fileName);
        return file;
    }

    @Override
    public String store(MultipartFile multipartfile) throws IOException {
        String hashedFileName = this.hashStringMD5(multipartfile.getOriginalFilename()+new Date().getTime());
        multipartfile.transferTo(new File(this.getConfigurations().getStorageRootPath()+hashedFileName));
        return hashedFileName;
    }

    @Override
    public Boolean remove(String fileName) {
        File file = new File(this.getConfigurations().getStorageRootPath()+fileName);
        return file.delete();
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

    public ApplicationConfigurationConstants getConfigurations() {
        return configurations;
    }

    public void setConfigurations(ApplicationConfigurationConstants configurations) {
        this.configurations = configurations;
    }
}
