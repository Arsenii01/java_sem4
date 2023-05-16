package com.example.practice14.controllers;

import com.example.practice14.dao.WorkersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WorkersController {

    private final WorkersDAO workersDAO;
    @Autowired
    public WorkersController(WorkersDAO m) {
        workersDAO = m;
    }
    @GetMapping("/workers")
    public ModelAndView workersPage(Model model) {
        model.addAttribute("workers", workersDAO.getAll());
        return new ModelAndView("workers");
    }
}
