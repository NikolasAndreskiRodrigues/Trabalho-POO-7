package br.edu.univille.poo2.login.settings;

import br.edu.univille.poo2.login.core.entity.User;
import br.edu.univille.poo2.login.core.entity.UserRole;
import br.edu.univille.poo2.login.core.repository.UserRepository;
import br.edu.univille.poo2.login.core.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Startup {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRoleRepository.findAll().isEmpty()) {
            UserRole adminRole = new UserRole();
            adminRole.setCode("ROLE_ADMIN");
            adminRole.setName("Administrador de Sistema");
            userRoleRepository.save(adminRole);
        }

        if (userRepository.findAll().isEmpty()) {
            var roleAdmin = userRoleRepository.findAll().stream()
                    .filter(userRole -> userRole.getCode().equals("ROLE_ADMIN"))
                    .findFirst();

            if (roleAdmin.isPresent()) {
                User admin = new User();
                admin.setUsername("Admin");
                admin.setActive(true);
                admin.setName("Administrador de Sistema");
                admin.setRole(roleAdmin.get());
                admin.setPassword(bCryptPasswordEncoder.encode("senha123"));
                userRepository.save(admin);
            }
        }
    }
}
