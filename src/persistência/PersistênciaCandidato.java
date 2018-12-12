
package persistência;

import java.util.ArrayList;
import modelo.Candidato;

public class PersistênciaCandidato {
    
    private static ArrayList <Candidato> listaCandidatos;
    static {
        listaCandidatos = new ArrayList<Candidato>();
    }
    
    public Candidato buscaCandidato(int numero){
        System.out.println("Busca: "+numero);
        for(Candidato c : listaCandidatos){
            if(c.getNumero() == numero){
                System.out.println("Achou: "+numero);
                return c;
            }
        }
        System.out.println("Não Achou: "+numero);
        return null;
    }
    
    public Candidato buscaCandidato(String partido){ // Para verificar se existem dois prefeitos do mesmo partido
        System.out.println("Busca: "+partido);
        for(Candidato c : listaCandidatos){
            if(c.getPartido().equalsIgnoreCase(partido)){
                System.out.println("Achou: "+partido);
                return c;
            }
        }
        System.out.println("Não Achou: "+partido);
        return null;
    }
    
    public boolean salvaCandidato(Candidato c){
        System.out.println("Salva: "+c.getNome());
        listaCandidatos.add(c);
        return true;
    }
    
    public void IncrementaVotos(int num){
        for(Candidato c : listaCandidatos){
            if(c.getNumero() == num){
                c.IcrementaNumeroVotos();
            }
        }
    }
    
    public ArrayList<Candidato> listaCandidatos(){
        return listaCandidatos;
    }
    
}

