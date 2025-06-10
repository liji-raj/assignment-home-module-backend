package in.gov.lsgkerala.user.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class RegisterResponse {

    private String message;
    private String userId;
    
}
