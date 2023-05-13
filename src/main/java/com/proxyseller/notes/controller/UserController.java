package com.proxyseller.notes.controller;

import com.proxyseller.notes.model.User;
import com.proxyseller.notes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

	private UserService userService;

	private static final String USERS = "Users";
	private static final String LOGIN = "Login";
	private static final String REGISTER = "Register";

	@GetMapping("users")
	public String usersPage(Model model) {
		List<User> allUsers = userService.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		model.addAttribute(model.addAttribute(HomeController.PAGE_TITLE_ATTRIBUTE, USERS));
		return "users";
	}

	@GetMapping("login")
	public String loginPage(Model model) {
		model.addAttribute(model.addAttribute("user", new User()));
		model.addAttribute(model.addAttribute(HomeController.PAGE_TITLE_ATTRIBUTE, LOGIN));
		return "login";
	}

	@GetMapping("register")
	public String registerPage(Model model) {
		model.addAttribute(model.addAttribute("user", new User()));
		model.addAttribute(model.addAttribute(HomeController.PAGE_TITLE_ATTRIBUTE, REGISTER));
		return "register";
	}

	@PostMapping(value = {"/register"})
	public String submitRegisterForm(@ModelAttribute User user) {
		userService.register(user);
		return "redirect:/login";
	}
}
