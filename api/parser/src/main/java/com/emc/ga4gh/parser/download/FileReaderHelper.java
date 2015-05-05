package com.emc.ga4gh.parser.download;

import com.emc.ga4gh.DTO.Read;
import com.emc.ga4gh.model.GAReadAlignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liza on 06.05.15.
 */
public interface FileReaderHelper {

    ArrayList<GAReadAlignment> getReadAlignments(List<Read> metaReads);

    // And so on
}
