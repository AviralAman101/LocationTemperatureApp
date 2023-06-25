package com.etraveli.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.etraveli.model.User;
import com.etraveli.service.UserService;

@Controller
@SessionAttributes("user")
public class UserDeleteController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public String selectForDelete(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "userDelete";
	}
	
	@RequestMapping(value = "/deleteUserFailed", method = RequestMethod.GET)
	public String deleteUserFailed() {
		return "userDelete";
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String delete(@ModelAttribute User user, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
		String message = null;
		String viewName = null;
		try {
			userService.delete(user.getId());
			message = "User deleted. User id :" + user.getId();
			viewName = "redirect:/mvc/listUsers";
			sessionStatus.setComplete();
		} catch (Exception ex) {
			message = "User delete failed.";
			viewName = "redirect:/mvc/deleteUserFailed";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
}
