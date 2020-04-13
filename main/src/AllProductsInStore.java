package src;

import java.util.HashMap;
import java.util.Map;

public class AllProductsInStore {
    private Map<String,Product> allProductsInStore;

    public AllProductsInStore() {
        allProductsInStore = new HashMap<>();
        allProductsInStore.put("soup",new Product("soup",2.56, 0.3,new Special(SaleType.NONE,new int[]{0})));
        allProductsInStore.put("banana",new Product("banana",0.6,0,
                new Special(SaleType.BUYNGETMORLESSOFF,new int[]{3,2,50})));
        allProductsInStore.put("milk",new Product("milk",4.2,0,
                new Special(SaleType.BUYNGETMFREELIMITX,new int[]{3,1,8})));
        allProductsInStore.put("egg",new Product("egg",3.9,0.9,
                new Special(SaleType.BUYNITEMSGETMATXOFF,new int[]{4,2,50})));
        allProductsInStore.put("pasta",new Product("pasta",1.2,0,
                new Special(SaleType.BUYNFORM,new int[]{3,3})));
        allProductsInStore.put("rice",new Product("rice",9.99,0,
                new Special(SaleType.BUYNFORM,new int[]{3,20})));
    }

    public Map<String, Product> getAllProductsInStore() {
        return allProductsInStore;
    }
}

