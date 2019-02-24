package fr.esiea.discountsTypes;
import fr.esiea.Product;
import fr.esiea.Discount;

public class FiveForAmount extends SpecialOffer {

	Double discountedPriceFor5Products;

	public FiveForAmount(Double discountedPriceFor5Products)
	{
		this.discountedPriceFor5Products = discountedPriceFor5Products;
	}


	public Discount getDiscount(Double unitPrice, Double quantityBought, Product product)
	{
		int quantityAsInt = quantityBought.intValue();
		Discount discount = null;

		int numberOfXs = quantityAsInt / 5;

		if (quantityAsInt >= 5) {
			double discountTotal = unitPrice * quantityBought - (discountedPriceFor5Products * numberOfXs + quantityAsInt % 5 * unitPrice);
			discount = new Discount(product, 5 + " for " + discountedPriceFor5Products, discountTotal);
		}
		return discount;
	}

}