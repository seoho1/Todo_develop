package com.example.tododevelop.controller;


import com.example.tododevelop.dto.*;
import com.example.tododevelop.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

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
            @RequestBody UpdateMemberRequestDto requestDto
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
    public ResponseEntity<String> login(
            @RequestBody LoginRequestDto loginRequestDto,
            HttpServletRequest request,
            HttpServletResponse response ) {

        LoginResponseDto loginResponseDto = memberService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        //세션에 사용자 정보 저장
        HttpSession session = request.getSession();
        session.setAttribute("sessionKey",loginResponseDto);

        //쿠키 생성
        Cookie cookie = new Cookie("userEmail", loginResponseDto.getEmail());
        cookie.setPath("/");
        cookie.setMaxAge(240);
        response.addCookie(cookie);

        return new ResponseEntity<>("로그인 성공",HttpStatus.OK);
    }
}
