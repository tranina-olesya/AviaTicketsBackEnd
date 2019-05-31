package ru.vsu.aviaticketsback;

import org.junit.Test;
import ru.vsu.aviaticketsback.ticketssearch.models.PriceLink;
import ru.vsu.aviaticketsback.ticketssearch.utils.TripUtils;
import ru.vsu.aviaticketsback.utils.DateConvert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void price_shouldFindMinPriceLink() {
        List<PriceLink> priceLinks = new ArrayList<>();
        priceLinks.add(new PriceLink(null, 900.0, null));
        priceLinks.add(new PriceLink(null, 1200.0, null));
        priceLinks.add(new PriceLink(null, 712.2, null));
        priceLinks.add(new PriceLink(null, 871.0, null));
        PriceLink minPriceLink = TripUtils.getMinPriceLink(priceLinks);
        assertEquals(minPriceLink, priceLinks.get(2));
    }

    @Test
    public void date_shouldConvertDateToString() {
        String expected = "28-12-2015";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 28);
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date = calendar.getTime();
        String actual = DateConvert.getStringFromDate(date);
        assertEquals(expected, actual);
    }

    @Test
    public void date_shouldConvertFromString() {
        Date expected = DateConvert.getDateFromRequest("15-10-2018");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date actual = calendar.getTime();
        assertEquals(expected, actual);
    }
}

