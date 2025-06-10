package in.gov.lsgkerala.user.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    private Boolean countryType;
    @Pattern(regexp = "(^$|\\+?[0-9]{10})", message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message = "OTP is required")
    private String otp;
    @Email(message = "Invalid email format")
    private String email;
}
