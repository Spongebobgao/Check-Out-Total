package src;

import java.util.*;

public class CheckOut {
    public static double theTotalOfPurchasedPrice=0;
    private Map<String,Product> allTheProductsInStore = allTheProductsInStore();
    public static List<Product> allPurchasedProducts = new ArrayList<>();

    public double getItemPrice(String productName, double quantity) {
        Product onSaleProduct;
        int[] buyNItemsGetMAtXOff = allTheProductsInStore.get(productName).getBuyNItemsGetMAtXOff();
        long alreadyPurchasedSameItemCount = allPurchasedProducts.stream().filter(product -> product.getProductName().equals(productName)).count();
        //if it's on sale will get the info for N M X (Buy N Get M at X% off)
        if(buyNItemsGetMAtXOff != null){
            int numberOfOriginalPrice = buyNItemsGetMAtXOff[0];
            int numberOfSalePrice = buyNItemsGetMAtXOff[1];
            int percentOfDiscount = buyNItemsGetMAtXOff[2];
            //if this is true, will get the original price
            if(alreadyPurchasedSameItemCount<numberOfOriginalPrice
                    ||(alreadyPurchasedSameItemCount % (numberOfOriginalPrice+numberOfSalePrice)>=0
                        && alreadyPurchasedSameItemCount % (numberOfOriginalPrice+numberOfSalePrice) <numberOfOriginalPrice)){
                //maybe the item has markdown as well as buy N get M at X off
                return getThePriceAfterApplyTheMarkdown(productName, quantity, allTheProductsInStore);
            }
            else{
                double onSalePrice = (allTheProductsInStore.get(productName).getProductPrice()
                        - allTheProductsInStore.get(productName).getMarkdown())*50/100;
                onSaleProduct = new Product(productName,onSalePrice*quantity, allTheProductsInStore.get(productName).getMarkdown()*quantity);
                allPurchasedProducts.add(onSaleProduct);
                theTotalOfPurchasedPrice += onSalePrice*quantity;
                return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
            }
        }
        int[] buyNForM = allTheProductsInStore.get(productName).getBuyNForM();
        //if this is true, the product is on sale in the form of buy N for M
        if(buyNForM != null){
            int numberOfNeededItems = buyNForM[0];
            int priceForNItems=buyNForM[1];
            //this if statement means the number of already purchased same items is not qualified for the sale price
            //because alreadyPurchasedSameItemCount starts from 0, so, add 1 to make the condition works as expected
            if( alreadyPurchasedSameItemCount+1<numberOfNeededItems || ((alreadyPurchasedSameItemCount+1) % numberOfNeededItems !=0 )){
                return getThePriceAfterApplyTheMarkdown(productName, quantity, allTheProductsInStore);
            }
            //else will reset each item price to M/N
            else {
                double originalPrice = allTheProductsInStore.get(productName).getProductPrice()
                        - allTheProductsInStore.get(productName).getMarkdown();
                double differenceAfterApplyTheBuyNForM = originalPrice * numberOfNeededItems - priceForNItems;
                onSaleProduct = new Product(productName, originalPrice, allTheProductsInStore.get(productName).getMarkdown());
                allPurchasedProducts.add(onSaleProduct);
                allPurchasedProducts.stream().filter(product -> product.getProductName().equals(productName))
                        .forEach(product -> product.setProductPrice(priceForNItems / numberOfNeededItems));
                theTotalOfPurchasedPrice += differenceAfterApplyTheBuyNForM;
                return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
            }
        }
        int [] buyNGetMFreeLimitX = allTheProductsInStore.get(productName).getBuyNGetMFreeLimitX();
        //check if the buy n get m free limit x available or not
        if(buyNGetMFreeLimitX!=null){
            int numberOfItemsNeedToBuy = buyNGetMFreeLimitX[0];
            int numberOfFreeItems = buyNGetMFreeLimitX[1];
            int limitNumberOfItems = buyNGetMFreeLimitX[2];
            if(alreadyPurchasedSameItemCount<limitNumberOfItems) {
                if((alreadyPurchasedSameItemCount+1) % (numberOfItemsNeedToBuy+numberOfFreeItems)==0) {
                    onSaleProduct = new Product(productName, 0, allTheProductsInStore.get(productName).getMarkdown());
                    allPurchasedProducts.add(onSaleProduct);
                    return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
                }
            }
        }
        return getThePriceAfterApplyTheMarkdown(productName, quantity, allTheProductsInStore);
    }

    private static double getThePriceAfterApplyTheMarkdown(String productName, double quantity, Map<String, Product> allTheProductsInStore) {
        Product onSaleProduct;
        double onSalePrice = allTheProductsInStore.get(productName).getProductPrice()
                - allTheProductsInStore.get(productName).getMarkdown();
        onSaleProduct = new Product(productName,onSalePrice*quantity, allTheProductsInStore.get(productName).getMarkdown()*quantity);
        allPurchasedProducts.add(onSaleProduct);
        theTotalOfPurchasedPrice += onSalePrice*quantity;
        return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
    }


    public double voidOneItem(String productName, double quantity) {
        double voidProductPrice = (allTheProductsInStore.get(productName).getProductPrice()
                                    - allTheProductsInStore.get(productName).getMarkdown())*quantity;
        Product voidProduct = allPurchasedProducts.stream()
                                                 .filter(product->product.getProductName().equals(productName))
                                                 .filter(product->product.getProductPrice()==voidProductPrice)
                                                 .findFirst().get();
        if(voidProduct!=null){
            theTotalOfPurchasedPrice -= voidProduct.getProductPrice();
            allPurchasedProducts.removeIf(product->product.getProductName().equals(productName)
                    &&product.getProductPrice()==voidProductPrice);
        }
        return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
    }


    public  Map<String,Product> allTheProductsInStore(){
        Map<String,Product> allTheProductsInStore = new HashMap<>();
        allTheProductsInStore.put("soup",new Product("soup", 2.56, 0.3));
        allTheProductsInStore.put("banana",new Product("banana",0.6,0));
        allTheProductsInStore.put("milk",new Product("milk",4.2,0));
        allTheProductsInStore.put("potato",new Product("potato",1.6,0));
        allTheProductsInStore.put("pasta",new Product("pasta",1.2,0));
        allTheProductsInStore.get("soup").setBuyNItemsGetMAtXOff(new int[]{1, 1, 100});
        //allTheProductsInStore.get("pasta").setBuyNItemsGetMAtXOff(new int[]{4, 2, 50});
        allTheProductsInStore.get("pasta").setBuyNForM(new int[]{3,3});
        allTheProductsInStore.get("milk").setBuyNGetMFreeLimitX(new int[]{2,1,6});
        return allTheProductsInStore;
    }
}
