package de.digitalcollections.rosetta.vpp.openwayback.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MetadataService {

  private static final DateTimeFormatter HARVEST_DATE_FORMAT =
      DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  public LocalDateTime parseHarvestDate(String source) throws ParseException {
    return LocalDateTime.parse(source, HARVEST_DATE_FORMAT);
  }
}
