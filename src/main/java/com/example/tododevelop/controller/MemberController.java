package com.example.tododevelop.controller;


import com.example.tododevelop.dto.MemberResponseDto;
import com.example.tododevelop.dto.SignUpRequestDto;
import com.example.tododevelop.dto.SignUpResponseDto;
import com.example.tododevelop.dto.UpdateMemberRequestDto;
import com.example.tododevelop.service.MemberService;
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

}
