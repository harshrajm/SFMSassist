package com.sfms.sfmsassist.controller;

import com.sfms.sfmsassist.config.CurrentUser;
import com.sfms.sfmsassist.entities.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

	@Autowired
	CurrentUser currentUser;

	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutController(HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes)
	{
		
		
		return logout(model, redirectAttributes, null, request, response);
	}

	private String logout(Model model, RedirectAttributes redirectAttributes, Object object,
			HttpServletRequest request, HttpServletResponse response) {
		
	UserDetail currentUserObj=	currentUser.getCurrentUser();
	
	if(currentUserObj!=null)
	{
		
		System.out.println(currentUserObj.getFirstName()+ " "+currentUserObj.getLastName()+" is getting logged out.");
		
		currentUser.removeCurrentUser(request, response);
	}
	
	return createRedirectViewPath("/loginform");
	
		
		
	}

	private String createRedirectViewPath(String mapping) {
		StringBuilder redirectViewPath = new StringBuilder();
		redirectViewPath.append("redirect:");
		redirectViewPath.append(mapping);
		return redirectViewPath.toString();
	}
	
	

}
