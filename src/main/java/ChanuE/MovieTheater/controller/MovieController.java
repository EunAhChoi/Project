package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.movie.MovieResponseDTO;
import ChanuE.MovieTheater.dto.movie.MovieSaveRequestDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import ChanuE.MovieTheater.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies/")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/list")
    public String movieList(@ModelAttribute("MovieSearch") MovieSearch movieSearch,
                            PageRequestDTO pageRequestDTO, Model model){
        log.info("Get Movie List");
        PageResponseDTO<Object[], MovieResponseDTO> result = movieService.list(pageRequestDTO, movieSearch);
        model.addAttribute("result", result);
        return "/movies/movie_list";
    }

    @GetMapping("/create")
    public String createMovie(){
        log.info("Get Movie Create View");
        return "/movies/movie_create";
    }

    @PostMapping("/create")
    public String saveMovie(@ModelAttribute MovieSaveRequestDTO requestDto){
        log.info("이름 : " + requestDto.getName());
        movieService.saveMovie(requestDto);
        return "redirect:/movies/list";
    }

    // 사용자 영화 목록 화면 조회용.
    @GetMapping("/info")
    public String movieInfo(@ModelAttribute("MovieSearch") MovieSearch movieSearch,
                            PageRequestDTO pageRequestDTO, Model model) {
        log.info("Movie Information");
        PageResponseDTO<Object[], MovieResponseDTO> result = movieService.list(pageRequestDTO, movieSearch);
        model.addAttribute("result", result);
        return "/movies/movie_info";
    }

    // 사용자 영화 상세 정보창.
    @GetMapping("/{id}")
    public String readMovie(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                            @PathVariable("id") Long id, Model model) {
        log.info("Movie Specific");
        MovieResponseDTO findMovie = movieService.findOne(id);

        log.info(findMovie);
        model.addAttribute("result", findMovie);
        return "/movies/movie_read";
    }
}
