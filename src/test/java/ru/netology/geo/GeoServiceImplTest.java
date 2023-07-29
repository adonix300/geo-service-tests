package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    @Test
    void testGeoServiceByIp() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp("172.123.12.19");
        assertEquals(Country.RUSSIA, location.getCountry());
    }
}