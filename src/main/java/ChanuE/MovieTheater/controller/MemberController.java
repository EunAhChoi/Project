package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Address;
import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/sign_up")
    public String createForm(Model model){
        model.addAttribute("modelForm", new MemberForm());
        return "/members/signup";
    }


    @PostMapping("/sign_up")
    public String create(HttpServletRequest request, HttpServletResponse response) throws Exception{

        Address address = new Address(request.getParameter("signup_city"), request.getParameter("signup_email"));

        Member member = new Member();
        member.setNickname(request.getParameter("signup_id"));
        member.setPassword(request.getParameter("signup_pw"));
        member.setName(request.getParameter("signup_name"));

        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";
    }
}
