package com.emc.ga4gh.fileService;

import java.io.File;

/**
 * Created by Elizaveta Belokopytova.
 */
public interface FileService {

    File getFile(String path);

    String uploadFile(String path);
}
