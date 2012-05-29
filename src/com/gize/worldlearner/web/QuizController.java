package com.gize.worldlearner.web;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gize.worldlearner.model.service.context.IContextService;
 
@Controller
public class QuizController{
 

	@Autowired
	@Qualifier("contextService")
	private IContextService contextService;
	
	@RequestMapping(value="/quiz.html", method= RequestMethod.GET)
	public String handleRequest( @RequestParam String context, Model model) {
		

		contextService.setModelForContext(context, model);
		return "quiz";  
	}
}