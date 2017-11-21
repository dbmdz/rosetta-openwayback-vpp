package de.digitalcollections.rosetta.vpp.openwayback;

import com.exlibris.digitool.common.dnx.DnxDocumentHelper;
import com.exlibris.dps.sdk.access.AccessException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class OpenWaybackVppTest {

  private OpenWaybackVpp vpp;

  private DnxDocumentHelper.WebHarvesting webHarvesting;

  @BeforeEach
  public void setUp() throws AccessException {
    vpp = new OpenWaybackVpp();
    webHarvesting = mock(DnxDocumentHelper.WebHarvesting.class);
    when(webHarvesting.getHarvestDate()).thenReturn("12/03/2014 13:57:04");
    when(webHarvesting.getPrimarySeedURL()).thenReturn("http://wwww.bahn.de");
  }

  @Test
  public void createUrlPathResultShouldStartAndEndWithMarkers() throws ParseException, UnsupportedEncodingException {
    String marker = vpp.getMarker(emptyMap());
    String path = vpp.createUrlPath(webHarvesting, emptyMap());
    assertThat(path)
        .startsWith(marker)
        .endsWith(marker);
  }

  @Test
  public void createUrlPathResultShouldContainUrlPath() throws ParseException, UnsupportedEncodingException {
    String path = vpp.createUrlPath(webHarvesting, emptyMap());
    assertThat(path).contains("%2F20140312135704%2Fhttp%3A%2F%2Fwwww.bahn.de");
  }

  @Test
  public void overviewQueryShouldSetQuery() throws UnsupportedEncodingException {
    String query = vpp.createOverviewQuery(webHarvesting);
    assertThat(query).isEqualTo("url=http%3A%2F%2Fwwww.bahn.de");
  }
  @Test
  public void hasRequestedDetailShouldReturnTrueIfDetailIsRequested() {
    Map<String, String> viewContext = new HashMap<>();
    viewContext.put("detail", "true");
    assertThat(vpp.hasRequestedDetail(viewContext)).isTrue();
  }

  @Test
  public void hasRequestedDetailShouldReturnFalseIfDetailIsNotRequested() {
    Map<String, String> viewContext = new HashMap<>();
    viewContext.put("detail", "false");
    assertThat(vpp.hasRequestedDetail(viewContext)).isFalse();
  }

  @Test
  public void hasRequestedDetailShouldReturnFalseIfDetailIsNotSpecified() {
    assertThat(vpp.hasRequestedDetail(emptyMap())).isFalse();
  }

  @Test
  public void executeShouldRunDetailIfRequested() throws ParseException, UnsupportedEncodingException {
    DnxDocumentHelper documentHelper = mock(DnxDocumentHelper.class);
    when(documentHelper.getWebHarvesting()).thenReturn(webHarvesting);

    Map<String, String> viewContext = new HashMap<>();
    viewContext.put("detail", "true");
    vpp.execute(documentHelper, viewContext);
    assertThat(vpp.getAdditionalParameters()).contains("20140312135704");
  }

  @Test
  public void executeShouldRunOverviewIfDetailIsNotRequested() throws ParseException, UnsupportedEncodingException {
    DnxDocumentHelper documentHelper = mock(DnxDocumentHelper.class);
    when(documentHelper.getWebHarvesting()).thenReturn(webHarvesting);
    vpp.execute(documentHelper, emptyMap());
    assertThat(vpp.getAdditionalParameters()).contains("url");
  }

  @Test
  public void getMarkerShouldBeConfigurable() {
    Map<String, String> viewContext = new HashMap<>();
    viewContext.put("marker", "ABC");
    assertThat(vpp.getMarker(viewContext)).isEqualTo("ABC");
  }

  @Test
  public void getMarkerShouldHaveDefaultValue() {
    assertThat(vpp.getMarker(emptyMap())).isEqualTo("@");
  }

}
