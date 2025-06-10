package in.gov.lsgkerala.user.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KycResponse {
    private String message;
    private String userId;
}
