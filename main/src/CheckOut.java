package src;

import java.util.*;

public class CheckOut {
    public static double theTotalOfPurchasedPrice=0;
    private Map<String,Product> alltheProductsInStore = allTheProductsInStore();
    public static List<Product> allPuchasedProducts = new ArrayList<>();
    public double getItemPrice(String productName, double quantity) {
        Product onSaleProduct;
        if(alltheProductsInStore.get(productName).getBuyNItmsGetMAtXOff().length!=0){
            if(allPuchasedProducts.stream().filter(product -> product.getProductName().equals(productName)).count()%2!=0){
                onSaleProduct = new Product(productName,0,
                        0);
                allPuchasedProducts.add(onSaleProduct);
                return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
            }
        }
        double onSalePrice = alltheProductsInStore.get(productName).getProductPrice()
                                -alltheProductsInStore.get(productName).getMarkdown();
        onSalePrice = Math.round(onSalePrice*100.0)/100.0;
        theTotalOfPurchasedPrice += onSalePrice*quantity;
        onSaleProduct = new Product(productName,onSalePrice*quantity,
                                alltheProductsInStore.get(productName).getMarkdown()*quantity);
        allPuchasedProducts.add(onSaleProduct);
        return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
    }


    public double voidOneItem(String productName, double quantity) {
        double voidProdcutPrice = (alltheProductsInStore.get(productName).getProductPrice()
                                    -alltheProductsInStore.get(productName).getMarkdown())*quantity;
        Product voidProduct = allPuchasedProducts.stream()
                                                 .filter(product->product.getProductName().equals(productName))
                                                 .filter(product->product.getProductPrice()==voidProdcutPrice)
                                                 .findFirst().get();
        if(voidProduct!=null){
            theTotalOfPurchasedPrice -= voidProduct.getProductPrice();
            allPuchasedProducts.removeIf(product->product.getProductName().equals(productName)
                    &&product.getProductPrice()==voidProdcutPrice);
        }
        return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
    }


    public  Map<String,Product> allTheProductsInStore(){
        Map<String,Product> alltheProductsInStore = new HashMap<>();
        alltheProductsInStore.put("soup",new Product("soup", 2.56, 0.3));
        alltheProductsInStore.put("banana",new Product("banana",0.6,0));
        alltheProductsInStore.get("soup").setBuyNItmsGetMAtXOff(new int[]{1, 1, 1});
        return alltheProductsInStore;
    }
}
