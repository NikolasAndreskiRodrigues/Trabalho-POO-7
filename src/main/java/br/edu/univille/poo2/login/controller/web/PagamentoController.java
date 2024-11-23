package br.edu.univille.poo2.login.controller.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/u/pagamento")
public class PagamentoController {


    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("pagamento/index");
    }
}
