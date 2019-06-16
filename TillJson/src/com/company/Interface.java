package com.company;

import java.util.Scanner;

public class Interface {
    Scanner reader;
    ReadExcel importBrott;

    public Interface() throws Exception {
        this.importBrott = new ReadExcel("C:\\Users\\Jens\\Downloads\\test.xlsx");
        this.importBrott.excelFileReader();
        this.reader = new Scanner(System.in);
    }

    public void meny() throws Exception {

        while (true) {


            System.out.println("Vad vill du göra?" +
                    " Du kan:\n" +
                    "\n" +
                    "1: Söka efter brott på viss gata.\n"+
                    "2: Stänga programmet \n" +
                    "3: Skriva till fil.");

            System.out.println();

            String command = reader.next();

            if (command.equals("1")) {
                System.out.println("Vilken adress?");
                command = reader.next();
                importBrott.searchAdress(command);
            } else if (command.equals("2")){
                break;
            } else if (command.equals("3")){
                importBrott.writeJson();
            }


        }
    }
}



