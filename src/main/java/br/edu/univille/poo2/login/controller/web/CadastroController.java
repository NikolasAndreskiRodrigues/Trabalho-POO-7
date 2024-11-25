package br.edu.univille.poo2.login.controller.web;

import br.edu.univille.poo2.login.core.entity.Avaliacao;
import br.edu.univille.poo2.login.core.entity.Hotel;
import br.edu.univille.poo2.login.core.repository.HotelRepository;
import br.edu.univille.poo2.login.core.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/a/admin/cadastrar")
public class CadastroController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;


    /*private static final String UPLOAD_DIR = "uploads/";*/

    @GetMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("cadastro/index");
        mv.addObject("hotel", new Hotel());
        return mv;
    }

    @PostMapping
    public String cadastrar(@ModelAttribute Hotel hotel /*@RequestParam("fotos") MultipartFile[] fotos*/) {
        /*
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        List<String> fotosPaths = new ArrayList<>();
        for (MultipartFile foto : fotos) {
            if (!foto.isEmpty()) {
                try {
                    Path path = Path.of(UPLOAD_DIR + foto.getOriginalFilename());
                    Files.copy(foto.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                    fotosPaths.add(path.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        hotel.setFotos(fotosPaths);
        */

        hotelRepository.save(hotel);

        for (Avaliacao avaliacao : hotel.getAvaliacoes()) {
            avaliacao.setHotel(hotel);
            avaliacaoRepository.save(avaliacao);
        }

        return "redirect:/a/admin";
    }

}
