package ar.edu.unlp.dsa.utils.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by acollard on 3/15/2017.
 */
public interface StorageService {
    public Resource loadAsResource(String fileName);
    public String store(MultipartFile file) throws IOException;
}
