package com.in.pathshala.onlineLoginSignup.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in.pathshala.onlineLoginSignup.Dto.BaseResponseDTO;
import com.in.pathshala.onlineLoginSignup.Dto.ResponseDto;
import com.in.pathshala.onlineLoginSignup.Dto.user.SignInDto;
import com.in.pathshala.onlineLoginSignup.Dto.user.SignInResponseDto;
import com.in.pathshala.onlineLoginSignup.Dto.user.SignupDto;
import com.in.pathshala.onlineLoginSignup.exceptions.AuthenticationFailException;
import com.in.pathshala.onlineLoginSignup.model.Product;
import com.in.pathshala.onlineLoginSignup.model.User;
import com.in.pathshala.onlineLoginSignup.repository.UserRepository;
import com.in.pathshala.onlineLoginSignup.service.AuthenticationService;
import com.in.pathshala.onlineLoginSignup.service.UserService;
//import com.in.pathshala.onlineLoginSignup.repository



@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	
	@Autowired
    AuthenticationService authenticationService;
	
	@Autowired
    UserRepository userRepository;

	//two apis	
	//signup:localhost:8085/user/signup
	@PostMapping("/signup")
	public ResponseDto signup(@RequestBody SignupDto signupDto) {
		return userService.signup(signupDto);
	}
	
	//localhost:8085/user/all?token=a1346f8b-30f9-4448-b3c1-19adef5952dc
	@GetMapping("/all")
    public List<User> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }
	//localhost:8085/user/all/1?token=a1346f8b-30f9-4448-b3c1-19adef5952dc
//	@GetMapping("/all/{id}")
//    public User findUserById(@PathVariable long id ,@RequestParam("token") String token) throws AuthenticationFailException {
//        authenticationService.authenticate(token);
//		return userRepository.findById(id).get();
//    }

	@GetMapping("/all/{id}")
    public User findUserById(@PathVariable long id ) throws AuthenticationFailException {
		return userRepository.findById(id).get();
    }
//	@PostMapping("/saveUser")
//    public User saveUser(@RequestBody User user ) throws AuthenticationFailException {
//		return userRepository.saveUser(user);
//    }
//	@PutMapping("/updateUser")
//    public User updateUser(@RequestBody User user ) throws AuthenticationFailException {
//		return userRepository.updateUser(user);
//    }
//signin:localhost:8085/user/signin
	@PostMapping("/signin")
	public  SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
		return userService.signIn(signInDto);
	}
	
	
	
	
}
