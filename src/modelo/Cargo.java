
package modelo;

public enum Cargo {
    PREFEITO("Prefeito"),
    VEREADOR("Vereador");
    
    private String cargo;
    
    private Cargo(String cargo){
        this.cargo = cargo;
    }
    
    public String getCargo(){
        return this.cargo;
    }
}

