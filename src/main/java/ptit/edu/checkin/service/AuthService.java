package ptit.edu.checkin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ptit.edu.checkin.jwt.JwtTokenProvider;
import ptit.edu.checkin.dto.response.LoginResponse;
import ptit.edu.checkin.entity.Role;
import ptit.edu.checkin.entity.User;
import ptit.edu.checkin.repository.RoleRepository;
import ptit.edu.checkin.repository.UserRepository;

import java.util.Collections;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> registerUser(String userName,String paasword){
        if (userRepository.existsByUserName(userName)) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(paasword));
        Role roles = roleRepository.findByName("USER").get(0);
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    public LoginResponse authenticateUser(String userName, String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info(SecurityContextHolder.getContext().getAuthentication().getName());

        String jwt=jwtTokenProvider.generateToken((CustomUserDetails)authentication.getPrincipal());
        log.info("log: "+authentication.getPrincipal().toString());
        return new LoginResponse(jwt);
    }
}
