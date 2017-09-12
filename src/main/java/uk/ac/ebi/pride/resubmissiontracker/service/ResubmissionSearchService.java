package uk.ac.ebi.pride.resubmissiontracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import uk.ac.ebi.pride.resubmissiontracker.model.Resubmission;
import uk.ac.ebi.pride.resubmissiontracker.service.repository.ResubmissionRepository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Service for looking up projects that have been recorded as being resubmissions,
 * by either document IDs or project accessions.
 */
@Service
public class ResubmissionSearchService {

  @Resource
  private ResubmissionRepository resubmissionRepository;

  @Autowired
  private MongoOperations mongoOperations;

  public ResubmissionSearchService() {
  }

  /**
   * Constructor, includes setting the resubmissionRepository.
   * @param resubmissionRepository the resubmissionRepository to set.
   */
  public ResubmissionSearchService(ResubmissionRepository resubmissionRepository) {
    this.resubmissionRepository = resubmissionRepository;
  }

  public Resubmission findById(String id) {
    return resubmissionRepository.findOne(id);
  }

  public List<Resubmission> findByIdIn(Collection<String> ids) {
    return resubmissionRepository.findByIdIn(ids);
  }

  public List<Resubmission> findByProjectAccession(String projectAccession) {
    return resubmissionRepository.findByProjectAccession(projectAccession);
  }

  public List<Resubmission> findAll() {
    return resubmissionRepository.findAll();
  }

  /**
   * Sets new resubmissionRepository.
   *
   * @param resubmissionRepository New value of resubmissionRepository.
   */
  public void setResubmissionRepository(ResubmissionRepository resubmissionRepository) {
    this.resubmissionRepository = resubmissionRepository;
  }
}
