package com.emc.ga4gh.parser.upload;

import java.io.File;

// File parsing methods, persisting the result

public interface ParserService {

    void parseSAM(File SAMFile);

    void parseBAM(File BAMFile);

    void parseVCF(File VCFFile);

}
