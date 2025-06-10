package in.gov.lsgkerala.user.controller;


import in.gov.lsgkerala.user.contract.KycRequest;
import in.gov.lsgkerala.user.contract.LoginResponse;
import in.gov.lsgkerala.user.contract.RegisterRequest;
import in.gov.lsgkerala.user.contract.RegisterResponse;
import in.gov.lsgkerala.user.contract.UserLoginRequest;
import in.gov.lsgkerala.user.service.UserService;


import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // Apply CORS globally to all methods in this controller
public class UserController {

    private final UserService userService;

    // "User login via phone/email and OTP"
    @PostMapping("/user-login")
    public LoginResponse createUser(@RequestBody @Valid UserLoginRequest request) {
        return userService.userLogin(request);
    }

    // "Register a new user"
    @PostMapping("/register-user")
    public RegisterResponse registerUser(@RequestBody @Valid RegisterRequest request) {
        return userService.registerUser(request);
    }

    //  "Verify KYC with Aadhaar and OTP"
    @PostMapping("/kyc_verify")
    public String verifyKyc(@RequestBody @Valid KycRequest request) {
        return userService.kycVerification(request);
    }
    @PostMapping("/user-exists")
    public boolean userExists(@RequestBody @Valid UserLoginRequest request) {
        return userService.userExists(request);
    }
     @PostMapping("/pasport-upload")
    public String uploadPassport(@RequestParam("file") MultipartFile file) {
        return userService.handlePassportUpload(file);
}
}