package com.emc.ga4gh.parser.upload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class ParserServiceImplTest {

    @Autowired
    ParserService parserService;

    @Test
    public void testParseBAM() throws Exception {
        parserService.parseBAM(new File("/home/liza/IdeaProjects/diploma/api/bam/example.bam"));
    }

    @Test
    public void testParseVCF() throws Exception {

    }

    @Test
    public void testParseSATA() throws Exception {

    }
}