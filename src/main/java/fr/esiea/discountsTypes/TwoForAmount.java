package fr.esiea.discountsTypes;
import fr.esiea.Product;
import fr.esiea.Discount;

public class TwoForAmount extends SpecialOffer {

	Double discountedPriceFor2Products;

	public TwoForAmount(Double discountedPriceFor2Products)
	{
		this.discountedPriceFor2Products= discountedPriceFor2Products;
	}


	public Discount getDiscount(Double unitPrice, Double quantityBought, Product product)
	{
		int quantityAsInt = quantityBought.intValue();
		Discount discount = null;
		if (quantityAsInt >= 2) {
			double total = discountedPriceFor2Products * quantityAsInt / 2 + quantityAsInt % 2 * unitPrice;
			double discountN = unitPrice * quantityBought - total;
			discount = new Discount(product, "2 for " + discountedPriceFor2Products, discountN);
		}
		return discount;
	}

}