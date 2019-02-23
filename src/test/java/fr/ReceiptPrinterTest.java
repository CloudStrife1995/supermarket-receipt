package fr;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import fr.esiea.*;

public class ReceiptPrinterTest 
{

@Test  
    public void ReceiptPrinter_methods_Test() 
    {
   
        
        SupermarketCatalog catalog = new FakeCatalog();
        Teller teller = new Teller(catalog);

        // We create a prodcut " BioWatch " with a special offer of the type "ThreeForTwo".
        Product BioWatch = new Product("BioWatch", ProductUnit.Each);
        catalog.addProduct(BioWatch, 5.0);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, BioWatch,0);
    

         // We create a prodcut " artefact " with a special offer of the type "TwoForAmount".
        Product artefact = new Product("artefact", ProductUnit.Each);
        catalog.addProduct(artefact, 10);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, artefact,5.00*2);

        // We create a prodcut "necklace" without  special offer. 
        Product necklace = new Product("necklace", ProductUnit.Each);
        catalog.addProduct(necklace, 20);

       // We put the created product in a cart
        ShoppingCart cart = new ShoppingCart();  
        cart.addItemQuantity(artefact, 2);      
        cart.addItemQuantity(BioWatch, 3);
        cart.addItemQuantity(necklace, 1); 
        
         // we get the printed receipt of the cart
      Receipt receipt_announced = teller.checksOutArticlesFrom(cart);
      ReceiptPrinter receipt_printed = new ReceiptPrinter(); 
      String printed_ticket = receipt_printed.printReceipt(receipt_announced);

        //Test the printed receipt is not blank.
      assertThat(printed_ticket).isNotBlank();


        // Thoses text must be in the printed receipt
        String artefactPrice = 
         "artefact                           20.00\n"
        +
        "  10.00 * 2\n";

        String bioWatchPrice = 
        "BioWatch                           15.00\n"
        +
        "  5.00 * 3\n";

        String necklacePrice = "necklace                           20.00\n";
        String bioWatchOffertDescrtiption = "3 for 2(BioWatch)                  -5.00\n";
        String artefactOffertDescrtiption = "2 for 10.0(artefact)              -10.00\n";
        String totalPrice ="Total:                             40.00";


        // We test if the printed ticket contains the text above
        assertThat(printed_ticket).containsOnlyOnce(artefactPrice);
        assertThat(printed_ticket).containsOnlyOnce(bioWatchPrice);
        assertThat(printed_ticket).containsOnlyOnce(necklacePrice);
        assertThat(printed_ticket).containsOnlyOnce(bioWatchOffertDescrtiption);
        assertThat(printed_ticket).containsOnlyOnce(artefactOffertDescrtiption);
        assertThat(printed_ticket).containsOnlyOnce(totalPrice);

        // We take one exemple of the printed suposed ticket to test the lenght of the receipt to make sure there isn't more than what we want
        String printed_ticket_exemple = 
                 "artefact                           20.00\n"
                +
                "  10.00 * 2\n"
                +
                "BioWatch                           15.00\n"
                +
                "  5.00 * 3\n"
                +
                "necklace                           20.00\n"
                +
                "3 for 2(BioWatch)                  -5.00\n"
                +
                "2 for 10.0(artefact)              -10.00\n"
                +
                "\n"
                +
                "Total:                             40.00";

        assertThat(printed_ticket).hasSameSizeAs(printed_ticket_exemple);

    }
}
