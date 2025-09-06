package modelo;

public class Palabras {
    private int codigoPalabra;
    private String palabra;
    private String pista;
    private String categoria;

    public Palabras() {
    }

    public Palabras(int codigoPalabra, String palabra, String pista, String categoria) {
        this.codigoPalabra = codigoPalabra;
        this.palabra = palabra;
        this.pista = pista;
        this.categoria = categoria;
    }

    // Getters y Setters
    public int getCodigoPalabra() {
        return codigoPalabra;
    }

    public void setCodigoPalabra(int codigoPalabra) {
        this.codigoPalabra = codigoPalabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Palabras{" + "\nCodigo Palabra: " + codigoPalabra 
                + "\nPalabra: " + palabra 
                + "\nPista: " + pista 
                + "\nCategoria: " + categoria + '}';
    }
}
