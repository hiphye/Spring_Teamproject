package com.spring.myspring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	UserServiceImpl service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/loginOk", method = RequestMethod.POST)
	public String loginCheck(HttpSession session, UserVO vo) {
		String returnURL = "";
		if (session.getAttribute("login") != null) {
			session.removeAttribute("login");
		}
		UserVO loginvo = service.getUser(vo);
		if (loginvo != null) { // �α��� ����
			System.out.println("�α��� ����!");
			session.setAttribute("login", loginvo);
			returnURL = "redirect:/posts";
		} else { // �α��� ����
			System.out.println("�α��� ����!");
			returnURL = "redirect:/login/login";
		}
		return returnURL;
	}

	// �α׾ƿ� �ϴ� �κ�
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login/login";
	}
}
