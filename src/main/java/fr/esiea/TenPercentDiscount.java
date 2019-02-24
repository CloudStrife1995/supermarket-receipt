package fr.esiea;

public class TenPercentDiscount extends SpecialOffer {


	public Discount getDiscount(Double unitPrice, Double quantityBought, Product product, Double argument)
	{
		Discount discount = null;
		discount = new Discount(product, argument + "% off", quantityBought * unitPrice * argument / 100.0);
		return discount;
	}

}