package com.tw.salesassignment;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PurchaseItem {
    private String itemName;
    private int quantity;
    private ItemType itemType;
    private Boolean isImported;
    private BigDecimal price;

    String pattern = "#0.00";
    DecimalFormat decimalFormat = new DecimalFormat(pattern);

    public PurchaseItem(String itemName, int quantity, ItemType itemType, Boolean isImported, BigDecimal price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemType = itemType;
        this.isImported = isImported;
        this.price = price;
    }

    private static double roundOffToNearest5(BigDecimal value)
    {
        return Math.ceil(value.doubleValue() / .05) * .05;
    }
    private static double roundOff(double value)
    {
        return Math.round(value * 100.0) / 100.0;
    }


    public double calculatePriceIncludingTax() {
        if(isImported){
            if(itemType==ItemType.BOOK || itemType==ItemType.MEDICINE || itemType==ItemType.FOOD) {
                //5% tax
                return roundOff(this.price.doubleValue() + roundOffToNearest5(price.multiply(TaxType.EXEMPTED_IMPORTED_TAX.rate)));
            } else {
                //15%
                return roundOff(this.price.doubleValue() + roundOffToNearest5(price.multiply(TaxType.NOT_EXEMPTED_IMPORTED_TAX.rate)));
            }
        } else {
            if(itemType==ItemType.BOOK || itemType==ItemType.MEDICINE || itemType==ItemType.FOOD){
                //0%
                return roundOff(this.price.doubleValue() + roundOffToNearest5(this.price.multiply(TaxType.EXEMPTED_UNIMPORTED_TAX.rate)));
            } else {
                //10% tax
                return roundOff(this.price.doubleValue() + roundOffToNearest5(price.multiply(TaxType.NOT_EXEMPTED_UNIMPORTED_TAX.rate)));
            }
        }
    }

    public double calculateItemTotalTax(){
        return calculatePriceIncludingTax()-this.price.doubleValue();
    }


    @Override
    public String toString() {
        return quantity +
                " " + (isImported == true ? "imported " : "") +
                itemName +
                ": " +
                decimalFormat.format(calculatePriceIncludingTax());
    }


}
