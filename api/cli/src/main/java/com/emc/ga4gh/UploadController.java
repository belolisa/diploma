package com.emc.ga4gh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UploadController {

    @Autowired
    UploadDataService dataService;

    public static void main(String[] args) {
        ApplicationContext context  = new FileSystemXmlApplicationContext("classpath*:cliContext.xml");
        UploadController bean = context.getBean(UploadController.class);

        String fileType = args[0];
        String fullPath = args[1];

        FileType type = null;
        try {
            type = FileType.getType(fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert type != null;
        switch (type) {
            case BAM: case SAM:
                bean.dataService.parseBAM(fullPath);
                break;
            case VCF:
                bean.dataService.parseVCF(fullPath);
                break;
            case SATA:
                bean.dataService.parseSATA(fullPath);
                break;
        }
    }
}
