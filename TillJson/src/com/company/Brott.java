package com.company;

public class Brott {


    private String Inskrivningsdatum;
    private String näpo;
    private String basområde;
    private String gata;
    private String brottskod;
    private String antal;
    private String brottsdagStart;
    private String brottsdagSlut;
    private String brottsTidStart;
    private String brottsTidSlut;


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
        this.brottskod =  brottskod;  //Integer.parseInt(brottskod);
    }
    public String getBrottskod(){
        return this.brottskod;
    }

    public void setAntal (String antal){
        this.antal = antal; //Integer.parseInt(antal);
    }
    public String getAntal(){
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

    // skickar tillbaka lite information men inte all. Mest för att testa mot originalfil för att se att infon kommer med.
    @Override
    public String toString() {
        return getNäpo()+" "+getGata()+" "+getBrottskod();
    }
}
