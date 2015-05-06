package com.emc.ga4gh;

/**
 * Created by liza on 06.05.15.
 */
public interface UploadDataService {

    void parseBAM(String path);

    void parseVCF(String path);

    void parseSATA(String path);

}
