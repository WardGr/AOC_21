package main.java.utilities.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public static Scanner makeScanner(String file) {
        try {
            Scanner x = new Scanner(new FileReader(file));
            return x;
        } catch(Exception e) { throw new RuntimeException(e);}
    }

    public static ArrayList<String> fileToList(String file) {
        ArrayList returnVal = new ArrayList();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while((line=reader.readLine())!=null){
                returnVal.add(line);
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return returnVal;
    }
}
