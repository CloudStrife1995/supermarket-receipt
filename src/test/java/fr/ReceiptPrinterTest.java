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

        // We create a prodcut "necklace " without  special offer. 
        Product necklace = new Product("necklace", ProductUnit.Each);
        catalog.addProduct(necklace, 20);

       // In our current cart we have :
        ShoppingCart cart = new ShoppingCart();  
        cart.addItemQuantity(artefact, 2);      
        cart.addItemQuantity(BioWatch, 3);
        cart.addItemQuantity(necklace, 1); 
        
        // SupposedCartPrice is equal to 55€
        double SupposedCartPrice = 1*20+ +2*10 +3*5; 

        // RealCartPrice is equal to 40€ 
        double RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();
        
         // we check the cart 
      Receipt receipt_announced = teller.checksOutArticlesFrom(cart);
      ReceiptPrinter receipt_printed = new ReceiptPrinter();   

        //Test the ReceiptPrinter ticket is not blank.
      assertThat(receipt_printed.printReceipt(receipt_announced)).isNotBlank();


        // there we are the printed receipt give to the client.
        String printed_ticket = 
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

        assertThat(printed_ticket).isEqualTo(receipt_printed.printReceipt(receipt_announced));

    }
}
