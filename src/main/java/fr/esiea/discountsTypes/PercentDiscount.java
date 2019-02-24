package fr.esiea.discountsTypes;
import fr.esiea.Product;
import fr.esiea.Discount;

public class PercentDiscount extends SpecialOffer {

	private Double discountPercentage;

	public PercentDiscount(Double discountPercentage)
	{
		this.discountPercentage =discountPercentage;
	}


	public Discount getDiscount(Double unitPrice, Double quantityBought, Product product)
	{
		Discount discount = null;
		discount = new Discount(product, discountPercentage + "% off", quantityBought * unitPrice * discountPercentage / 100.0);
		return discount;
	}

}