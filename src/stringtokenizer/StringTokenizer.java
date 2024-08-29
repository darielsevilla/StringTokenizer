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
                break;
        }
    }
}
