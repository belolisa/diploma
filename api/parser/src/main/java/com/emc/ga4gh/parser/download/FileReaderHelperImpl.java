package com.emc.ga4gh.parser.download;

import com.emc.ga4gh.DTO.Read;
import com.emc.ga4gh.model.*;
import htsjdk.samtools.SAMRecord;
import htsjdk.samtools.SAMRecordIterator;
import htsjdk.samtools.SamReader;
import htsjdk.samtools.SamReaderFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liza on 05.05.15.
 */
public class FileReaderHelperImpl implements FileReaderHelper {

    private File file;

    public FileReaderHelperImpl(File file) {
        this.file = file;
    }

    @Override
    public ArrayList<GAReadAlignment> getReadAlignments(List<Read> metaReads) {
        ArrayList<GAReadAlignment> responseAlignments = new ArrayList<>();
        try (SamReader samReader = SamReaderFactory.make().open(file)) {
            SAMRecordIterator iterator = samReader.iterator();
            int i = -1;
            for (Read metaRead : metaReads) {
                SAMRecord samRecord = iterator.next();
                i++;
                while (i != metaRead.getNumberInFile()) {
                    if (!iterator.hasNext()) {
                        break;
                    }
                    samRecord = iterator.next();
                    i++;
                }
                responseAlignments.add(parseSamRecord(samRecord));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseAlignments;
    }

    private GAReadAlignment parseSamRecord(SAMRecord samRecord) {
        Integer numberReads = null;
        Integer readNumber = null;
        GAPosition nextMatePosition = null;
        Boolean properPlacement = null;
        if (samRecord.getReadPairedFlag()) {
            numberReads = 2;
            properPlacement = samRecord.getProperPairFlag();
            if (samRecord.getFirstOfPairFlag()) {
                readNumber = 0;
            }
            readNumber = 1;
            nextMatePosition = new GAPosition(
                    samRecord.getReferenceName(),
                    samRecord.getMateAlignmentStart(),
                    samRecord.getMateNegativeStrandFlag());
        }

        GALinearAlignment linAlignment = null;
        if (!samRecord.getReadUnmappedFlag()) {
            GAPosition position = new GAPosition(
                    samRecord.getReferenceName(),
                    samRecord.getReferencePositionAtReadPosition(0),
                    samRecord.getReadNegativeStrandFlag());
            GACigarUnit[] cigar = new GACigarUnit[samRecord.getCigar()
                    .getCigarElements().size()];
            for (int j = 0; j < cigar.length; j++) {
                // null | string referenceSequence??
                cigar[j] = new GACigarUnit(translate(samRecord
                        .getCigar().getCigarElement(j).getOperator()
                        .toString()), samRecord.getCigar()
                        .getCigarElement(j).getLength(), null);
            }
            linAlignment = new GALinearAlignment(position,
                    samRecord.getMappingQuality(), cigar);
        }
        Map<String, Object> info = new HashMap<String, Object>();
        for (SAMRecord.SAMTagAndValue pair : samRecord.getAttributes()) {
            info.put(pair.tag, pair.value);
        }

        GAReadAlignment alignment = new GAReadAlignment("", samRecord
                .getReadGroup().getId(), samRecord.getReadName(),
                properPlacement, samRecord.getDuplicateReadFlag(),
                numberReads, samRecord.getReadLength(), readNumber,
                samRecord.getReadFailsVendorQualityCheckFlag(),
                linAlignment, samRecord.getNotPrimaryAlignmentFlag(),
                samRecord.getSupplementaryAlignmentFlag(),
                samRecord.getReadString(),
                byteArrayToIntArray(samRecord.getBaseQualities()),
                nextMatePosition, info);
        return alignment;
    }

    private int[] byteArrayToIntArray(byte[] byteArray) {
        int[] intArray = new int[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            intArray[i] = byteArray[i];
        }
        return intArray;
    }

    private GACigarOperation translate(String SAMCigarOperationName) {
        switch (SAMCigarOperationName) {
            case "M":
                return GACigarOperation.ALIGNMENT_MATCH;
            case "I":
                return GACigarOperation.INSERT;
            case "D":
                return GACigarOperation.DELETE;
            case "N":
                return GACigarOperation.SKIP;
            case "S":
                return GACigarOperation.CLIP_SOFT;
            case "H":
                return GACigarOperation.CLIP_HARD;
            case "P":
                return GACigarOperation.PAD;
            case "=":
                return GACigarOperation.SEQUENCE_MATCH;
            case "X":
                return GACigarOperation.SEQUENCE_MISMATCH;
            default:
                return GACigarOperation.ALIGNMENT_MATCH;
        }
    }
}
