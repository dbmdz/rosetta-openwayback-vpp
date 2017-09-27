package de.digitalcollections.rosetta.vpp.openwayback.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

  public String createDetailUrlPath(String seed, Date harvestDate) throws UnsupportedEncodingException {
    return encode("/" + urlDateString(harvestDate) + "/" + seed);
  }

  public String createOverviewQueryString(String seed) throws UnsupportedEncodingException {
    return "url=" + encode(seed);
  }

  private String encode(String value) throws UnsupportedEncodingException {
    return URLEncoder.encode(value, "UTF-8");
  }

}
