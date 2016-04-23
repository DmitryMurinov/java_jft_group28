package learn.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * Created by Dima on 23.04.2016.
 */
public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("188.123.230.218");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

    @Test
    public void testWrongIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("188.123.230.xxx");
        assertNotEquals(geoIP.getCountryCode(), "RUS");
        assertEquals(geoIP.getReturnCodeDetails(), "Invalid IP address");
    }
}
