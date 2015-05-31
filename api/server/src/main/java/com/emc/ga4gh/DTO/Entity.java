package com.emc.ga4gh.DTO;

/**
 * Created by Elizaveta Belokopytova.
 */

public interface Entity {

    FileEntity getFile();

    void setFile(FileEntity file);

    String getRid();

    void setRid(String id);
}
