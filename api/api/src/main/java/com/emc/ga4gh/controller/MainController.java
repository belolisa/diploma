package com.emc.ga4gh.controller;

import com.emc.ga4gh.cache.Cache;
import com.emc.ga4gh.model.*;
import com.emc.ga4gh.spring.aop.logger.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

	@Autowired
	private Cache cache;

	@Log
	@RequestMapping(value = "/references/{id}", method = RequestMethod.GET)
	public @ResponseBody GAReference getReference(@PathVariable("id") String id) {
		return cache.getReference(id);
	}

	@Log
	@RequestMapping(value = "/references/{id}/bases", method = RequestMethod.GET)
	public @ResponseBody GAListReferenceBasesResponse getReferenceBases(
			@PathVariable("id") String id,
			@RequestParam(value = "start", defaultValue="0") long start,
			@RequestParam(value = "end", defaultValue="null") Long end,
			@RequestParam(value = "pageToken", defaultValue="null") String pageToken) {
		return cache.getReferenceBases(id, new GAListReferenceBasesRequest(start, end, pageToken));
	}

	@Log
	@RequestMapping(value = "/referencesets/{id}", method = RequestMethod.GET)
	public @ResponseBody
	GAReferenceSet getReferenceSet(@PathVariable("id") String id) {
		return cache.getReferenceSet(id);
	}
	
	@Log @RequestMapping(value = "/callsets/search", method = RequestMethod.POST)
	public 	@ResponseBody GASearchCallSetsResponse searchCallSets(@RequestBody GASearchCallSetsRequest request) {
		return cache.searchCallSets(request);
	}  
	
	@Log @RequestMapping(value = "/readgroupsets/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReadGroupSetsResponse searchReadGroupSets(@RequestBody GASearchReadGroupSetsRequest request) {
		return cache.searchReadGroupSets(request);
	}	
	
	@Log @RequestMapping(value = "/reads/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReadsResponse searchReads(@RequestBody GASearchReadsRequest request) {	
		return cache.searchReads(request);
	}	
	
	@Log @RequestMapping(value = "/referencesets/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReferenceSetsResponse searchReferenceSets(@RequestBody GASearchReferenceSetsRequest request) {
		return cache.searchReferenceSets(request);
	}	
	
	@Log @RequestMapping(value = "/references/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReferencesResponse searchReferences(@RequestBody GASearchReferencesRequest request) {	
		return cache.searchReferences(request);
	}	
	
	@Log @RequestMapping(value = "/variantsets/search", method = RequestMethod.POST)
	public @ResponseBody GASearchVariantSetsResponse searchVariantSets(@RequestBody GASearchVariantSetsRequest request) {
		return cache.searchVariantSets(request);
	}	
	
	@Log @RequestMapping(value = "/variants/search", method = RequestMethod.POST)
	public @ResponseBody GASearchVariantsResponse searchVariants(@RequestBody GASearchVariantsRequest request) {
		return cache.searchVariants(request);
	}	
}
