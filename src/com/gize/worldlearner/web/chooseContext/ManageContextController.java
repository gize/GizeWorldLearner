package com.gize.worldlearner.web.chooseContext;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gize.worldlearner.model.service.context.IContextService;

@Controller
public class ManageContextController {

	@Autowired
	@Qualifier("contextService")
	private IContextService contextService;

	@RequestMapping("/manage_context.html")
	public String handleRequest(Model model) {

		String aMessage = "Hello World MVC!";
		model.addAttribute("message", aMessage);

		return "manage_context";
	}

	@RequestMapping("/delete_context.html")
	public String deleteContext(@RequestParam String context, Model model) {

		contextService.deleteContext(context);
     
		return "redirect:manage_context.html";
	}

	@RequestMapping(value = "/contextlist.json", method = RequestMethod.GET)
	public @ResponseBody
	List<Option> test() {

		List<String> listContextName = contextService.getContextList();

		List<Option> options = new ArrayList<Option>();
		for (String contextName : listContextName) {
			options.add(new Option(contextName, contextName));
		}
		return options;

		// String s =
		// "[{\"caption\":\"aa\",\"value\":\"aaaa\"},{\"caption\":\"bb\",\"value\":\"bbbb\"}]";
		// return s;
	}

}

class Option {

	String caption;
	String value;

	public Option(String caption, String value) {
		this.caption = caption;
		this.value = value;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}