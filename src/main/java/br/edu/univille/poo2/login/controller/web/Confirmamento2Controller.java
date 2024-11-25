package br.edu.univille.poo2.login.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/u/confirmamento2")
public class Confirmamento2Controller {


    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("confirmamento2/index");
    }
}
