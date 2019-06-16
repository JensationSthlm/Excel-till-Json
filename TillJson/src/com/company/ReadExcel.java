package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ReadExcel {

    private File excelFile;
    private Brott enskiltBrott;
    private Writer writer;
    private ArrayList<Brott> listaBrott;


// tar input en String som är adressen till den Excelfil som ska läsas.
    public ReadExcel(String filen) throws Exception{
       this.excelFile = new File(filen);
       this.enskiltBrott = new Brott();
       this.listaBrott = new ArrayList<Brott>();
       this.writer= new Writer();

    }


    // läser excelfil och gör alla brott till objekt och lägger i listan "listaBrott'.
    public void excelFileReader() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(excelFile);
        // skapar en läsbarkopia av Excelfilen.
        XSSFWorkbook excelWorkbook = new XSSFWorkbook(fileInputStream);
        // bestämmer vilket sheet som ska läsas.
        XSSFSheet excelSheet = excelWorkbook.getSheetAt(0);
        // skapar en radhanterare som går igenom varje rad.
        Iterator<Row> rowIterator = excelSheet.iterator();
        // skapar ett objekt som formaterar informationen i en cell exact som den ser ut. Alltså bevarar siffror som börjar med 0 etc.
        DataFormatter dataFormatter = new DataFormatter();
        // skapar ett skrivarobjekt som kan skriva till fil


        // skippar första raden i excelarket. Den med alla rubriker.
        rowIterator.next();
        // så länge det finns en ny rad att hantera:

        while (rowIterator.hasNext()){

            // skapar en lista för att hålla informationen som finns i cellerna i varje rad. = information om ett brott.
            ArrayList<String>information = new ArrayList<String>();

            // skapa en rad. Så länge radhanteraren har en till rad.
            Row row = rowIterator.next();

            // skapa en cellhanterare som går igenom varje cell per rad.
            Iterator<Cell> cellIterator = row.cellIterator();

            // så länge det finns en ny cell att hantera i raden:
            while (cellIterator.hasNext()){
                // hämtar informationen från cellen.
                Cell cell = cellIterator.next();
                // här lagras informationen i som hämtas i varje cell.
                String cellInformation;
                // gör en string av informationen i cellen.
                cellInformation = dataFormatter.formatCellValue(cell);
                // tar bort blanksteg i början och slutet av ordet. Finns flera i orignalexcellen.
                cellInformation=cellInformation.trim();

                // om det saknas informationen i cellen skriv värdet null.
                if (cellInformation.isEmpty()){
                    cellInformation = "NULL";
                }
                // lägger till Stringen i listan.
                information.add(cellInformation);
            }
            // när all information i en rad ligger i listan. Skicka listan till createBrott.
            createBrott(information);

        }
        //Stänger excelfilen som vi skapat och jobbat i.
        excelWorkbook.close();
        //stänger fileInputStream
        fileInputStream.close();

        // fixar till alla datum i brottsobjektlistan.
        fixaObjektetsAllaDatum();

        // Skriver ut antalet brott i listan
        System.out.println("Antal brott i listan: " + listaBrott.size());

        // Visar att läsningen är klar och hanteringen av objekten är klara.
        System.out.println("KLART");
    }

    // skriver information till fil.
    public void writeToFile(String string) throws Exception{
        this.writer.writeToFile(string);
    }

    // har inte kommit på hur jag ska lägga in closeBufferedWriter i writeToFilemetoden
    // utan att den stängs för tidigt. Lägger den i en egen metod istället så kan jag anropa den
    // när jag vet att allt är skrivet.
    public void bufferedWriterClose()throws IOException {
        writer.closeBufferedWriter();
    }


    // metoden som hanterar alla undermetoder som fixar datum i brottsobjekten.
    public void fixaObjektetsAllaDatum(){
        for (Brott brott: listaBrott) {
            fixDateToString(brott);
            brott.setStartDateTime(brott.getBrottsdagStart()+" "+fixTime(brott.getBrottsTidStart()));
            brott.setSlutDateTime(brott.getBrottsdagSlut()+" "+fixTime(brott.getBrottsTidSlut()));
        }
    }

    // fixar alla kortadatum i brottsobjektet till yyyy-mm-dd genom att anropa hjälp metoder.
    public void fixDateToString(Brott brott) {
            brott.setInskrivningsdatum(fixDateShort(brott.getInskrivningsdatum()));
            brott.setBrottsdagStart(fixDateShort(brott.getBrottsdagStart()));
            brott.setBrottsdagSlut(fixDateShort(brott.getBrottsdagSlut()));

    }

    // fixar till datum till formatet MM-DD-YY. Lägger till en 0 framför om det dag/månad bara är en siffra.
    // fixar bara datum i form av av xx/xx/xx. För metoden avnänder sig av tecknet / för att bryta ner substrings.
     public String fixDateShort (String oredigerad) {
        String datum = oredigerad;
        String arbetsDatum = "";
        String komplettDatum = "";

        if (datum.equals("NULL") || datum.equals("inskrdat")) {
            return "NULL";
        } else {

            //sätter arbetsdatum till siffrorna innan första / tecknet. Siffrorna representerar månaden.
            komplettDatum = datum.substring(0, datum.indexOf("/"));
            // sätter datum till det kvarvarande efter första / tecknet.
            datum = datum.substring(datum.indexOf("/") + 1, datum.length());
            // Om det bara finns en siffra lägg till en 0a innan.
            if (komplettDatum.length() == 1) {
                komplettDatum = "0" + komplettDatum;
            }
            //sätter arbetsdatum till siffrorna innan första / tecknet. Siffrorna representerar dagen.
            arbetsDatum = datum.substring(0, datum.indexOf("/"));
            datum = datum.substring(datum.indexOf("/") + 1);
            // Om det bara finns en siffra lägg till en 0a innan.
            if (arbetsDatum.length() == 1) {
                arbetsDatum = "0" + arbetsDatum;
            }
            // lägger ihop det kompletta datumsträngen. DD-MM-YY
            komplettDatum += "-" + arbetsDatum + "-" + datum;
            // här sker anrop till annan metod som gör tidsobjekt och retunerar yyyy-mm-dd
            komplettDatum = niceDateString(komplettDatum);

            return komplettDatum;
        }
    }

    // skapar ett tidsobjekt som tar MM-dd-yy och retunerar en string med yyyy-mm-dd.
    public String niceDateString(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy");
        LocalDate localDate = LocalDate.parse(date,formatter);

        return localDate.toString();

    }

   // tar en tidsangivelsse enligt HHmm och gör om den till HH:mm.
    public String fixTime(String brottTid){
        String tidRedigerad = brottTid.substring(0,2)+":"+brottTid.substring(2);
        return tidRedigerad;
            }

    // skapar nytt brottobjekt.
    public void createBrott(ArrayList<String> information){

        Brott brott = new Brott();

        // hämtar informationen från listan och lägger till det till objektet.
        brott.setInskrivningsdatum(information.get(0));
        brott.setNäpo(information.get(1));
        brott.setBasområde(information.get(2));
        brott.setGata(information.get(3));
        brott.setBrottskod(information.get(4));
        brott.setAntal(information.get(5));
        brott.setBrottsdagStart(information.get(6));
        brott.setBrottsdagSlut(information.get(7));
        brott.setBrottsTidStart(information.get(8));
        brott.setBrottsTidSlut(information.get(9));

        // lägger till brottet i listan för alla brott.
        listaBrott.add(brott);
    }

    // från Jacksonrepositorin, skapar en sträng med information från brottsobjektet i Jsonformat.
    public void writeJson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        for (Brott brott : listaBrott) {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(brott);
            writeToFile(json);
        }
        // Måste tydligen ha med detta annars vägrar den att skriva fill fil.
        // Kanske för att den lagar allt den ska skriva i bufferedWriter och skriver först till filen när den stängs?
        bufferedWriterClose();
    }

    // söker efter objekt med en viss adress
    public void searchAdress(String adress){
        // skapar en lista med sökresultat
        ArrayList<Brott>searchResult = new ArrayList<Brott>();
        for (Brott brott : listaBrott){
            if (brott.getGata().equalsIgnoreCase(adress)){
                searchResult.add(brott);
            }
         }

// sorterar den sökta listan efter datum. Tidigast först.
    Collections.sort(searchResult);

        for (Brott brott : searchResult){
            System.out.println(brott.toString());
        }
        System.out.println();
        System.out.println("Antal brott på " + adress +": "+searchResult.size());
        System.out.println();
        searchResult.clear();
    }
}
