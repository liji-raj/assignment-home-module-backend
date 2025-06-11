package in.gov.lsgkerala.user.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.gov.lsgkerala.user.constants.DocumentType;
import in.gov.lsgkerala.user.model.Document;
import in.gov.lsgkerala.user.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    private final String UPLOAD_DIR = "uploads/";

    public void processKycUpload(
            MultipartFile file,
            DocumentType documentType,
            String documentNumber,
            String name,
            String gender,
            LocalDate dateOfBirth
    ) {
        try {
            
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + filename);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

        
            Document doc = new Document();
            doc.setDocumentType(documentType.name());
            doc.setDocumentNumber(documentNumber);
            doc.setName(name);
            doc.setGender(gender);
            doc.setDateOfBirth(dateOfBirth);
            doc.setFilePath(path.toString());

            documentRepository.save(doc);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save KYC document", e);
        }
    }
}
