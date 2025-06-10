
package in.gov.lsgkerala.user.service;

import in.gov.lsgkerala.user.contract.KycRequest;
import in.gov.lsgkerala.user.contract.LoginResponse;
import in.gov.lsgkerala.user.contract.RegisterRequest;
import in.gov.lsgkerala.user.contract.RegisterResponse;
import in.gov.lsgkerala.user.contract.UserLoginRequest;
import in.gov.lsgkerala.user.model.User;
import in.gov.lsgkerala.user.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private static final String OTP = "123456";

    // /login using phone or email with OTP.
    public LoginResponse userLogin(UserLoginRequest request) {
        Optional<User> userOpt = Optional.empty();

        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            userOpt = userRepository.findByPhoneNumber(request.getPhoneNumber());
        } else if (request.getEmail() != null && !request.getEmail().isBlank()) {
            userOpt = userRepository.findByEmail(request.getEmail());
        } else {
            return new LoginResponse("Phone number or email must be provided.");
        }

        if (userOpt.isEmpty()) {
            return new LoginResponse("User not found. Please register.");
        }

        User user = userOpt.get();

        if (!isValidOtp(request.getOtp())) {
            return new LoginResponse("Invalid OTP. Access denied.");
        }

        if (Boolean.TRUE.equals(user.getIsKycVerified())) {
            if (!Boolean.TRUE.equals(user.getIsFirstLogin())) {
                user.setIsFirstLogin(true);
                userRepository.save(user);
            }
            return new LoginResponse("Login successful");
        } else {
            return new LoginResponse("KYC not completed. Please verify your Aadhaar first.");
        }
    }

      //  Registers a new user after OTP verification.
    public RegisterResponse registerUser(RegisterRequest request) {
        boolean isIndian = Boolean.TRUE.equals(request.getCountryType());
        String identifier = isIndian ? request.getPhoneNumber().trim() : request.getEmail().trim();

        Optional<User> existingUser = isIndian
                ? userRepository.findByPhoneNumber(identifier)
                : userRepository.findByEmail(identifier);

        if (existingUser.isPresent()) {
            return new RegisterResponse("User already registered", identifier);
        }

        if (!isValidOtp(request.getOtp())) {
            return new RegisterResponse("Invalid OTP. Access denied", null);
        }

        User user = modelMapper.map(request, User.class);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);

         return new RegisterResponse("User account created", identifier);
    }
           // Verifies user KYC using Aadhaar and OTP.
    public String kycVerification(KycRequest request) {
        Optional<User> userOpt;

        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            userOpt = userRepository.findByPhoneNumber(request.getPhoneNumber());
        } else if (request.getEmail() != null && !request.getEmail().isBlank()) {
            userOpt = userRepository.findByEmail(request.getEmail());
        } else {
            return "Phone number or email must be provided.";
        }

        if (userOpt.isEmpty()) {
            return "User not found.";
        }

        User user = userOpt.get();

        if (Boolean.TRUE.equals(user.getIsKycVerified())) {
            return "KYC already verified for this user.";
        }

        if (!isValidOtp(request.getOtp())) {
            return "Invalid OTP.";
        }

        user.setAadhaarNo(request.getAadhaarNo());
        user.setIsKycVerified(true);
        user.setIsActive(true);
        user.setIsFirstLogin(true);
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);

        return "KYC verification successful. User is now active.";
    }

    // OTP check
     
    private boolean isValidOtp(String otp) {
        return OTP.equals(otp);
    }

public Boolean userExists(UserLoginRequest request) {
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            return userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent();
        } else if (request.getEmail() != null && !request.getEmail().isBlank()) {
            return userRepository.findByEmail(request.getEmail()).isPresent();
        }
        return false;
    }
public String handlePassportUpload(MultipartFile file) {
      
        return "Passport uploaded successfully";
    }
}
