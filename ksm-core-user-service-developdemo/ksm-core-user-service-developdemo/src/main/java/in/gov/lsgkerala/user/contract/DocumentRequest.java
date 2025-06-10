package in.gov.lsgkerala.user.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentRequest {
    private String email;
    private String documentNo;
    private String name;
    private String dob;
    private String gender;
}
