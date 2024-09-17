package stringtokenizer;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class PreProcess {
    private File file;
    private File dict;
    // private int length;
    // private int rows = 6400000;
    private HashMap<Integer, String> dictionary;

    public PreProcess() {

    }

    public PreProcess(String file, String dict) {
        this.dict = new File(dict);
        this.file = new File(file);
        dictionary = new HashMap<Integer, String>();
    }

    public void loadDict() {
        try {
            Scanner lea = new Scanner(this.dict);

            while (lea.hasNext() == true) {
                String line = lea.nextLine();
                line = line.trim();
                dictionary.put(line.hashCode(), line);
            }
            System.out.println(dictionary.size());
            lea.close();
        } catch (Exception e) {

        }
    }

    public boolean isDigit(String word) {
        try {
            Long.parseLong(word);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void filter() {
        try {
            Scanner lea = new Scanner(file);
            File escritura = new File("./dataFiles/processedDS.csv");
            if (!escritura.exists()) {
                escritura.createNewFile();
            }
            FileWriter fw = new FileWriter(escritura);
            BufferedWriter bw = new BufferedWriter(fw);
            while (lea.hasNext() == true) {
                String line = lea.nextLine();

                // line = line.replaceAll("[#$+\\[\\]@%╠►=♥❤^💗░██▓:/(.\\!“”;)€°~;*?\"]", "");
                line = line.replaceAll("[,'\\-_]", " ");
                line = line.replaceAll("[^A-Za-z ]", "");
                line = line.toLowerCase().replaceAll("co op", "co-op");
                // line = line.replaceAll(" ", " ").replaceAll(" ", " ");
                String lineFiltered = "";
                String[] array = line.split(" ");
                for (int i = 0; i < array.length; i++) {
                    array[i].trim();
                    if (dictionary.get(array[i].hashCode()) == null && !isDigit(array[i]) && !array[i].equals("")) {
                        if (lineFiltered.equals("")) {
                            lineFiltered += array[i];
                        } else {
                            lineFiltered += " " + array[i];
                        }
                    }
                }
                lineFiltered += "\n";
                bw.append(lineFiltered);
            }
            bw.close();
            fw.close();
            lea.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}