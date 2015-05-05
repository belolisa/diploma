package com.emc.ga4gh.cache;

import com.emc.ga4gh.dataservice.DataService;
import com.emc.ga4gh.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by liza on 06.05.15.
 */

@Component
public class CacheImpl implements Cache {



    @Qualifier("dataServiceImpl")
    @Autowired
    DataService dataService;

    @Override
    public GAReference getReference(String id) {
        return dataService.getReference(id);
    }

    @Override
    public GAListReferenceBasesResponse getReferenceBases(String id, GAListReferenceBasesRequest request) {
        return dataService.getReferenceBases(id, request);
    }

    @Override
    public GAReferenceSet getReferenceSet(String id) {
        return dataService.getReferenceSet(id);
    }

    @Override
    public GASearchCallSetsResponse searchCallSets(GASearchCallSetsRequest request) {
        return dataService.searchCallSets(request);
    }

    @Override
    public GASearchReadGroupSetsResponse searchReadGroupSets(GASearchReadGroupSetsRequest request) {
        return dataService.searchReadGroupSets(request);
    }

    @Override
    public GASearchReadsResponse searchReads(GASearchReadsRequest request) {
        return dataService.searchReads(request);
    }

    @Override
    public GASearchReferenceSetsResponse searchReferenceSets(GASearchReferenceSetsRequest request) {
        return dataService.searchReferenceSets(request);
    }

    @Override
    public GASearchReferencesResponse searchReferences(GASearchReferencesRequest request) {
        return dataService.searchReferences(request);
    }

    @Override
    public GASearchVariantSetsResponse searchVariantSets(GASearchVariantSetsRequest request) {
        return dataService.searchVariantSets(request);
    }

    @Override
    public GASearchVariantsResponse searchVariants(GASearchVariantsRequest request) {
        return dataService.searchVariants(request);
    }
}
