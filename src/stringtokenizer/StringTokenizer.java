package stringtokenizer;

public class StringTokenizer {
    public static void main(String[] args) {
        switch (args[0]) {
            case "1":
                System.out.println("Pre Process");
                PreProcess pprocess = new PreProcess("./dataFiles/dataset.csv", "./dataFiles/DiccionarioFiltro.txt");
                pprocess.loadDict();
                pprocess.filter();
                break;
            case "2":
                System.out.println("Frequency Analysis");
                // ./StringTokenizer 2 1 10
                FrequencyAnalysis freq = new FrequencyAnalysis(Integer.parseInt(args[2]));
                if (args[1].equals("1")) {
                    freq.freqAnalyzer();
                } else if (args[1].equals("2")) {
                    freq.pairFreqAnalyzer();
                } else {
                    System.out.println("*Debe ingresar si la busqueda sera de una o dos palabras mas frecuentes");
                }
                break;
        }
    }
}
