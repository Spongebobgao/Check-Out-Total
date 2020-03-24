package src;

import java.util.*;

public class CheckOut {
    public static double theTotalOfPurchasedPrice=0;
    private Map<String,Product> alltheProductsInStore = allTheProductsInStore();
    public static List<Product> allPuchasedProducts = new ArrayList<>();

    public double getItemPrice(String productName, double quantity) {
        Product onSaleProduct;
        int[] salesInfo = alltheProductsInStore.get(productName).getBuyNItmsGetMAtXOff();
        //if it's on sale will get the info for N M X (Buy N Get M at X% off)
        if(salesInfo.length!=0){
            int numberOfOriginalPrice = salesInfo[0];
            int numberOfSalePrice = salesInfo[1];
            int percentOfDiscount = salesInfo[2];
            long alreadyPuchasedSameItemCount = allPuchasedProducts.stream().filter(product -> product.getProductName().equals(productName)).count();
            //if this is true, will get the original price
            if(alreadyPuchasedSameItemCount<numberOfOriginalPrice
                    ||(alreadyPuchasedSameItemCount % (numberOfOriginalPrice+numberOfSalePrice)>=0
                        && alreadyPuchasedSameItemCount % (numberOfOriginalPrice+numberOfSalePrice) <numberOfOriginalPrice)){
                //maybe the item has markdown as well as buy N get M at X off
                double originalPrice = alltheProductsInStore.get(productName).getProductPrice()
                        -alltheProductsInStore.get(productName).getMarkdown();
                onSaleProduct = new Product(productName,originalPrice*quantity,alltheProductsInStore.get(productName).getMarkdown()*quantity);
                allPuchasedProducts.add(onSaleProduct);
                theTotalOfPurchasedPrice += originalPrice*quantity;
                return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
            }
            else{
                double onSalePrice = (alltheProductsInStore.get(productName).getProductPrice()
                        -alltheProductsInStore.get(productName).getMarkdown())*50/100;
                onSaleProduct = new Product(productName,onSalePrice*quantity,alltheProductsInStore.get(productName).getMarkdown()*quantity);
                allPuchasedProducts.add(onSaleProduct);
                theTotalOfPurchasedPrice += onSalePrice*quantity;
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
        alltheProductsInStore.put("pasta",new Product("pasta",1.2,0));
        alltheProductsInStore.get("soup").setBuyNItmsGetMAtXOff(new int[]{1, 1, 100});
        alltheProductsInStore.get("pasta").setBuyNItmsGetMAtXOff(new int[]{4, 2, 50});
        return alltheProductsInStore;
    }
}
