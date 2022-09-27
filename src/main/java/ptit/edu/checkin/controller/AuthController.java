package ptit.edu.checkin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptit.edu.checkin.dto.request.LoginDto;
import ptit.edu.checkin.dto.response.LoginResponse;
import ptit.edu.checkin.dto.request.SignUpDto;
import ptit.edu.checkin.service.AuthService;

@Slf4j
@RestController
@RequestMapping("")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public LoginResponse authenticateUser(@RequestBody LoginDto loginDto) {
       return authService.authenticateUser(loginDto.getUserName(),loginDto.getPassword());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
      return authService.registerUser(signUpDto.getUserName(), signUpDto.getUserName());
    }
}
