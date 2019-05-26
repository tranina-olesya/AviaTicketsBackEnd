package ru.vsu.aviaticketsback.ticketssearch.utils;

import ru.vsu.aviaticketsback.ticketssearch.models.PriceLink;

import java.util.List;

public class TripUtils {
    public static PriceLink getMinPriceLink(List<PriceLink> priceLinks) {
        if (priceLinks == null || priceLinks.isEmpty())
            return null;
        PriceLink result = priceLinks.get(0);
        for (int i = 1; i < priceLinks.size(); i++) {
            if (priceLinks.get(i).getPrice() < result.getPrice()) {
                result = priceLinks.get(i);
            }
        }
        return result;
    }
}
