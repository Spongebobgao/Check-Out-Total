package src;

public class Special {
    private SaleType saleType;
    private int[] value;

    public Special() {
    }

    public Special(SaleType saleType, int[] value) {
        this.saleType = saleType;
        this.value = value;
    }

    public SaleType getSaleType() {
        return saleType;
    }

    public void setSaleType(SaleType saleType) {
        this.saleType = saleType;
    }

    public int[] getValue() {
        return value;
    }

    public void setValue(int[] value) {
        this.value = value;
    }
}
