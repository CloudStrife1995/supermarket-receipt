package fr.esiea;
import fr.esiea.discountsTypes.SpecialOffer;

public class Offer {
    private SpecialOffer offerType;
    private final Product product;

    public Offer(SpecialOffer offerType, Product product) {
        this.offerType = offerType;
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public SpecialOffer getSpecialOffer(){
        return this.offerType;
    }
}
