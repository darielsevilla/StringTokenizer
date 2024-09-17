package stringtokenizer;

import java.io.*;
import java.util.*;

public class FrequencyAnalysis {
    private String singlesOutput = "./dataFiles/results/part-00000",
            paresOutput = "./dataFiles/resultsdouble/part-00000",
            linea = "";
    private int i = 0, minimum = 5000, top = 0;

    /*
     * public static void main(String[] args) {
     * freqAnalyzer();
     * // pairFreqAnalyzer();
     * }
     */
    public FrequencyAnalysis(int top) {
        this.top = top;
    }

    public void freqAnalyzer() {
        ArrayList<wordFrequency> palabras = new ArrayList<>();

        try (BufferedReader lectura = new BufferedReader(new FileReader(singlesOutput))) {
            while ((linea = lectura.readLine()) != null) {
                if (!linea.contains("$")) {

                    String[] parts = linea.split("\\s+");

                    if (Integer.parseInt(parts[1]) >= minimum)
                        palabras.add(new wordFrequency(parts[0], Integer.parseInt(parts[1])));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        palabras.sort(Comparator.comparing(wordFrequency::getFreq).reversed());

        System.out.println(palabras.size());
        while (i < palabras.size() && i < top) {
            System.out.println((i + 1) + ". " + palabras.get(i));
            i++;
        }

    }

    public void pairFreqAnalyzer() {
        ArrayList<wordFrequency> pares = new ArrayList<>();

        try (BufferedReader lectura = new BufferedReader(new FileReader(paresOutput))) {
            while ((linea = lectura.readLine()) != null) {

                if (!linea.contains("$")) {
                    String[] parts = linea.split("\\s+");
                    String contenido = parts[0] + " " + parts[1];

                    if (Integer.parseInt(parts[2]) >= minimum)
                        pares.add(new wordFrequency(contenido.replace(",", " "), Integer.parseInt(parts[2])));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < pares.size(); i++) {
            wordFrequency par = pares.get(i);
            for (int j = 0; j < pares.size(); j++) {
                if (i != j) {
                    wordFrequency otro = pares.get(j);
                    if (par.esIgual(otro))
                        pares.remove(otro);
                }
            }
        }

        pares.sort(Comparator.comparing(wordFrequency::getFreq).reversed());

        int i = 0;
        while (i < pares.size() && i < top) {
            System.out.println((i + 1) + ". " + pares.get(i));
            i++;
        }
    }

}