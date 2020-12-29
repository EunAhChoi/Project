package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Reservation;
import ChanuE.MovieTheater.dto.member.MemberResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.reservation.ReservationResponseDto;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.service.MemberService;
import ChanuE.MovieTheater.service.MovieService;
import ChanuE.MovieTheater.service.ReservationService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;
    private final MovieService movieService;

    @GetMapping("/reservations")
    public String reservationList(@ModelAttribute("reservationSearch") ReservationSearch reservationSearch, Model model){

        List<ReservationResponseDto> reservations = reservationService.findAll(reservationSearch);

        for (ReservationResponseDto reservation : reservations) {
            System.out.println("id = " + reservation.getId());
            System.out.println("member name = " + reservation.getMember().getMemberName());
            System.out.println("movie name = " + reservation.getMovie().getMovieName());
            System.out.println("status = " + reservation.getStatus());
        }

        model.addAttribute("reservations", reservations);

        return "/reservation/reservation_list";

    }

    @GetMapping("/reservation/create")
    public String reservationForm(Model model){

        List<MemberResponseDto> findMembers = memberService.findAll();
        List<MovieResponseDto> findMovies = movieService.findAll();
        model.addAttribute("members", findMembers);
        model.addAttribute("movies", findMovies);

        return "/reservation/reservation_form";

    }

    @PostMapping("/reservation/create")
    public String createReservation(@RequestParam("memberId") Long memberId, @RequestParam("movieId") Long movieId){

        reservationService.reservation(memberId, movieId);

        return "redirect:/";

    }


}