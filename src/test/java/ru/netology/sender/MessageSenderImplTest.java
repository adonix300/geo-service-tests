package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {
    @Test
    void shouldReturnRussianMessagesOnRussianIP() {
        //given
        String ip = "172.123.12.19";
        String excepted = "Добро пожаловать";

        var geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp(ip)).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        var localizationServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn(excepted);

        var messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        //when
        String result = messageSender.send(headers);

        //then
        assertEquals(excepted, result);
    }

    @Test
    void shouldReturnEnglishMessagesOnUsaIP() {
        //given
        String ip = "96.44.183.149";
        String excepted = "Welcome";

        var geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp(ip)).thenReturn(new Location("New York", Country.USA, null, 0));

        var localizationServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceMock.locale(Country.USA)).thenReturn(excepted);

        var messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        //when
        String result = messageSender.send(headers);

        //then
        assertEquals(excepted, result);
    }

}