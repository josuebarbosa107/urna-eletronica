
package persistência;

import java.util.ArrayList;
import modelo.Eleitor;

public class PersistênciaUrnaEletronica {
    private static ArrayList<Eleitor> votaram;
    private static int nulosP;
    private static int brancosP;
    private static int nulosV;
    private static int brancosV;
    private static int zona;
    static{
        votaram = new ArrayList<Eleitor>();
        nulosP = 0;
        brancosP = 0;
        nulosV = 0;
        brancosV = 0;
        zona = 172;
    }
    
    public Eleitor buscaEleitorVotou(String titulo){
        System.out.println("Busca votou: "+titulo);
        for(Eleitor e : votaram){
            if(e.getTitulo().equalsIgnoreCase(titulo)){
                System.out.println("Achou: "+titulo);
                return e;
            }
        }
        System.out.println("Não Achou: "+titulo);
        return null;
    }
    
    public void IncrementaNulosP(){
        this.nulosP++;
    }
    
    public void IncrementaBrancosP(){
        this.brancosP++;
    }
    
    public int RetornaBrancosP(){
        return this.brancosP;
    }
    
    public int RetornaNulosP(){
        return this.nulosP;
    }
    
    public void IncrementaNulosV(){
        this.nulosV++;
    }
    
    public void IncrementaBrancosV(){
        this.brancosV++;
    }
    
    public int RetornaBrancosV(){
        return this.brancosV;
    }
    
    public int RetornaNulosV(){
        return this.nulosV;
    }
    
    public int RetornaZona (){
        return this.zona;
    }
    
    public boolean salvaEleitorVotou(Eleitor e){
        System.out.println("Salva: "+e.getNome());
        votaram.add(e);
        return true;
    }
    
    public ArrayList<Eleitor> listaEleitoresVotou(){
        return votaram;
    }
}
