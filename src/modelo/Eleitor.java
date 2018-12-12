
package modelo;

public class Eleitor {
    private String nome;
    private String titulo;
    private int zonaEleitoral;
    private boolean votou;

    public Eleitor(String nome, String titulo, int zonaEleitoral) {
        this.nome = nome;
        this.titulo = titulo;
        this.zonaEleitoral = zonaEleitoral;
        this.votou = false;
    }

    public String getNome() {
        return nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getZonaEleitoral() {
        return zonaEleitoral;
    }

    public boolean getVotou() {
        return votou;
    }

    public void setVotou(boolean votou) {
        this.votou = votou;
    }
    
    
    
}
