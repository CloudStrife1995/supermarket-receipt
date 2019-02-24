package fr.esiea;

public class FiveForAmount extends SpecialOffer {

	public Discount getDiscount(Double unitPrice, Double quantityBought, Product product, Double argument)
	{
		int quantityAsInt = quantityBought.intValue();
		Discount discount = null;

		int numberOfXs = quantityAsInt / 5;

		if (quantityAsInt >= 5) {
			double discountTotal = unitPrice * quantityBought - (argument * numberOfXs + quantityAsInt % 5 * unitPrice);
			discount = new Discount(product, 5 + " for " + argument, discountTotal);
		}
		return discount;
	}

}