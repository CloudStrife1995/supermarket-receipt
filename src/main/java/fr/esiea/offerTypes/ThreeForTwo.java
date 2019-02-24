package fr.esiea.offerTypes;
import fr.esiea.Product;
import fr.esiea.Discount;

public class ThreeForTwo extends SpecialOffer {

	public Discount getDiscount(Double unitPrice, Double quantityBought, Product product)
	{
		int quantityAsInt = quantityBought.intValue();
		Discount discount = null;
		int numberOfTimesOfferIsApplied = quantityAsInt / 3;
		if (quantityAsInt > 2) {
			double discountAmount = quantityBought * unitPrice - ((numberOfTimesOfferIsApplied * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
			discount = new Discount(product, "3 for 2", discountAmount);
		}
		return discount;
	}

}