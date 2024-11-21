package br.edu.univille.poo2.login.core.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MinNoites {

    @Autowired
    private LocalDate dataCheckIn;
    @Autowired
    private LocalDate dataCheckOut;
    @Autowired
    private static final int MIN_NIGHTS = 1;

    public MinNoites(LocalDate dataCheckIn, LocalDate dataCheckOut) {

        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
    }

    public boolean verificarDisponibilidade() {
        long numNoites = ChronoUnit.DAYS.between(dataCheckIn, dataCheckOut);
        return numNoites >= MIN_NIGHTS;
    }
    @Override
    public MinNoites loadUserByUsername(String username) throws UsernameNotFoundException {
        var opt = userRepository.findByUsernameAndActiveTrue(username);
        if(opt.isEmpty()) {throw new UsernameNotFoundException(username);}
        var user = opt.get();
        var list = List.of(user.getRole());
        System.out.println(user);
        return new  org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(list));
    }
}
