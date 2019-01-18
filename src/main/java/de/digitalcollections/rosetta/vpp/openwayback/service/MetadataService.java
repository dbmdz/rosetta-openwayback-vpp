package de.digitalcollections.rosetta.vpp.openwayback.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MetadataService {

  private static final SimpleDateFormat HARVEST_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  public Date parseHarvestDate(String source) throws ParseException {
    return HARVEST_DATE_FORMAT.parse(source);
  }

}
