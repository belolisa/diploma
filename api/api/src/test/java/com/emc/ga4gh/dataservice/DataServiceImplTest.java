package com.emc.ga4gh.dataservice;

import com.emc.ga4gh.model.GASearchReadsRequest;
import com.emc.ga4gh.model.GASearchReadsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class DataServiceImplTest {

    @Qualifier("dataServiceImpl")
    @Autowired
    DataService dataService;

    @Test
    public void testSearchReads() throws Exception {
        GASearchReadsRequest request = new GASearchReadsRequest();
        request.setReadGroupIds(new String[]{"some_rg"});
        request.setPageSize(10);
        request.setPageToken("2");
        GASearchReadsResponse gaSearchReadsResponse = dataService.searchReads(request);
        System.out.println("gaSearchReadsResponse = " + gaSearchReadsResponse);
    }
}