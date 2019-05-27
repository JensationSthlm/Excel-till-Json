package com.company;

public class Main {

    public static void main(String[] args) throws Exception {

        ReadExcel read = new ReadExcel("C:\\Users\\Jens\\Downloads\\test.xlsx");
        read.excelFileReader();
        read.writeJson();




    }
}
