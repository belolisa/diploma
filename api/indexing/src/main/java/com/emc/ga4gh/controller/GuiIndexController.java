package com.emc.ga4gh.controller;

import com.emc.ga4gh.Indexer;
import com.emc.ga4gh.spring.aop.logger.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GuiIndexController {

    @Autowired
    Indexer indexer;

    @Log
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public void index(@RequestParam(value = "type") String fileType,
                      @RequestParam(value = "path") String fullPath) {
        indexer.index(fileType, fullPath);
    }

    @Log
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadAndIndex(@RequestParam(value = "type") String fileType,
                               @RequestParam(value = "path") String fullPath) {
        indexer.uploadAndIndex(fileType, fullPath);
    }

}
