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
    public void onApplicationEvent(ContextRefreshedEvent event){
        if(userRoleRepository.findAll().isEmpty()){
            UserRole userRole = new UserRole();
            userRole.setCode("ROLE_ADMIN");
            userRole.setName("Administrador de Sistema");
            userRoleRepository.save(userRole);
            userRole = new UserRole();
            userRole.setCode("ROLE_USER");
            userRole.setName("Usuário");
            userRoleRepository.save(userRole);
        }
        if(userRepository.findAll().isEmpty()){
            var roleAdmin = userRoleRepository.findAll().stream().filter(userRole -> userRole.getCode().equals("ROLE_ADMIN")).findFirst();
            User user = new User();
            user.setUsername("admin");
            user.setActive(true);
            user.setName("Administrador de Sistema");
            user.setRole(roleAdmin.get());
            user.setPassword(bCryptPasswordEncoder.encode("senha123"));
            userRepository.save(user);
            var roleUser = userRoleRepository.findAll().stream().filter(userRole -> userRole.getCode().equals("ROLE_USER")).findFirst();
            user = new User();
            user.setUsername("user");
            user.setActive(true);
            user.setName("teste");
            user.setRole(roleUser.get());
            user.setPassword(bCryptPasswordEncoder.encode("senha123"));
            userRepository.save(user);
        }
    }

}
