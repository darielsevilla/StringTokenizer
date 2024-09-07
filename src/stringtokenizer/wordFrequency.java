package stringtokenizer;

public class wordFrequency {
    private String palabra = "";
    private int freq = 0;

    public wordFrequency(String palabra, int freq) {
        this.palabra = palabra;
        this.freq = freq;
    }

    public String getPalabra() {
        return palabra;
    }
    
    public int getFreq() {
        return freq;
    }
    
    public boolean esIgual(wordFrequency par) {
        String[] otroPar = par.getPalabra().split(" ");
        if (palabra.contains(otroPar[0]) && palabra.contains(otroPar[1]))
            freq += par.getFreq();
        return (palabra.contains(otroPar[0]) && palabra.contains(otroPar[1]));
    }

    @Override
    public String toString() {
        return "" + palabra + "\t\t\t\t-> " + freq;
    }
}
