package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public Writer() throws Exception {
        this.fileWriter = new FileWriter("JsonRedigerad");
        this.bufferedWriter = new BufferedWriter(fileWriter);
            }

    public void writeToFile(String string) throws IOException{

        try{
            bufferedWriter.write(string);


        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void closeBufferedWriter()throws IOException{
        bufferedWriter.close();
    }
}
