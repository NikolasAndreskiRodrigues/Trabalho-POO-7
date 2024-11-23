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
        ModelAndView modelAndView = new ModelAndView("register/index"); // Cria uma única instância de ModelAndView

        // Valida se as senhas coincidem
        if (!password.equals(confirmPassword)) {
            modelAndView.addObject("error", "As senhas não coincidem!");
            return modelAndView;
        }

        // Verifica se o nome de usuário já está em uso
        if (userRepository.findByUsernameAndActiveTrue(username).isPresent()) {
            modelAndView.addObject("error", "O nome de usuário já está em uso!");
            return modelAndView;
        }

        // Verifica se o email já está em uso
        if (userRepository.findAll().stream().anyMatch(user -> user.getEmail().equals(email))) {
            modelAndView.addObject("error", "O email já está em uso!");
            return modelAndView;
        }

        // Criação do novo usuário
        UserRole roleUser = userRoleRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("Role não encontrado!"));

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setName(name);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        newUser.setActive(true);
        newUser.setRole(roleUser);

        userRepository.save(newUser);

        return new ModelAndView("redirect:/login"); // Redireciona após sucesso
    }
}
