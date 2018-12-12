
package controle;

import java.util.ArrayList;
import modelo.Eleitor;
import persistência.PersistênciaUrnaEletronica;

public class ControleUrnaEletronica {
    private PersistênciaUrnaEletronica persistencia;

    public ControleUrnaEletronica() {
        persistencia = new PersistênciaUrnaEletronica();
    }

    public Eleitor buscaEleitorVotou(String titulo) {
        return persistencia.buscaEleitorVotou(titulo);
    }

    public boolean salvaEleitorVotou(Eleitor eleitor) {
        
        if (persistencia.salvaEleitorVotou(eleitor)) {
            System.out.println("Eleitor salvo com sucesso em votaram");
            return true;
        } else {
            System.out.println("Falha ao salvar eleitor em votaram");
            return false;
        }
    }

    public void IncrementaNulosP(){
        this.persistencia.IncrementaNulosP();
    }
    
    public void IncrementaBrancosP(){
        this.persistencia.IncrementaBrancosP();
    }
    
    public int RetornaBrancosP(){
        return this.persistencia.RetornaBrancosP();
    }
    
    public int RetornaNulosP(){
        return this.persistencia.RetornaNulosP();
    }
    
    public void IncrementaNulosV(){
        this.persistencia.IncrementaNulosV();
    }
    
    public void IncrementaBrancosV(){
        this.persistencia.IncrementaBrancosV();
    }
    
    public int RetornaBrancosV(){
        return this.persistencia.RetornaBrancosV();
    }
    
    public int RetornaNulosV(){
        return this.persistencia.RetornaNulosV();
    }
    
    public int RetornaZona(){
        return this.persistencia.RetornaZona();
    }
    
    public ArrayList<Eleitor> listaEleitorVotaram() {
        return persistencia.listaEleitoresVotou();
    }
    
}
