package com.kavi.user.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.kavi.user.bo.UserBO;
import com.kavi.user.vo.LoginVO;
import com.kavi.user.vo.UserVO;

@RestController
@RequestMapping("/base")
public class LoginController {

	@Autowired
	private UserBO userBO;

	@GetMapping("/index")
	public ModelAndView index(Model model) {
		model.addAttribute("message", "Welcome to kavi project!!!");
		return new ModelAndView("index");
	}

	@GetMapping("/login")
	public ModelAndView login(Model model) {
		model.addAttribute("message", "Please Enter Your Login Details");
		model.addAttribute("loginVO", new LoginVO());
		return new ModelAndView("login");
	}

	@PostMapping("/loginAction")
	public ModelAndView loginAction(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("loginVO") LoginVO loginVO) {
		UserVO userVO = null;
		request.getSession().invalidate();

		if (loginVO != null) {
			userVO = userBO.validateUserCredential(loginVO);
		}

		if (userVO != null) {
			setSessionInfo(request, userVO);
			model.addAttribute("message", "Login Successful!!");
		} else {
			model.addAttribute("message", "Login Failure!!");
		}

		return new ModelAndView("index");
	}

	private void setSessionInfo(HttpServletRequest request, UserVO userVO) {
		WebUtils.setSessionAttribute(request, "LOGGEDUSERID", userVO.getUserId());
	}

}
