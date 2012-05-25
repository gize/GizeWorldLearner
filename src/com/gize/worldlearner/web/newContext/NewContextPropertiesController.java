package com.gize.worldlearner.web.newContext;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gize.worldlearner.model.context.Context;
 
@Controller
@RequestMapping("/new_context_properties.html")
public class NewContextPropertiesController {
	

	@Autowired(required=true)
	private ContextValidator contextValidator;// = new ContextValidator();
	
	@ModelAttribute("context")
	public Context initContext(){
		return new Context();
	}
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
	 
//	dataBinder.setDisallowedFields(new String[] {"id"});
	 
//		dataBinder.setRequiredFields(new String[] {"contextname"});
	 
//	dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	 
//	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//	dateFormat.setLenient(false);
//	dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String handleRequest(Model model) {

		return "new_context_properties";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@ModelAttribute("context") Context context, BindingResult result, Model model) {

		contextValidator.validate(context, result);
		
		if(result.hasErrors()){
			return "new_context_properties";
		} else {
		
			model.addAttribute("contextname", context.getContextname());

			return "define_text";
		}
	}
}
