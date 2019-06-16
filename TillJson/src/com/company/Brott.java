package com.company;

public class Brott implements Comparable<Brott>{

    private String Inskrivningsdatum;
    private String näpo;
    private String basområde;
    private String gata;
    private int brottskod;
    private int antal;
    private String brottsdagStart;
    private String brottsdagSlut;
    private String brottsTidStart;
    private String brottsTidSlut;
    private String startDateTime;
    private String slutDateTime;


    public Brott(){
    }

    public void setInskrivningsdatum(String inskrivningsdatum){
        this.Inskrivningsdatum = inskrivningsdatum;
    }
    public String getInskrivningsdatum(){
        return this.Inskrivningsdatum;
    }

    public void setNäpo(String napo){
        this.näpo=napo;

    }
    public String getNäpo(){
        return this.näpo;
    }

    public void setBasområde(String basområde){
        this.basområde = basområde;
    }
    public String getBasområde(){
        return this.basområde;
    }

    public void setGata (String gata){
        this.gata = gata;
    }
    public String getGata(){
        return this.gata;
    }

    public void setBrottskod(String brottskod){
        // försöker först göra om stringen till integer. Går inte det sätt brottskoden till 0000 som ett tecken på att det inte fungerade.
        try {
            this.brottskod = Integer.parseInt(brottskod);
        } catch (Exception e){
            this.brottskod = 0000;
        }
    }
    public int getBrottskod(){
        return this.brottskod;
    }

    public void setAntal (String antal){
        try {
            this.antal = Integer.parseInt(antal);
        } catch (Exception e){
            this.brottskod = 0000;
        }
    }
    public int getAntal(){
        return this.antal;
    }

    public void setBrottsdagStart(String brottsdagStart){
        this.brottsdagStart = brottsdagStart;
    }
    public String getBrottsdagStart(){
        return this.brottsdagStart;
    }

    public void setBrottsTidStart(String brottsTidStart){
        this.brottsTidStart = brottsTidStart;
    }
    public String getBrottsTidStart(){
        return this.brottsTidStart;
    }

    public void setBrottsdagSlut (String brottsdagSlut){
        this.brottsdagSlut = brottsdagSlut;
    }
    public String getBrottsdagSlut(){
        return this.brottsdagSlut;
    }

    public void setBrottsTidSlut (String brottsTidSlut){
        this.brottsTidSlut = brottsTidSlut;
    }
    public String getBrottsTidSlut(){
        return this.brottsTidSlut;
    }

    public void setStartDateTime(String StartDateTime){
        this.startDateTime=StartDateTime;
    }
    public String getStartDateTime() {
        return this.startDateTime;
    }

    public void setSlutDateTime(String slutDateTime ){
        this.slutDateTime= slutDateTime;
    }
    public String getSlutDateTime(){
        return this.slutDateTime;
    }

    // skickar tillbaka lite information men inte all. Mest för att testa mot originalfil för att se att infon kommer med.
    @Override
    public String toString() {
        return getBrottsdagStart()+" "+getGata()+" "+getBrottskod();
    }
    
// Sorterar brotten beroende på vilken brottstid.
    public int compareTo(Brott compareTo) {
        return this.brottsdagStart.compareToIgnoreCase(compareTo.getBrottsdagStart());
    }

}
