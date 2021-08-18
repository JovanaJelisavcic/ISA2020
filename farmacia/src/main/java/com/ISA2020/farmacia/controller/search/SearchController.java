package com.ISA2020.farmacia.controller.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ISA2020.farmacia.entity.Drug;
import com.ISA2020.farmacia.entity.Farmacy;
import com.ISA2020.farmacia.entity.Views;
import com.ISA2020.farmacia.repository.DrugRepository;
import com.ISA2020.farmacia.repository.FarmacyRepository;
import com.ISA2020.farmacia.util.FilteringUtil;
import com.fasterxml.jackson.annotation.JsonView;



@RestController
@RequestMapping("/search")
public class SearchController {
	@Autowired
	FarmacyRepository farmacyRepo;
	@Autowired
	DrugRepository drugRepo;
	@Autowired 
	FilteringUtil filteringUtil;
	
	@JsonView(Views.Simple.class)
	@GetMapping(value="/farmacy/{criteria}")
	public ResponseEntity<List<Farmacy>> searchFarmacyByNameOrPlace(@PathVariable String criteria){
		 StringBuilder sb = new StringBuilder(criteria.concat("%"));
		 sb.insert(0,"%");
		List<Farmacy> farmacies = farmacyRepo.findByAdressLikeIgnoreCaseOrNameLikeIgnoreCase(sb.toString(), sb.toString());
		if(farmacies.isEmpty() || farmacies==null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Farmacy>>(farmacies, HttpStatus.OK);
	}
	
	@JsonView(Views.Detailed.class)
	@GetMapping(value="/drugs/{name}")
	public ResponseEntity<List<Drug>> searchDrugsByName(@PathVariable String name){
		 StringBuilder sb = new StringBuilder(name.concat("%"));
		 sb.insert(0,"%");
		List<Drug> drugs = drugRepo.findByNameLikeIgnoreCase(sb.toString());
		if(drugs.isEmpty() || drugs==null) {
			return ResponseEntity.notFound().build();
		}
		List<Drug> filtered= filteringUtil.filterPrices(drugs);
		if(filtered.isEmpty() || filtered==null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Drug>>(drugs, HttpStatus.OK);
	}


	
	
}
