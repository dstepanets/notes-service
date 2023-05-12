package com.proxyseller.notes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	public static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
	private static final String HOME = "Home";
	private static final String USERS = "Users";

	@GetMapping({"/", "index"})
	public String homePage(Model model) {
		model.addAttribute(PAGE_TITLE_ATTRIBUTE, HOME);
		return "index";
	}

	@GetMapping("users")
	public String usersPage(Model model) {
		model.addAttribute(PAGE_TITLE_ATTRIBUTE, USERS);
		return "users";
	}
}
