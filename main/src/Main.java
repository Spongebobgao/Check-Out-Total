package src;

import src.CheckOut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        CheckOut checkOut = new CheckOut();
        File file = new File("input.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println("Please enter the test file path: ");
        Scanner readPath = new Scanner(System.in);
        String filePath = readPath.nextLine();
        file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String itemInfo = reader.readLine();
        System.out.println(itemInfo);
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

