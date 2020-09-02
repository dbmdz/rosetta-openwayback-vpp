package de.digitalcollections.rosetta.vpp.openwayback.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WaybackUrlServiceTest {

  private static final DateTimeFormatter FORMAT =
      DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  private WaybackUrlService service;

  @BeforeEach
  public void setUp() {
    service = new WaybackUrlService();
  }

  @Test
  public void urlDateStringShouldFormatDate() throws ParseException {
    LocalDateTime date = LocalDateTime.parse("01/08/2012 23:07:05", FORMAT);
    assertThat(service.urlDateString(date)).isEqualTo("20120801230705");
  }

  @Test
  public void createDetailUrlPathShouldReturnValidPath()
      throws ParseException, UnsupportedEncodingException {
    LocalDateTime date = LocalDateTime.parse("01/08/2012 23:07:05", FORMAT);
    assertThat(service.createDetailUrlPath("http://example.com", date))
        .isEqualTo("/20120801230705/http://example.com");
  }

  @Test
  public void createOverviewQueryStringShouldReturnValidQueryString()
      throws UnsupportedEncodingException {
    assertThat(service.createOverviewQueryString("http://example.com"))
        .isEqualTo("url=http%3A%2F%2Fexample.com");
  }
}
