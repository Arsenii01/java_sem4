package com.example.pw15;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello.html");
		return modelAndView;
	}
}
