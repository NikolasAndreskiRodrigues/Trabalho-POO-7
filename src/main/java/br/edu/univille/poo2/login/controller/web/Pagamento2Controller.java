package br.edu.univille.poo2.login.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/u/pagamento2")
public class Pagamento2Controller {


    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("pagamento2/index");
    }
}
