package com.emc.ga4gh;

import com.emc.ga4gh.fileStorage.FileStorage;
import com.emc.ga4gh.parser.upload.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liza on 06.05.15.
 */

@Component
public class UploadDataServiceImpl implements UploadDataService {

    @Autowired
    FileStorage fs;

    @Autowired
    ParserService parser;

    @Override
    public void parseBAM(String path) {
        parser.parseBAM(fs.getFile(path));
    }

    @Override
    public void parseVCF(String path) {
        parser.parseVCF(fs.getFile(path));

    }

    @Override
    public void parseSATA(String path) {
        parser.parseSATA(fs.getFile(path));
    }
}
