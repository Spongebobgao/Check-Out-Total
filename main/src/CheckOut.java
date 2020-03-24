package src;

import java.util.HashMap;
import java.util.Map;

public class CheckOut {
    public static double theTotalOfPurchasedPrice=0;
    private Map<String,Product> alltheProductsInStore = allTheProductsInStore();
    public double getItemPrice(String productName, double quantity) {
        double onSalePrice = alltheProductsInStore.get(productName).getProductPrice()-alltheProductsInStore.get(productName).getMarkdown();
        theTotalOfPurchasedPrice += onSalePrice*quantity;
        return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
    }




    public  Map<String,Product> allTheProductsInStore(){
        Map<String,Product> alltheProductsInStore = new HashMap<>();
        alltheProductsInStore.put("soup",new Product("soup", 2.56, 0.3));
        alltheProductsInStore.put("banana",new Product("banana",0.6,0));
        return alltheProductsInStore;
    }
}
