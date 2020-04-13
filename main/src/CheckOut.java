package src;

import java.util.*;

public class CheckOut {
    public static double theTotalOfPurchasedPrice=0;
    private Map<String,Product> allTheProductsInStore = new AllProductsInStore().getAllProductsInStore();
    public static List<Product> allPurchasedProducts = new ArrayList<>();

    public double getItemPrice(String productName, double quantity) {
        long alreadyPurchasedSameItemCount = allPurchasedProducts.stream().filter(product -> product.getProductName().equals(productName)).count();
        double originalPrice = allTheProductsInStore.get(productName).getProductPrice()
                - allTheProductsInStore.get(productName).getMarkdown();
        SaleType saleType = allTheProductsInStore.get(productName).getSpecial().getSaleType();
        switch (saleType){
            case BUYNITEMSGETMATXOFF:
                theTotalOfPurchasedPrice = ScanOnSaleProduct.getItemPriceWithBuyNItemsGetMAtXOff(
                        productName,quantity,originalPrice,theTotalOfPurchasedPrice,
                        alreadyPurchasedSameItemCount,allTheProductsInStore,allPurchasedProducts);
                return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
            case BUYNFORM:
                theTotalOfPurchasedPrice = ScanOnSaleProduct.getItemPriceWithBuyNForM(productName,quantity, originalPrice,
                        theTotalOfPurchasedPrice, alreadyPurchasedSameItemCount, allTheProductsInStore, allPurchasedProducts);
                return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
            case BUYNGETMFREELIMITX:
                theTotalOfPurchasedPrice =  ScanOnSaleProduct.getItemPriceWithBuyNGetMFreeLimitX(productName,originalPrice, alreadyPurchasedSameItemCount,
                        quantity, theTotalOfPurchasedPrice,allTheProductsInStore, allPurchasedProducts);
                return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
            case BUYNGETMORLESSOFF:
                theTotalOfPurchasedPrice =  ScanOnSaleProduct.getItemPriceWithBuyNGetMOrLessOff(productName,quantity, originalPrice,
                        theTotalOfPurchasedPrice, allTheProductsInStore, allPurchasedProducts);
                return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
            case NONE:
            default: return getThePriceAfterApplyTheMarkdown(productName, quantity,originalPrice, allTheProductsInStore);
        }

    }
    private static double getThePriceAfterApplyTheMarkdown(String productName, double quantity, double originalPrice, Map<String, Product> allTheProductsInStore) {
        Product onSaleProduct;
        onSaleProduct = new Product(productName,originalPrice*quantity,quantity);
        allPurchasedProducts.add(onSaleProduct);
        theTotalOfPurchasedPrice += originalPrice*quantity;
        return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
    }

    public double voidOneItem(String productName, double quantity) {
        if(allPurchasedProducts.stream().filter(product -> product.getProductName().equals(productName)).count()==0)
            return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
        Product voidProduct;
        //the original price with or without markdown
        double originalPrice = allTheProductsInStore.get(productName).getProductPrice()
                - allTheProductsInStore.get(productName).getMarkdown();
        //if the product is not on sale
        Optional<Product> possibleProductToDeleteWithOriginalPrice = allPurchasedProducts.stream()
                .filter(product -> product.getProductName().equals(productName))
                .filter(product -> product.getProductPrice()==originalPrice)
                .findFirst();
        //if the product is on sale
        Optional<Product> possibleProductToDeleteWithOnSalePrice;
        long alreadyPurchasedSameItemCount = allPurchasedProducts.stream().filter(product -> product.getProductName().equals(productName)).count();
        SaleType saleType = allTheProductsInStore.get(productName).getSpecial().getSaleType();

        switch (saleType){
            case BUYNITEMSGETMATXOFF:
                theTotalOfPurchasedPrice =  VoidOnSaleProduct.voidItemWithBuyNItemsGetMAtXOff(productName,originalPrice,
                        alreadyPurchasedSameItemCount, theTotalOfPurchasedPrice, possibleProductToDeleteWithOriginalPrice,
                        allTheProductsInStore, allPurchasedProducts);
                return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
            case BUYNFORM:
                theTotalOfPurchasedPrice =  VoidOnSaleProduct.voidItemWithBuyNNForM(productName, originalPrice,
                        alreadyPurchasedSameItemCount, theTotalOfPurchasedPrice, possibleProductToDeleteWithOriginalPrice,
                        allTheProductsInStore, allPurchasedProducts);
                return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
            case BUYNGETMFREELIMITX:
                theTotalOfPurchasedPrice =  VoidOnSaleProduct.voidItemWithBuyNGetMFreeLimitX(productName,
                        alreadyPurchasedSameItemCount, theTotalOfPurchasedPrice, possibleProductToDeleteWithOriginalPrice,
                        allTheProductsInStore, allPurchasedProducts);
                return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
            case BUYNGETMORLESSOFF:
                theTotalOfPurchasedPrice =  VoidOnSaleProduct.voidItemWithBuyNGetMOrLessXOff(productName, quantity, originalPrice,
                        theTotalOfPurchasedPrice, possibleProductToDeleteWithOriginalPrice,
                        allTheProductsInStore, allPurchasedProducts);
                return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
            case NONE:
            default:
                theTotalOfPurchasedPrice =  VoidOnSaleProduct.deleteItemWithOriginalPrice(possibleProductToDeleteWithOriginalPrice,
                        theTotalOfPurchasedPrice,allPurchasedProducts);
                return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
        }
    }
}
