package de.digitalcollections.rosetta.vpp.openwayback;

import com.exlibris.digitool.common.dnx.DnxDocumentHelper;
import com.exlibris.dps.sdk.delivery.AbstractViewerPreProcessor;
import de.digitalcollections.rosetta.vpp.openwayback.service.MetadataService;
import de.digitalcollections.rosetta.vpp.openwayback.service.WaybackUrlService;
import java.text.ParseException;
import java.util.Map;


public class OpenWaybackVpp extends AbstractViewerPreProcessor {

  private static final String DETAIL_KEY = "detail";

  private static final String DEFAULT_MARKER = "@";

  private final WaybackUrlService waybackUrlService;

  private final MetadataService metadataService;

  private String additionalParameters;

  public OpenWaybackVpp() {
    this.waybackUrlService = new WaybackUrlService();
    this.metadataService = new MetadataService();
  }

  @Override
  public void execute() throws ParseException {
    execute(new DnxDocumentHelper(getDnx()), getViewContext());
  }

  void execute(DnxDocumentHelper documentHelper, Map<String, String> viewContext) throws ParseException {
    if (hasRequestedDetail(viewContext)) {
      additionalParameters = createUrlPath(documentHelper.getWebHarvesting(), viewContext);
    }
    else {
      additionalParameters = createOverviewQuery(documentHelper.getWebHarvesting());
    }
  }

  String createUrlPath(DnxDocumentHelper.WebHarvesting webHarvesting, Map<String, String> viewContext) throws ParseException {
    String marker = getMarker(viewContext);
    return marker +
        waybackUrlService.createDetailUrlPath(webHarvesting.getPrimarySeedURL(), metadataService.parseHarvestDate(webHarvesting.getHarvestDate())) +
        marker;
  }

  @Override
  public String getAdditionalParameters() {
    return additionalParameters;
  }

  String getMarker(Map<String, String> viewContext) {
    return viewContext.getOrDefault("marker", DEFAULT_MARKER);
  }

  boolean hasRequestedDetail(Map<String, String> viewContext) {
    return viewContext.containsKey(DETAIL_KEY) && "true".equals(viewContext.get(DETAIL_KEY));
  }

  String createOverviewQuery(DnxDocumentHelper.WebHarvesting webHarvesting) {
    return waybackUrlService.createOverviewQueryString(webHarvesting.getPrimarySeedURL());
  }

}
