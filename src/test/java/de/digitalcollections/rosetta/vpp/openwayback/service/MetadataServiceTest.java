package de.digitalcollections.rosetta.vpp.openwayback.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class MetadataServiceTest {

  private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  private MetadataService metadataService;

  @Before
  public void setUp() {
    metadataService = new MetadataService();
  }

  @Test
  public void parseHarvestDateShouldParseDate() throws ParseException {
    Calendar  calendar = new GregorianCalendar();
    calendar.set(2012, 10, 1, 23, 11, 5);
    Date date = calendar.getTime();
    assertThat(metadataService.parseHarvestDate(FORMAT.format(date)).toString(), is(date.toString()));
  }

}
