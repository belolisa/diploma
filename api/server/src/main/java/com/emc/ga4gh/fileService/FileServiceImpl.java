package com.emc.ga4gh.fileService;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by Elizaveta Belokopytova.
 */

@Component
public class FileServiceImpl implements FileService {

    @Override
    public File getFile(String path) {
        return new File(path);
    }

    @Override
    public String uploadFile(String path) {
        String newPath = getNewPath(path);
        // write file
        return newPath;
    }

    private String getNewPath(String path) {
        return "newpath";
    }
}
