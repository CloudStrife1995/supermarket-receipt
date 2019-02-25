package fr.esiea;
import java.util.Map;

public class OffersHandler {

	Receipt getAppliableDiscountsFromOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog, Map<Product, Double> productsInCart) {
        for (Product p: productsInCart.keySet()) {
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                Discount discount = null;
				discount=offer.getSpecialOffer().getDiscount(catalog.getUnitPrice(p), productsInCart.get(p), p);
                    receipt.addDiscount(discount);
            }

        }

        return receipt;
    }



}