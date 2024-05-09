package at.davl.movie.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    // upload file
    String uploadFile(String path, MultipartFile file) throws IOException;

    // download file
    InputStream getResourceFile(String path, String fileName) throws FileNotFoundException;

}
