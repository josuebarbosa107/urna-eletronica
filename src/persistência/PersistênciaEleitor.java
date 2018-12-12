
package persistência;

import java.util.ArrayList;
import modelo.Eleitor;

public class PersistênciaEleitor {
    
    private static ArrayList<Eleitor> listaEleitores;
    static{
        listaEleitores = new ArrayList<Eleitor>();
    }
    
    public Eleitor buscaEleitor(String titulo){
        System.out.println("Busca: "+titulo);
        for(Eleitor e : listaEleitores){
            if(e.getTitulo().equalsIgnoreCase(titulo)){
                System.out.println("Achou: "+titulo);
                return e;
            }
        }
        System.out.println("Não Achou: "+titulo);
        return null;
    }
    
    public boolean salvaEleitor(Eleitor e){
        System.out.println("Salva: "+e.getNome());
        listaEleitores.add(e);
        return true;
    }
    
    public ArrayList<Eleitor> listaEleitores(){
        return listaEleitores;
    }
}
