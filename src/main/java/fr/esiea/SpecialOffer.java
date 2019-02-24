package fr.esiea;

public abstract class SpecialOffer
{
    public abstract Discount getDiscount(Double unitPrice, Double quantityBought,  Product product, Double argument);
}