package fr.esiea;
import fr.esiea.offerTypes.*;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OfferTest 
{

@Test  
    public void getProductOffer_Test() 
    {
         Product Umbrella= new Product("Umbrella", ProductUnit.Each);
         Offer Umbrella_offer = new Offer(new ThreeForTwo(), Umbrella );
         
         // test of the method getProduct
         assertThat(Umbrella_offer.getProduct()).isEqualTo(Umbrella);

    }
}