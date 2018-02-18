package com.company;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.stream.Collectors;

public class DataReadWrite {

    public static String dataReader(String path){
        if(path == "No file selected"){ System.exit(0);}
        File data = new File(path);

        try{
            BufferedReader getData = new BufferedReader(new FileReader(data));
            String dataString = getData.lines().collect(Collectors.joining());
            getData.close();

            return dataString;
        } catch(FileNotFoundException e ){
            System.out.println("Couldn't find the file");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("An I/O Error Occurred");
            System.exit(0);
        }
        return null;
    }

    public static PrintWriter dataWriter(String path){
        File data = new File(path);

        try{
            PrintWriter infoToWrite = new PrintWriter(new BufferedWriter(new FileWriter(data)));

            return infoToWrite;
        } catch (IOException e) {
            System.out.println("An I/O Error Occurred");
            System.exit(0);
        }
        return null;
    }
}
