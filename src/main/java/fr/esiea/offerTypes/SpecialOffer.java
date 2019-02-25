package fr.esiea.offerTypes;
import fr.esiea.Product;
import fr.esiea.Discount;

public abstract class SpecialOffer
{
    public abstract Discount getDiscount(Double unitPrice, Double quantityBought,  Product product);
}