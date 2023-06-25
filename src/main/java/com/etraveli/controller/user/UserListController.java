package com.etraveli.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.etraveli.service.UserService;

@Controller
public class UserListController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/listUsers", method = RequestMethod.GET)
	public String findAllPersons(Model model) {
		model.addAttribute("users", userService.findAll());
		return "userList";
	}
}