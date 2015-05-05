package com.emc.ga4gh.dataservice;

import com.emc.ga4gh.DAO.ReadDAO;
import com.emc.ga4gh.DTO.Read;
import com.emc.ga4gh.fileStorage.FileStorage;
import com.emc.ga4gh.model.*;
import com.emc.ga4gh.parser.download.FileReaderHelper;
import com.emc.ga4gh.parser.download.FileReaderHelperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

/**
 * Created by Elizaveta Belokopytova.
 */

@Component
public class DataServiceImpl implements DataService {

    @Autowired
    ReadDAO readDAO;

    @Autowired
    FileStorage fs;

    @Override
    public GAReference getReference(String id) {
        return null;
    }

    @Override
    public GAListReferenceBasesResponse getReferenceBases(String id, GAListReferenceBasesRequest request) {
        return null;
    }

    @Override
    public GAReferenceSet getReferenceSet(String id) {
        return null;
    }

    @Override
    public GASearchCallSetsResponse searchCallSets(GASearchCallSetsRequest request) {
        return null;
    }

    @Override
    public GASearchReadGroupSetsResponse searchReadGroupSets(GASearchReadGroupSetsRequest request) {
        return null;
    }

    @Override
    public GASearchReadsResponse searchReads(GASearchReadsRequest request) {
        ArrayList<GAReadAlignment> responseAlignments = new ArrayList<>();

        List<String> readGroupIds = Arrays.asList(request.getReadGroupIds());
        String referenceId = request.getReferenceId();
        String referenceName = request.getReferenceName();
        Long start = request.getStart();
        if (start == null) {
            start = (long) 0;
        }
        Long end = request.getEnd();
        List<Read> metaReads = readDAO.findIncOrdered(referenceId, referenceName, start, end, readGroupIds);

        Map<String, List<Read>> pathReadsMap = new HashMap<>();
        for (Read metaRead : metaReads) {
            List<Read> reads = pathReadsMap.get(metaRead.getPath());
            if (reads == null) {
                reads = new ArrayList<>();
                pathReadsMap.put(metaRead.getPath(), reads);
            }
            reads.add(metaRead);
        }

        for (String path : pathReadsMap.keySet()) {
            File file = fs.getFile(path);
            FileReaderHelper fileReaderHelper = new FileReaderHelperImpl(file);
            responseAlignments.addAll(fileReaderHelper.getReadAlignments(pathReadsMap.get(path)));
        }

        return new GASearchReadsResponse((GAReadAlignment[]) responseAlignments.toArray(), "");
    }

    /*@Override
    public GASearchReadsResponse searchReadsEfficientlyBySamReaderMethods(GASearchReadsRequest request) {return null;}
*/
    @Override
    public GASearchReferenceSetsResponse searchReferenceSets(GASearchReferenceSetsRequest request) {
        return null;
    }

    @Override
    public GASearchReferencesResponse searchReferences(GASearchReferencesRequest request) {
        return null;
    }

    @Override
    public GASearchVariantSetsResponse searchVariantSets(GASearchVariantSetsRequest request) {
        return null;
    }

    @Override
    public GASearchVariantsResponse searchVariants(GASearchVariantsRequest request) {
        return null;
    }
}
