package com.emc.ga4gh.parser.upload;

import com.emc.ga4gh.DAO.ReadDAO;
import com.emc.ga4gh.DAO.ReferenceDAO;
import com.emc.ga4gh.DAO.VariantDAO;
import com.emc.ga4gh.DTO.Read;
import htsjdk.samtools.SAMRecord;
import htsjdk.samtools.SamReader;
import htsjdk.samtools.SamReaderFactory;
import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.vcf.VCFFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by liza on 06.05.15.
 */

@Component
public class ParserServiceImpl implements ParserService {

    @Autowired
    ReadDAO readDAO;

    @Autowired
    VariantDAO variantDAO;

    @Autowired
    ReferenceDAO referenceDAO;

    @Override
    public void parseBAM(File SAMFile) {
            SamReader samReader = SamReaderFactory.make().open(SAMFile);
            int num = 0;
            for (SAMRecord samRecord : samReader) {
                readDAO.create(getRead(num, samRecord));
                num++;
            }
    }

    private Read getRead(int num, SAMRecord samRecord) {
        Read read = new Read();
        read.setNumberInFile(num);
        read.setReadGroupId(samRecord.getReadGroup().getId());
        read.setAlignmentStart(samRecord.getAlignmentStart());
        read.setAlignmentEnd(samRecord.getAlignmentEnd());
        read.setReferenceId(samRecord.getReferenceIndex());
        read.setReferenceName(samRecord.getReferenceName());
        return read;
    }

    @Override
    public void parseVCF(File VCFFile) {
        VCFFileReader variantContexts = new VCFFileReader(VCFFile);
        int num = 0;
        for (VariantContext variantContext : variantContexts) {
            num++;
        }
    }

    @Override
    public void parseSATA(File SATAFile) {
    }
}
