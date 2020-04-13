package src;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VoidOnSaleProduct {
    public static double voidItemWithBuyNItemsGetMAtXOff(String productName, double originalPrice,
                                                         long alreadyPurchasedSameItemCount, double theTotalOfPurchasedPrice,
                                                         Optional<Product> possibleProductToDeleteWithOriginalPrice,
                                                         Map<String, Product> allTheProductsInStore,
                                                         List<Product> allPurchasedProducts) {
        int[] buyNItemsGetMAtXOff = allTheProductsInStore.get(productName).getSpecial().getValue();
        int numberOfOriginalPrice = buyNItemsGetMAtXOff[0];
        int numberOfSalePrice = buyNItemsGetMAtXOff[1];
        int percentOfDiscount = buyNItemsGetMAtXOff[2];
        double onSalePrice = originalPrice-originalPrice*percentOfDiscount/100.0;
        Product voidProduct;
        Optional<Product> possibleProductToDeleteWithOnSalePrice = allPurchasedProducts.stream()
                .filter(product->product.getProductName().equals(productName))
                .filter(product->product.getProductPrice()==onSalePrice)
                .findFirst();
        //if this is true will delete the one applied discount first
        if(alreadyPurchasedSameItemCount<=(numberOfOriginalPrice+numberOfSalePrice)
                &&alreadyPurchasedSameItemCount>numberOfOriginalPrice){
            if(possibleProductToDeleteWithOnSalePrice.isPresent()){
                voidProduct = possibleProductToDeleteWithOnSalePrice.get();
                theTotalOfPurchasedPrice -= voidProduct.getProductPrice();
                allPurchasedProducts.remove(voidProduct);
                return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
            }
        }
        return deleteItemWithOriginalPrice(possibleProductToDeleteWithOriginalPrice,
                theTotalOfPurchasedPrice,allPurchasedProducts);
    }

    public static double voidItemWithBuyNNForM(String productName, double originalPrice,
                                               long alreadyPurchasedSameItemCount, double theTotalOfPurchasedPrice,
                                               Optional<Product> possibleProductToDeleteWithOriginalPrice,
                                               Map<String, Product> allTheProductsInStore,
                                               List<Product> allPurchasedProducts) {
        int [] buyNForM = allTheProductsInStore.get(productName).getSpecial().getValue();
        int numberOfNeededItems = buyNForM[0];
        int priceForNItems=buyNForM[1];
        Product voidProduct;
        if(alreadyPurchasedSameItemCount % numberOfNeededItems == 0){
            double differenceAfterInvalidateSpecial = priceForNItems-originalPrice*(numberOfNeededItems-1);
            theTotalOfPurchasedPrice -= differenceAfterInvalidateSpecial;
            double onSalePrice = (double)priceForNItems/(double)numberOfNeededItems;
            Optional<Product> possibleProductToDeleteWithOnSalePrice = allPurchasedProducts.stream()
                    .filter(product->product.getProductName().equals(productName))
                    .filter(product->product.getProductPrice()==Math.round(onSalePrice*100.0)/100.0)
                    .findFirst();
            // delete one and reset (n-1) items' price to original
            if(possibleProductToDeleteWithOnSalePrice.isPresent())
                allPurchasedProducts.remove(possibleProductToDeleteWithOnSalePrice.get());
            //each time find one and reset the price, do n-1 times
            for(int i = 0; i<numberOfNeededItems-1; i++){
                possibleProductToDeleteWithOnSalePrice = allPurchasedProducts.stream()
                        .filter(product->product.getProductName().equals(productName))
                        .filter(product->product.getProductPrice()==Math.round(onSalePrice*100.0)/100.0)
                        .findFirst();
                if(possibleProductToDeleteWithOnSalePrice.isPresent())
                    possibleProductToDeleteWithOnSalePrice.get().setProductPrice(originalPrice);
            }
            return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
        }
        return deleteItemWithOriginalPrice(possibleProductToDeleteWithOriginalPrice,
                theTotalOfPurchasedPrice,allPurchasedProducts);
    }

    public static double voidItemWithBuyNGetMFreeLimitX(String productName, long alreadyPurchasedSameItemCount,
                                                        double theTotalOfPurchasedPrice,
                                                        Optional<Product> possibleProductToDeleteWithOriginalPrice,
                                                        Map<String, Product> allTheProductsInStore,
                                                        List<Product> allPurchasedProducts) {
        int[] buyNGetMFreeLimitX = allTheProductsInStore.get(productName).getSpecial().getValue();
        int numberOfItemsNeedToBuy = buyNGetMFreeLimitX[0];
        int numberOfFreeItems = buyNGetMFreeLimitX[1];
        int limitNumberOfItems = buyNGetMFreeLimitX[2];
        Product voidProduct;
        if(alreadyPurchasedSameItemCount % (numberOfFreeItems + numberOfItemsNeedToBuy) ==0
                && alreadyPurchasedSameItemCount <= limitNumberOfItems){
            Optional<Product> possibleProductToDeleteWithOnSalePrice = allPurchasedProducts.stream()
                    .filter(product -> product.getProductName().equals(productName))
                    .filter(product -> product.getProductPrice()==0)
                    .findFirst();
            if(possibleProductToDeleteWithOnSalePrice.isPresent())
                allPurchasedProducts.remove(possibleProductToDeleteWithOnSalePrice.get());
            return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
        }
        return deleteItemWithOriginalPrice(possibleProductToDeleteWithOriginalPrice,
                theTotalOfPurchasedPrice,allPurchasedProducts);
    }

    public static double voidItemWithBuyNGetMOrLessXOff(String productName, double quantity, double originalPrice,
                                                        double theTotalOfPurchasedPrice,
                                                        Optional<Product> possibleProductToDeleteWithOriginalPrice,
                                                        Map<String, Product> allTheProductsInStore,
                                                        List<Product> allPurchasedProducts) {
        int[] buyNGetMOrLessXOff = allTheProductsInStore.get(productName).getSpecial().getValue();
        int numberOfNeedWeight = buyNGetMOrLessXOff[0];
        int numberOfSaleWeight = buyNGetMOrLessXOff[1];
        int numberOfSaleWeightDiscount = buyNGetMOrLessXOff[2];
        Product voidProduct;
        double onSalePrice;
        if (quantity > numberOfNeedWeight) {
            if ((quantity - numberOfNeedWeight) <= numberOfSaleWeight) {
                onSalePrice = originalPrice * numberOfSaleWeightDiscount / 100.0 * (quantity - numberOfNeedWeight) + originalPrice * numberOfNeedWeight;
            } else {
                onSalePrice = originalPrice * numberOfSaleWeightDiscount / 100.0 * numberOfSaleWeight + originalPrice * (quantity - numberOfSaleWeight);
            }
            theTotalOfPurchasedPrice -= onSalePrice;
            allPurchasedProducts.remove(new Product(productName,quantity,onSalePrice));
            return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
        }
        return deleteItemWithOriginalPrice(possibleProductToDeleteWithOriginalPrice,
                theTotalOfPurchasedPrice,allPurchasedProducts);
    }

    public static double deleteItemWithOriginalPrice(Optional<Product> possibleProductToDeleteWithOriginalPrice,
                                                     double theTotalOfPurchasedPrice,
                                                     List<Product> allPurchasedProducts){
        if(possibleProductToDeleteWithOriginalPrice.isPresent()){
            Product voidProduct = possibleProductToDeleteWithOriginalPrice.get();
            theTotalOfPurchasedPrice -= voidProduct.getProductPrice();
            allPurchasedProducts.remove(voidProduct);
            return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
        }
        return Math.round(theTotalOfPurchasedPrice*100.0)/100.0;
    }
}
