package fr.esiea;

public class TwoForAmount extends SpecialOffer {


	public Discount getDiscount(Double unitPrice, Double quantityBought, Product product, Double argument)
	{
		int quantityAsInt = quantityBought.intValue();
		Discount discount = null;
		if (quantityAsInt >= 2) {
			double total = argument * quantityAsInt / 2 + quantityAsInt % 2 * unitPrice;
			double discountN = unitPrice * quantityBought - total;
			discount = new Discount(product, "2 for " + argument, discountN);
		}
		return discount;
	}

}