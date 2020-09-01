package de.digitalcollections.rosetta.vpp.openwayback.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Prepare data for OpenWayback. */
public class WaybackUrlService {

  private static final DateTimeFormatter WAYBACK_DATE_FORMAT =
      DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  String urlDateString(LocalDateTime date) {
    return date.format(WAYBACK_DATE_FORMAT);
  }

  public String createDetailUrlPath(String seed, LocalDateTime harvestDate) {
    return "/" + urlDateString(harvestDate) + "/" + seed;
  }

  public String createOverviewQueryString(String seed) throws UnsupportedEncodingException {
    return "url=" + encode(seed);
  }

  private String encode(String value) throws UnsupportedEncodingException {
    return URLEncoder.encode(value, "UTF-8");
  }
}
