package br.edu.univille.poo2.login.core.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class MinNoites {

    @Autowired
    private Date dataCheckIn;
    @Autowired
    private Date dataCheckOut;
    @Autowired
    private static final int MIN_NIGHTS = 1;

    public MinNoites(Date dataCheckIn, Date dataCheckOut) {
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
    }

    public boolean verificarDisponibilidade() {
        long numNoites = ChronoUnit.DAYS.between(dataCheckIn, dataCheckOut);
        return numNoites >= MIN_NIGHTS;
    }
}
