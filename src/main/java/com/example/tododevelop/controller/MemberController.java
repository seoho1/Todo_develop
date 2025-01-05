package com.example.tododevelop.controller;


import com.example.tododevelop.dto.login.LoginRequestDto;
import com.example.tododevelop.dto.login.LoginResponseDto;
import com.example.tododevelop.dto.member.MemberResponseDto;
import com.example.tododevelop.dto.member.UpdateMemberRequestDto;
import com.example.tododevelop.dto.signup.SignUpRequestDto;
import com.example.tododevelop.dto.signup.SignUpResponseDto;
import com.example.tododevelop.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto = memberService.signUp(
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getEmail()
        );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

   @GetMapping("/{id}")
   public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id){

       MemberResponseDto memberResponseDto = memberService.findById(id);

       return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
   }

   @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMemberRequestDto requestDto
   )  {
       MemberResponseDto memberResponseDto = memberService.updateMember(id, requestDto.getUsername(), requestDto.getEmail());

       return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MemberResponseDto> deleteMember(
            @PathVariable Long id
    ) {
        memberService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody LoginRequestDto loginRequestDto,
            HttpServletRequest request,
            HttpServletResponse response ) {

        LoginResponseDto loginResponseDto = memberService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());


        HttpSession session = request.getSession();
        session.setAttribute("sessionKey",loginResponseDto.getId());


        Cookie cookie = new Cookie("userEmail", loginResponseDto.getEmail());
        cookie.setPath("/");
        cookie.setMaxAge(240);
        response.addCookie(cookie);

        return new LoginResponseDto(loginResponseDto.getId(),loginRequestDto.getEmail(), loginResponseDto.getUsername());
    }
}
