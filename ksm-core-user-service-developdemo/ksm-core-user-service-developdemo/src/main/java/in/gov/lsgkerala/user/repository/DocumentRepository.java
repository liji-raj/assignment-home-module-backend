package in.gov.lsgkerala.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.gov.lsgkerala.user.model.Document;

public interface DocumentRepository  extends JpaRepository<Document, Long> {

   
    
}
