package in.gov.lsgkerala.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import in.gov.lsgkerala.user.constants.DocumentType;
@Entity
@Setter
@Getter
@Table(name="document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String documentNumber;
    private String documentName;
    private String documentType;
    private String filePath; 
    private LocalDateTime uploadedAt = LocalDateTime.now();
    public void setName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setName'");
    }
    public void setGender(String gender) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGender'");
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDateOfBirth'");

    }
   @ManyToOne
@JoinColumn(name = "user_id")
private User user;
}
