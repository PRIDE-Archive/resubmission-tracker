package uk.ac.ebi.pride.resubmissiontracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Models a document to in the projectresubmissions collection.
 */
@Document(collection = "projectresubmissions")
public class Resubmission {
  @Id
  private String id;
  private String projectAccession;
  private List<Date> resubmissionDates;

  /**
   * Sets new projectAccession.
   *
   * @param projectAccession New value of projectAccession.
   */
  public void setProjectAccession(String projectAccession) {
    this.projectAccession = projectAccession;
  }

  /**
   * Sets new id.
   *
   * @param id New value of id.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets projectAccession.
   *
   * @return Value of projectAccession.
   */
  public String getProjectAccession() {
    return projectAccession;
  }

  /**
   * Gets id.
   *
   * @return Value of id.
   */
  public String getId() {
    return id;
  }

  /**
   * Gets resubmissionDates.
   *
   * @return Value of resubmissionDates.
   */
  public List<Date> getResubmissionDates() {
    return resubmissionDates;
  }

  /**
   * Sets new resubmissionDates.
   *
   * @param resubmissionDates New value of resubmissionDates.
   */
  public void setResubmissionDates(List<Date> resubmissionDates) {
    this.resubmissionDates = resubmissionDates;
  }
}
