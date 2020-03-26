package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CheckOutTotal {
     public static void main(String[] args) throws IOException {
         CheckOut checkOut = new CheckOut();
         File file = new File("main/src/input.txt");
         BufferedReader reader = new BufferedReader(new FileReader(file));
         String itemInfo = reader.readLine();
         Scanner scanner = new Scanner(itemInfo);
         String[] typeAndProductNameAndQuantity = new String[3];
         while(itemInfo!=null){
             int counter = 0;
             scanner = new Scanner(itemInfo);
             while(scanner.hasNext()&&counter<3){
                 typeAndProductNameAndQuantity[counter] = scanner.next();
                 counter ++;
             }
             if(typeAndProductNameAndQuantity[0].equals("scan")){
                 System.out.println("Scanned  "+typeAndProductNameAndQuantity[2]+" " + typeAndProductNameAndQuantity[1] + ". The total now is " + checkOut.getItemPrice(typeAndProductNameAndQuantity[1], Double.parseDouble(typeAndProductNameAndQuantity[2])));
             }
             else {
                 System.out.println("Voided  "+typeAndProductNameAndQuantity[2]+" " + typeAndProductNameAndQuantity[1] + ". The total now is " + checkOut.voidOneItem(typeAndProductNameAndQuantity[1], Double.parseDouble(typeAndProductNameAndQuantity[2])));
             }
             itemInfo = reader.readLine();
         }
     }
}
