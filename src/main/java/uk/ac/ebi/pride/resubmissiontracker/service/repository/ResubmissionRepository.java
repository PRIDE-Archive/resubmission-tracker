package uk.ac.ebi.pride.resubmissiontracker.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.pride.resubmissiontracker.model.Resubmission;

import java.util.Collection;
import java.util.List;

/**
 * Repository to query for Resubmission documents.
 */
@Repository
public interface ResubmissionRepository extends MongoRepository<Resubmission, String> {

  Resubmission findById(String id);
  List<Resubmission> findByIdIn(Collection<String> id);
  List<Resubmission> findByProjectAccession(String projectAccession);

}

