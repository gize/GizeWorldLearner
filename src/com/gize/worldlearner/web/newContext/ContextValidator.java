package com.gize.worldlearner.web.newContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.gize.worldlearner.Const;
import com.gize.worldlearner.model.context.Context;
import com.gize.worldlearner.model.service.context.IContextService;

@Component
public class ContextValidator {
	
	@Autowired(required=true)
	@Qualifier("contextService")
	private IContextService contextService;
	 
	public void validate(Context context, Errors errors) {
	 
		if(context.getContextname() != null && context.getContextname().isEmpty()){
			errors.rejectValue("contextname", "validation.context.null", Const.DEFAULT_MESSAGE_REQUIRED);		
		}

		if(contextService.existsContext(context.getContextname())){
			
			errors.rejectValue("contextname", "validation.context.exists", Const.DEFAULT_MESSAGE_REQUIRED);
		}

//		if (!errors.hasFieldErrors("name")) {
//			Country existingCountry = worldService.getCountryByName(country.getName());
//			if (existingCountry != null &&
//					(country.isNew() || !country.getId().equals(existingCountry.getId()))) {
//				errors.rejectValue("name", "validation.exists", "exists");
//			}
//		}
	}
}


