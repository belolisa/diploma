package com.emc.ga4gh;

import com.emc.ga4gh.fileService.FileService;
import com.emc.ga4gh.parser.upload.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Indexer {

    @Autowired
    ParserService parserService;

    @Autowired
    FileService fs;

    public void index(String fileType, String fullPath) {

        FileType type = null;
        try {
            type = FileType.getType(fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = fs.getFile(fullPath);

        assert type != null;
        switch (type) {
            case BAM:
            case SAM:
                parserService.parseBAM(file);
                break;
            case VCF:
                parserService.parseVCF(file);
                break;
            case SATA:
                parserService.parseSATA(file);
                break;
        }
    }

    public String upload(String fullPath) {
        return fs.uploadFile(fullPath);
    }

    public void uploadAndIndex(String fileType, String fullPath) {
        upload(fullPath);
        index(fileType, fullPath);
    }
}
