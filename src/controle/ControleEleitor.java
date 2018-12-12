package controle;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import modelo.Eleitor;
import persistência.PersistênciaEleitor;
import persistência.*;

public class ControleEleitor {

    private PersistênciaEleitor persistencia;
    private PersistênciaUrnaEletronica PU;
    public ControleEleitor() {
        persistencia = new PersistênciaEleitor();
        PU = new persistência.PersistênciaUrnaEletronica();
    }

    public Eleitor buscaEleitor(String titulo) {
        return persistencia.buscaEleitor(titulo);
    }

    public boolean salvaEleitor(String nome, String titulo, int zona) {
        
        if( zona != PU.RetornaZona())
            return false;
            
        if (buscaEleitor(titulo) != null) {
            System.out.println("Eleitor já existe!");
            return false;
        }

        Eleitor eleitor = new Eleitor(nome, titulo, zona);
        if (persistencia.salvaEleitor(eleitor)) {
            System.out.println("Eleitor salvo com sucesso");
            return true;
        } else {
            System.out.println("Falha ao salvar eleitor");
            return false;
        }
    }

    public ArrayList<Eleitor> listaEleitor() {
        return persistencia.listaEleitores();
    }

    public void LerEleitores() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("Eleitores.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";
        while ((linha = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(linha, "|");
            String nome = st.nextToken();
            String titulo = st.nextToken();
            int zona = Integer.parseInt(st.nextToken());
            salvaEleitor(nome, titulo, zona);
        }
        br.close();
        fr.close();
    }
}
