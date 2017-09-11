package uk.ac.ebi.pride.resubmissiontracker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.pride.resubmissiontracker.model.Resubmission;
import uk.ac.ebi.pride.resubmissiontracker.service.repository.ResubmissionRepository;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Service to save resubmissions to the database.
 */
@Service
public class ResubmissionKeepService {

  @Resource
  private ResubmissionRepository resubmissionRepository;

  /**
   * Default constructor.
   */
  public ResubmissionKeepService() {
  }

  /**
   * Saves the resubmission to the collection.
   * @param resubmission the resubmission to track.
   */
  @Transactional
  public void save(Resubmission resubmission) {
    resubmissionRepository.save(resubmission);
  }

  /**
   * Saves a list of resubmissions to the collection.
   * @param resubmissions the resubmission to track.
   */
  @Transactional
  public void save(Iterable<Resubmission> resubmissions) {
    if (resubmissions==null || !resubmissions.iterator().hasNext()) {
      resubmissionRepository.save(resubmissions);
    }
  }
  /**
   * Saves a collection of resubmissions to the collection.
   * @param resubmissions the resubmission to track.
   */
  @Transactional
  public Iterable<Resubmission> save(Collection<Resubmission> resubmissions) {
    return resubmissionRepository.save(resubmissions);
  }

  /**
   * Delete a resubmission in the collection.
   * @param resubmission the resubmission to delete.
   */
  @Transactional
  public void delete(Resubmission resubmission) {
    resubmissionRepository.delete(resubmission);
  }

  /**
   * Delete an iterable of resubmissions in the collection.
   * @param resubmissions the resubmissions to delete.
   */
  @Transactional
  public void delete(Iterable<Resubmission> resubmissions) {
    if (resubmissions==null || !resubmissions.iterator().hasNext()) {
      resubmissionRepository.delete(resubmissions);
    }
  }

  /**
   * Delete all the documents from the collection.
   */
  @Transactional
  public void deleteAll() {
    resubmissionRepository.deleteAll();
  }

  /**
   * Delete all the documents from the collection by project accession.
   */
  @Transactional
  public void deleteByProjectAccession(String projectAccession) {
    //Possible improvement, retrieve the ids to be deleted instead of the objects
    resubmissionRepository.delete(resubmissionRepository.findByProjectAccession(projectAccession));
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
