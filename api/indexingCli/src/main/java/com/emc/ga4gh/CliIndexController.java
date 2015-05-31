package com.emc.ga4gh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CliIndexController {

    @Autowired
    public Indexer indexer;

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("classpath*:indexing-cli-controller-context.xml");
        CliIndexController bean = context.getBean(CliIndexController.class);

        String fileType = args[0];
        String fullPath = args[1];
        Boolean doUpload = Boolean.valueOf(args[2]);

        if (doUpload) {
            bean.indexer.uploadAndIndex(fileType, fullPath);
        } else {
            bean.indexer.index(fileType, fullPath);
        }
    }
}
