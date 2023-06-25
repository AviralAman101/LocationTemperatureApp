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
public class UserUpdateController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
	public String selectForUpdate(@PathVariable Long id, Model model) {
		model.addAttribute("person", userService.findById(id));
		return "userUpdate";
	}
	
	@RequestMapping(value="/updateUserFailed", method=RequestMethod.GET)
	public String updateUserFailed() {
		return "userUpdate";
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String performUpdate(@ModelAttribute User user, RedirectAttributes redirectAttributes,
								SessionStatus sessionStatus) {
		String message = null;
		String viewName = null;
		try {
			userService.update(user);
			message = "User updated. User id :" + user.getId();
			viewName = "redirect:/mvc/listUsers";
			sessionStatus.setComplete();
		} catch (Exception ex) {
			message = "User update failed. ";
			viewName = "redirect:/mvc/updateUserFailed";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
}
