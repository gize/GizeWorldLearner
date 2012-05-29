package com.gize.worldlearner.web;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gize.worldlearner.model.service.context.IContextService;
 
@Controller
public class SaveNewTextController{
	
	@Autowired
	@Qualifier("contextService")
	private IContextService contextService;
	
	@RequestMapping("/save_new_text.html")
	public String handleRequest(
			@RequestParam("contextName") String contextName,
			@RequestParam("vocabularyList") Object vocabularyList,
			@RequestParam("selectedIndices") Object selectedIndices, 
			@RequestParam("meaningArray") Object meaningArray, 
			Model model) {

		String[] arrVocabularyList = ((String)vocabularyList).split(",");
		List<String> listVocabularyList = Arrays.asList( arrVocabularyList );
		
		String[] meanings = ((String)meaningArray).split(",");
		List<String> listMeanings =  Arrays.asList( meanings );
		
		List<Integer> arrSelectedIndices = new ArrayList<Integer>(); 
		for (String selectedIndex : ((String)selectedIndices).split(",")) {
			arrSelectedIndices.add(Integer.parseInt(selectedIndex));
		}

		contextService.saveNewContext(contextName, listVocabularyList, arrSelectedIndices, listMeanings);
		contextService.setModelForContext(contextName, model);
		
		return "quiz";  
	}
}