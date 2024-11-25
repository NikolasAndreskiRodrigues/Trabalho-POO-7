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

    // Diretório onde as imagens serão armazenadas
    private static final String UPLOAD_DIR = "uploads/";

    @GetMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("cadastro/index");
        mv.addObject("hotel", new Hotel()); // Inicializa um novo hotel
        return mv;
    }

    @PostMapping
    public String cadastrar(@ModelAttribute Hotel hotel, @RequestParam("fotos") MultipartFile[] fotos) {
        // Criar diretório de uploads caso não exista
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Lista para armazenar os caminhos das fotos
        List<String> fotosPaths = new ArrayList<>();
        for (MultipartFile foto : fotos) {
            if (!foto.isEmpty()) {
                try {
                    // Salva o arquivo no diretório especificado
                    Path path = Path.of(UPLOAD_DIR + foto.getOriginalFilename());
                    Files.copy(foto.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                    // Adiciona o caminho do arquivo à lista de fotos
                    fotosPaths.add(path.toString());
                } catch (IOException e) {
                    e.printStackTrace(); // Lide com o erro de upload de arquivo
                }
            }
        }

        // Atribui as fotos ao hotel
        hotel.setFotos(fotosPaths);

        // Salva o hotel
        hotelRepository.save(hotel);

        // Salva as avaliações (se houverem)
        for (Avaliacao avaliacao : hotel.getAvaliacoes()) {
            avaliacao.setHotel(hotel); // Associa a avaliação ao hotel
            avaliacaoRepository.save(avaliacao); // Salva a avaliação
        }

        return "redirect:/a/admin"; // Redireciona para a página principal do admin
    }

}
