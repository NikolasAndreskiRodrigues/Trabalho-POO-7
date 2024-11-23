package br.edu.univille.poo2.login.controller.web;

import br.edu.univille.poo2.login.core.entity.User;
import br.edu.univille.poo2.login.core.entity.UserRole;
import br.edu.univille.poo2.login.core.repository.UserRepository;
import br.edu.univille.poo2.login.core.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("register/index");
    }

    @PostMapping
    public ModelAndView register(@RequestParam("username") String username,
                                 @RequestParam("name") String name,
                                 @RequestParam("password") String password,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 @RequestParam("email") String email) {

        // Verifica se as senhas coincidem
        if (!password.equals(confirmPassword)) {
            ModelAndView modelAndView = new ModelAndView("register/index");
            modelAndView.addObject("error", "As senhas não coincidem!");
            return modelAndView;
        }

        // Verifica se o username já está em uso
        if (userRepository.findByUsernameAndActiveTrue(username).isPresent()) {
            ModelAndView modelAndView = new ModelAndView("register/index");
            modelAndView.addObject("error", "O nome de usuário já está em uso!");
            return modelAndView;
        }

        // Verifica se o email já está em uso
        if (userRepository.findAll().stream().anyMatch(user -> user.getEmail().equals(email))) {
            ModelAndView modelAndView = new ModelAndView("register/index");
            modelAndView.addObject("error", "O email já está em uso!");
            return modelAndView;
        }

        // Busca o papel ROLE_USER (role_id = 1)
        UserRole roleUser = userRoleRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("Role não encontrado!"));

        // Cria o usuário
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setName(name);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        newUser.setActive(true);
        newUser.setRole(roleUser);  // Associação com o role de id = 1

        userRepository.save(newUser);

        // Redireciona para a página de login
        return new ModelAndView("redirect:/login");
    }
}
