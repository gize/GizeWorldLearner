package com.gize.worldlearner.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class ChooseModeController {

	@RequestMapping("/choose_mode.html")
	public ModelAndView handleRequest(Model model) {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		
		if(user != null ){

			String aMessage = "Hello World MVC!";

			ModelAndView modelAndView = new ModelAndView("choose_mode");
			modelAndView.addObject("message", aMessage);

			return modelAndView;        
		} else {
			HttpServletRequest curRequest = 
					((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();

			RedirectView redirectView = new RedirectView(userService.createLoginURL(curRequest.getRequestURL().toString()));

			ModelAndView modelAndView = new ModelAndView(redirectView);
			return modelAndView;
		}
	}

}