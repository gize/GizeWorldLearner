package com.gize.worldlearner.web;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gize.worldlearner.model.context.Word;
import com.gize.worldlearner.model.service.context.IContextService;
 
@Controller
public class QuizController{
 

	@Autowired
	@Qualifier("contextService")
	private IContextService contextService;
	
	@RequestMapping(value="/quiz.html", method= RequestMethod.GET)
	public String handleRequest( @RequestParam String context, Model model) {
		
//		String vocabularyList = "At,W3Schools,you,will,find,all,the,Web-building,tutorials,you,need," +
//				"from,basic,HTML,to,advanced,XML,SQL,ASP,and,PHP.";
//		String[] arrVocabularyList = vocabularyList.split(",");
//		
//		model.addAttribute("context", context);
//		model.addAttribute("vocabularyList", arrVocabularyList);
//
//		Integer[] arrSelectedIndices =new Integer[]{15,16,20};
//		model.addAttribute("selectedIndices", arrSelectedIndices);
		
		List<Word> words = contextService.getWordsForContext(context);
		
		List<Integer> listSelectedIndices = contextService.createListSelectedIndices(words);
		
		model.addAttribute("context", context);
		model.addAttribute("vocabularyList", createListWordString(words));
		model.addAttribute("selectedIndices", listSelectedIndices);
		
		return "quiz";  
	}

	private List<String> createListWordString(List<Word> words){
		List<String> strWords = new ArrayList<String>();
		for(Word word : words){
			strWords.add(word.getWord());
		}
		return strWords;
	}
//	@RequestMapping("/quiz.html")
//	public ModelAndView handleRequest(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		
//		Map arr = request.getParameterMap();
//		String vocabularyList = "At,W3Schools,you,will,find,all,the,Web-building,tutorials,you,need," +
//				"from,basic,HTML,to,advanced,XML,SQL,ASP,and,PHP.";
//		String[] arrVocabularyList = vocabularyList.split(",");
//		
//		String selectedIndices="15,16,20";
//		Integer[] arrSelectedIndices =new Integer[]{15,16,20};
//		
//		request.setAttribute("vocabularyList", arrVocabularyList);
//		request.setAttribute("selectedIndices", arrSelectedIndices);
//		
//		ModelAndView modelAndView = new ModelAndView("quiz");
//
//
//		return modelAndView;  
//	}
//	
}