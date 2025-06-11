package in.gov.lsgkerala.user.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import in.gov.lsgkerala.user.constants.DocumentType;
import in.gov.lsgkerala.user.service.DocumentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/document")
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadKycDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("documentType") DocumentType documentType,
            @RequestParam("documentNumber") String documentNumber,
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth
    ) {
        // ✅ Use the instance method, not a static call
        documentService.processKycUpload(file, documentType, documentNumber, name, gender, dateOfBirth);
        return ResponseEntity.ok("Document uploaded successfully");
    }
}
