package uk.ac.ebi.pride.resubmissiontracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import uk.ac.ebi.pride.resubmissiontracker.model.Resubmission;
import uk.ac.ebi.pride.resubmissiontracker.service.repository.ResubmissionRepository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class ResubmissionSearchService {

  @Resource
  private ResubmissionRepository resubmissionRepository;

  @Autowired
  private MongoOperations mongoOperations;

  public ResubmissionSearchService() {
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

  /**
   * Sets new resubmissionRepository.
   *
   * @param resubmissionRepository New value of resubmissionRepository.
   */
  public void setResubmissionRepository(ResubmissionRepository resubmissionRepository) {
    this.resubmissionRepository = resubmissionRepository;
  }
}
