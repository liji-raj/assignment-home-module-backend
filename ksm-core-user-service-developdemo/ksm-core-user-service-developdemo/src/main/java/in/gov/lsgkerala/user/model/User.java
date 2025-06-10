package in.gov.lsgkerala.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "k_smart_user")
public class User {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID id;

    // Contact Information
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    @Column(length = 10)
    private String phoneNumber;

    @Email(message = "Invalid email format")
    private String email;

    private String whatsappNumber;

    // Personal Information
    private String name;
    private String gender;
    private String dob;

    // Identification
    @Column(name = "aadhaar_no")
    private String aadhaarNo;

    private String documentNo;

    // User Metadata
    private Integer tenantId;
    private String userType;
    private Long regNo;

    // Status Flags
    private Boolean isActive;
    private Boolean isFirstLogin;
    private Boolean isKycVerified = Boolean.FALSE;
    private Boolean isDocumentVerified;
    private Boolean countryType = Boolean.TRUE;

    // Timestamps
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
