package de.digitalcollections.rosetta.vpp.openwayback.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MetadataServiceTest {

  private static final DateTimeFormatter FORMAT =
      DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  private MetadataService metadataService;

  @BeforeEach
  public void setUp() {
    metadataService = new MetadataService();
  }

  @Test
  public void parseHarvestDateShouldParseDate() throws ParseException {
    LocalDateTime date = LocalDateTime.of(2018, Month.APRIL, 15, 23, 10, 05);
    assertThat(metadataService.parseHarvestDate(FORMAT.format(date)).toString())
        .isEqualTo(date.toString());
  }
}
