package com.tw.salesassignment;

import java.text.DecimalFormat;
import java.util.List;
     public class Receipt {

        private List<PurchaseItem> purchaseItemList;

        String pattern = "#0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        public Receipt(List<PurchaseItem> purchaseItemList) {
            this.purchaseItemList = purchaseItemList;
        }

        public double calculateTotalSalesTax(){

            double itemTax =0;
            for(int i=0;i<purchaseItemList.size();i++)
            {
                itemTax=itemTax+purchaseItemList.get(i).calculateItemTotalTax();
            }

            return roundOff(itemTax);

        }
        private static double roundOff(double value)
        {
            return Math.round(value * 100.0) / 100.0;
        }

        public double calculateTotalAmount(){
            double totalAmount =0;
            for(int i=0;i<purchaseItemList.size();i++)
            {
                totalAmount=totalAmount+purchaseItemList.get(i).calculatePriceIncludingTax();
            }
            return roundOff(totalAmount);
        }

        public String printReceipt()
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < purchaseItemList.size(); i++) {
                System.out.println(purchaseItemList.get(i));
                sb.append(purchaseItemList.get(i));
                sb.append("\n");
            }
            System.out.println("Sales Taxes: "+decimalFormat.format(calculateTotalSalesTax()));
            System.out.println("Total: "+decimalFormat.format(calculateTotalAmount()));

            sb.append("Sales Taxes: "+decimalFormat.format(calculateTotalSalesTax()));
            sb.append("\n");
            sb.append("Total: "+decimalFormat.format(calculateTotalAmount()));
            return sb.toString();
        }


    }


