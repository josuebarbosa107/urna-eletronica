
package modelo;

public class Candidato implements Comparable<Candidato> {
    private String nome;
    private int numero;
    private String partido;
    private Cargo cargo;
    private int numeroVotos;
    private String caminhoImagem;

    public Candidato(String nome, int numero, String partido, Cargo cargo, String caminho) {
        this.nome = nome;
        this.numero = numero;
        this.partido = partido;
        this.cargo = cargo;
        this.numeroVotos = 0;
        this.caminhoImagem = new String(caminho);
    }

    public int getNumeroVotos() {
        return numeroVotos;
    }

    public void IcrementaNumeroVotos() {
        this.numeroVotos++;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getPartido() {
        return partido;
    }

    public Cargo getCargo() {
        return cargo;
    }
    
    public String getCaminho(){
        return this.caminhoImagem;
    }
    
    public int compareTo(Candidato candidato){
        if(this.numeroVotos > candidato.numeroVotos)
            return -1;
        if(this.numeroVotos < candidato.numeroVotos)
            return 1;
        return 0;
    }
    
}
