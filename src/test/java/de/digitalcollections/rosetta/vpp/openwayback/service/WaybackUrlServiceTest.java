package de.digitalcollections.rosetta.vpp.openwayback.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class WaybackUrlServiceTest {

  private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  private WaybackUrlService service;

  @Before
  public void setUp() {
    service = new WaybackUrlService();
  }

  @Test
  public void urlDateStringShouldFormatDate() throws ParseException {
    Date date = FORMAT.parse("01/08/2012 23:07:05");
    assertThat(service.urlDateString(date), is("20120801230705"));
  }

  @Test
  public void createDetailUrlPathShouldReturnValidPath() throws ParseException, UnsupportedEncodingException {
    Date date = FORMAT.parse("01/08/2012 23:07:05");
    assertThat(service.createDetailUrlPath("http://example.com", date), is("%2F20120801230705%2Fhttp%3A%2F%2Fexample.com"));
  }

  @Test
  public void createOverviewQueryStringShouldReturnValidQueryString() throws UnsupportedEncodingException {
    assertThat(service.createOverviewQueryString("http://example.com"), is("url=http%3A%2F%2Fexample.com"));
  }

}
