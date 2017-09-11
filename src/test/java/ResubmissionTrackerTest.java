import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.ac.ebi.pride.resubmissiontracker.model.Resubmission;
import uk.ac.ebi.pride.resubmissiontracker.service.ResubmissionKeepService;
import uk.ac.ebi.pride.resubmissiontracker.service.ResubmissionSearchService;

import javax.annotation.Resource;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mongo-test-context.xml")
public class ResubmissionTrackerTest {
  private static Logger logger = LoggerFactory.getLogger(ResubmissionTrackerTest.class);

  private static final String PXD000001 = "PXD000001";
  private static final String PXD000002 = "PXD000002";
  @Resource
  private ResubmissionKeepService resubmissionKeepService;

  @Resource
  private ResubmissionSearchService resubmissionSearchService;

  /**
   * To start, delete any existing test data, and inserts new test data.
   * @throws Exception any problems setting up test data.
   */
  @Before
  public void setUp() throws Exception {
    deleteAllData();
    insertTestData();
  }

  /**
   * Finally tidy up and delete all test data.
   * @throws Exception any problems deleting the test data.
   */
  @After
  public void tearDown() throws Exception {
    deleteAllData();
  }

  /**
   * Deletes all test data from the test database.
   */
  private void deleteAllData() {
    resubmissionKeepService.deleteAll();
  }

  /**
   * Inserts test data ino a test database.
   */
  private void insertTestData() {
    Resubmission testData1 = new Resubmission();
    testData1.setProjectAccession(PXD000001);
    List<Date> testData1Dates = new ArrayList<>();
    testData1Dates.add(Date.from(Instant.now()));
    testData1.setResubmissionDates(testData1Dates);
    resubmissionKeepService.save(testData1);
    Resubmission testData2 = new Resubmission();
    testData2.setProjectAccession(PXD000002);
    List<Date> testData2Dates = new ArrayList<>();
    testData2Dates.add(Date.from(Instant.now()));
    testData2.setResubmissionDates(testData2Dates);
    resubmissionKeepService.save(testData2);
    LocalDate past = LocalDate.of(2010, 1, 14);
    testData2Dates.add(Date.from(past.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    resubmissionKeepService.save(testData2);
    logger.info("Successfully tested inserting data");
  }

  /**
   * Tests searching the collection via IDs.
   * @param ids input document IDs to search on.
   */
  private void testSearchById(List<String> ids) {
    assertNotNull(ids);
    assertTrue(ids.size()>0);
    for (String id : ids) {
      Resubmission resubmission = resubmissionSearchService.findById(id);
      assertNotNull(resubmission);
      assertEquals(resubmission.getId(), id);
    }
    List<Resubmission> resubmissions = resubmissionSearchService.findByIdIn(ids);
    assertNotNull(resubmissions);
    logger.info("Successfully tested searching by document IDs.");
  }

  /**
   * Tests searching the collection via project accessions.
   */
  @Test
  public void testSearchByProjectAccession() {
    List<Resubmission> resubmissions = resubmissionSearchService.findByProjectAccession(PXD000001);
    assertEquals(resubmissions.size(), 1);
    Resubmission resubmission1 = resubmissions.get(0);
    assertNotNull(resubmission1);
    assertEquals(resubmission1.getProjectAccession(), PXD000001);
    resubmissions = resubmissionSearchService.findByProjectAccession(PXD000002);
    assertEquals(resubmissions.size(), 1);
    Resubmission resubmission2 = resubmissions.get(0);
    assertNotNull(resubmission2);
    assertEquals(resubmission2.getProjectAccession(), PXD000002);
    logger.info("Successfully tested searching by project accession.");
    List<String> idsToSerch = new ArrayList<>();
    idsToSerch.add(resubmission1.getId());
    idsToSerch.add(resubmission2.getId());
    testSearchById(idsToSerch);
  }
}
