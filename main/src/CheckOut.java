package src;

import java.util.*;

public class CheckOut {
    public static double theTotalOfPurchasedPrice=0;
    private Map<String,Product> alltheProductsInStore = allTheProductsInStore();
    public static List<Product> allPuchasedProducts = new ArrayList<>();
    public double getItemPrice(String productName, double quantity) {
        double onSalePrice = alltheProductsInStore.get(productName).getProductPrice()-alltheProductsInStore.get(productName).getMarkdown();
        theTotalOfPurchasedPrice += onSalePrice*quantity;
        Product onSaleProduct = new Product(productName,onSalePrice*quantity,alltheProductsInStore.get(productName).getMarkdown()*quantity);
        allPuchasedProducts.add(onSaleProduct);
        return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
    }




    public  Map<String,Product> allTheProductsInStore(){
        Map<String,Product> alltheProductsInStore = new HashMap<>();
        alltheProductsInStore.put("soup",new Product("soup", 2.56, 0.3));
        alltheProductsInStore.put("banana",new Product("banana",0.6,0));
        return alltheProductsInStore;
    }

    public double voidOneItem(String productName) {
        Product voidProduct = allPuchasedProducts.stream().filter(product->product.getProductName().equals(productName))
                .findFirst().get();
        theTotalOfPurchasedPrice -= voidProduct.getProductPrice();
        allPuchasedProducts.remove(productName);
        return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
    }
}
