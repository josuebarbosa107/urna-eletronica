package controle;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import modelo.Candidato;
import modelo.Cargo;
import persistência.PersistênciaCandidato;

public class ControleCandidato {

    private PersistênciaCandidato persistencia;

    public ControleCandidato() {
        persistencia = new PersistênciaCandidato();
    }

    public Candidato buscaCandidato(int numero) {
        return persistencia.buscaCandidato(numero);
    }

    public Candidato buscaCandidato(String partido) {
        return persistencia.buscaCandidato(partido);
    }

    public void IncrementaVotos(int num) {
        this.persistencia.IncrementaVotos(num);
    }

    public boolean salvaCandidato(String nome, int numero, String partido, String cargo, String caminho) {

        if (buscaCandidato(numero) != null) {
            System.out.println("Candidato já existe!");
            return false;
        }

        if ((cargo).equalsIgnoreCase("Prefeito") && buscaCandidato(partido) != null) {
            System.out.println("Prefeitos do mesmo partido!");
            return false;
        }

        Cargo tipoCargo;
        if (cargo.equalsIgnoreCase("Prefeito")) {
            tipoCargo = Cargo.PREFEITO;
        } else if (cargo.equalsIgnoreCase("Vereador")) {
            tipoCargo = Cargo.VEREADOR;
        } else {
            return false;
        }

        Candidato candidato = new Candidato(nome, numero, partido, tipoCargo, caminho);
        if (persistencia.salvaCandidato(candidato)) {
            System.out.println("Candidato salvo com sucesso");
            return true;
        } else {
            System.out.println("Falha ao salvar candidato");
            return false;
        }
    }

    public ArrayList<Candidato> listaCandidatos() {
        return persistencia.listaCandidatos();
    }
    
    public void Ordena(ArrayList<Candidato> c){
        Collections.sort(c);
    }

    public void LerCandidatos() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("Candidatos.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";
        while ((linha = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(linha, "|");
            String nome = st.nextToken();
            String numero = st.nextToken();
            String partido = st.nextToken();
            String cargo = st.nextToken();
            String caminho = st.nextToken();
            if (numero.length() != 2 && numero.length() != 5) {
                System.out.println("Erro ao ler. Arquivo cont�m informa��es incorretas");
            } else {
                salvaCandidato(nome, Integer.parseInt(numero), partido, cargo, caminho);
            }
        }
        br.close();
        fr.close();
    }
}
