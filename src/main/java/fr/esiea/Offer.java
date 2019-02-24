package fr.esiea;

public class Offer {
    private SpecialOffer offerType;
    private final Product product;
    private double argument;

    public Offer(SpecialOffer offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public SpecialOffer getSpecialOffer(){
        return this.offerType;
    }

    public double getArgument(){
        return this.argument;
    }

}
