package fr.esiea;

public interface SupermarketCatalog {
	// add a product in the catalog
    void addProduct(Product product, double price);
    // remove a product rom the catalog by his name
    void deleteProduct(String name);
    // list of products into the catalog
    Map<String, Product> getListOfProducts();
    // list of prices of products into the catalog
    Map<String, Product> getListOfProductsPrices();
    
    // give the price by Unit of the product (in kilo or per each )
    double getUnitPrice(Product product);

}
