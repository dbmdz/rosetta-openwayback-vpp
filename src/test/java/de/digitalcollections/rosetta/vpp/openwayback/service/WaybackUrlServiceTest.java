package de.digitalcollections.rosetta.vpp.openwayback.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class WaybackUrlServiceTest {

  private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  private WaybackUrlService service;

  @BeforeEach
  public void setUp() {
    service = new WaybackUrlService();
  }

  @Test
  public void urlDateStringShouldFormatDate() throws ParseException {
    Date date = FORMAT.parse("01/08/2012 23:07:05");
    assertThat(service.urlDateString(date)).isEqualTo("20120801230705");
  }

  @Test
  public void createDetailUrlPathShouldReturnValidPath() throws ParseException, UnsupportedEncodingException {
    Date date = FORMAT.parse("01/08/2012 23:07:05");
    assertThat(service.createDetailUrlPath("http://example.com", date)).isEqualTo("%2F20120801230705%2Fhttp%3A%2F%2Fexample.com");
  }

  @Test
  public void createOverviewQueryStringShouldReturnValidQueryString() throws UnsupportedEncodingException {
    assertThat(service.createOverviewQueryString("http://example.com")).isEqualTo("url=http%3A%2F%2Fexample.com");
  }

}
