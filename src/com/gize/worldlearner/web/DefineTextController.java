package com.gize.worldlearner.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DefineTextController {

	@RequestMapping("/define_text.html")
	public String handleRequest(@RequestParam String contextname, Model model){

		model.addAttribute("contextname", contextname);

		return "define_text";  
	}

}