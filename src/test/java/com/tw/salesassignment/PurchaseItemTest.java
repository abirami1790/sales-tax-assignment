package com.tw.salesassignment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


class PurchaseItemTest {

    List<PurchaseItem> purchaseItemList = new ArrayList<>();

    Receipt reciept;

    BigDecimal basicdsalesTaxRate = new BigDecimal(10);
    BigDecimal importTaxRate = new BigDecimal(5);

    String pattern = "##.##";
    DecimalFormat decimalFormat = new DecimalFormat(pattern);


    @Test
    public void testItemTotalSalesTaxUnimportedFoodBookAndOthers() {

        PurchaseItem purchaseItem = new PurchaseItem("book", 1, ItemType.BOOK, false, new BigDecimal(12.49));
        Assertions.assertEquals(decimalFormat.format(12.49), String.valueOf(purchaseItem.calculatePriceIncludingTax()));
        purchaseItemList.add(purchaseItem);

        purchaseItem = new PurchaseItem("music CD", 1, ItemType.OTHERS, false, new BigDecimal(14.99));
        Assertions.assertEquals(decimalFormat.format(16.49), String.valueOf(purchaseItem.calculatePriceIncludingTax()));
        purchaseItemList.add(purchaseItem);

        purchaseItem = new PurchaseItem("chocolate bar", 1, ItemType.FOOD, false, new BigDecimal(0.85));
        Assertions.assertEquals(decimalFormat.format(0.85), String.valueOf(purchaseItem.calculatePriceIncludingTax()));
        purchaseItemList.add(purchaseItem);

        reciept = new Receipt(purchaseItemList);
        Assertions.assertEquals(decimalFormat.format(1.50), String.valueOf(reciept.calculateTotalSalesTax()));
        Assertions.assertEquals(decimalFormat.format(29.83), String.valueOf(reciept.calculateTotalAmount()));

        String output = "1 book: 12.49\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 29.83";
        Assertions.assertEquals(output,reciept.printReceipt());
        purchaseItemList.clear();

    }

    @Test
    public void testItemTotalSalesTaxImportedFoodAndOthers(){

        PurchaseItem purchaseItem = new PurchaseItem("box of chocolates",1,ItemType.BOOK,true,new BigDecimal(10.00));
        Assertions.assertEquals(decimalFormat.format(10.50),String.valueOf(purchaseItem.calculatePriceIncludingTax()));
        purchaseItemList.add(purchaseItem);

        purchaseItem = new PurchaseItem("bottle of perfume",1,ItemType.OTHERS,true,new BigDecimal(47.50));
        Assertions.assertEquals(decimalFormat.format(54.65),String.valueOf(purchaseItem.calculatePriceIncludingTax()));
        purchaseItemList.add(purchaseItem);

        reciept = new Receipt(purchaseItemList);
        Assertions.assertEquals(decimalFormat.format(7.65), String.valueOf(reciept.calculateTotalSalesTax()));
        Assertions.assertEquals(decimalFormat.format(65.15), String.valueOf(reciept.calculateTotalAmount()));

        String output = "1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.65\n" +
                "Total: 65.15";
        Assertions.assertEquals(output,reciept.printReceipt());
        purchaseItemList.clear();
    }

    @Test
    public void testItemTotalSalesTaxImportedAndUnimportedAllExceptBook(){

        PurchaseItem purchaseItem = new PurchaseItem("bottle of perfume",1,ItemType.OTHERS,true,new BigDecimal(27.99));
        Assertions.assertEquals(decimalFormat.format(32.19),String.valueOf(purchaseItem.calculatePriceIncludingTax()));
        purchaseItemList.add(purchaseItem);

        purchaseItem = new PurchaseItem("bottle of perfume",1,ItemType.OTHERS,false,new BigDecimal(18.99));
        Assertions.assertEquals(decimalFormat.format(20.89),String.valueOf(purchaseItem.calculatePriceIncludingTax()));
        purchaseItemList.add(purchaseItem);

        purchaseItem = new PurchaseItem("packet of headache pills",1,ItemType.MEDICINE,false,new BigDecimal(9.75));
        Assertions.assertEquals(decimalFormat.format(9.75),String.valueOf(purchaseItem.calculatePriceIncludingTax()));
        purchaseItemList.add(purchaseItem);

        purchaseItem = new PurchaseItem("box of chocolates",1,ItemType.FOOD,true,new BigDecimal(11.25));
        Assertions.assertEquals(decimalFormat.format(11.85),String.valueOf(purchaseItem.calculatePriceIncludingTax()));
        purchaseItemList.add(purchaseItem);

        reciept = new Receipt(purchaseItemList);
        Assertions.assertEquals(decimalFormat.format(6.70), String.valueOf(reciept.calculateTotalSalesTax()));
        Assertions.assertEquals(decimalFormat.format(74.68), String.valueOf(reciept.calculateTotalAmount()));

        String output = "1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "1 imported box of chocolates: 11.85\n" +
                "Sales Taxes: 6.70\n" +
                "Total: 74.68";
        Assertions.assertEquals(output,reciept.printReceipt());
        purchaseItemList.clear();
    }
}