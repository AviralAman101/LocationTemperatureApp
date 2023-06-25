package com.etraveli.controller.user;

import com.etraveli.model.User;
import com.etraveli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("user")
public class UserCreateController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String startCreatingNewUser(Model model) {
		model.addAttribute("user", new User());
		return "userCreate";
	}
	
	@RequestMapping(value = "/createUserFailed", method = RequestMethod.GET)
	public String createUserFailed() {
		return "userCreate";
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String performCreate(@ModelAttribute User user, RedirectAttributes redirectAttributes,
								SessionStatus sessionStatus) {
		String message = null;
		String viewName = null;
		try {
			userService.create(user);
			message = "User created. User id :" + user.getId();
			viewName = "redirect:/mvc/listUsers";
			sessionStatus.setComplete();
		} catch (Exception ex) {
			message = "User create failed";
			viewName = "redirect:/mvc/createUserFailed";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
}
