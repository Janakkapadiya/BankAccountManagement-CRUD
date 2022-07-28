//package com.accountmanagement.practice.Services;
//
//import com.accountmanagement.practice.ConfigurationAndSecurity.JwtHelper;
//import com.accountmanagement.practice.Model.User;
//import com.accountmanagement.practice.Repository.UserRepository;
//import com.accountmanagement.practice.dto.TokenReq.Tokenreq;
//import com.accountmanagement.practice.dto.TokenRes.TokenRes;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AuthService {
//    @Autowired
//    private JwtHelper jwtHelper;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private CostumUserdetailService costumUserdetailService;
//    @Autowired
//    @Qualifier("userDetails")
//    private UserDetailsService userDetailsService;
//
//    public TokenRes createAccessToken(Tokenreq tokenreq) throws Exception {
//        String username = tokenreq.getUserName();
//        String password = tokenreq.getPassword();
//        authenticate(username, password);
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        final String accessToken = jwtHelper.generateToken(userDetails);
//        Optional<User> user = userRepository.findByName(username);
//        return new TokenRes(user, accessToken);
//    }
//
//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("User is disabled");
//        } catch (BadCredentialsException e) {
//            throw new Exception("Bad credentials");
//        }
//    }
//}
