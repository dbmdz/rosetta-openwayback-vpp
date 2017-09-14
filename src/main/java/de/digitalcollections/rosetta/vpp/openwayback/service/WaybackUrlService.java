package de.digitalcollections.rosetta.vpp.openwayback.service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Prepare data for OpenWayback.
 *
 */
public class WaybackUrlService {

  private static final SimpleDateFormat waybackDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

  String urlDateString(Date date) {
    return waybackDateFormat.format(date);
  }

  public String createDetailUrlPath(String seed, Date harvestDate) {
    return "/" + urlDateString(harvestDate) + "/" + seed;
  }

  public String createOverviewQueryString(String seed) {
    return "url=" + seed;
  }

}
