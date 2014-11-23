
package Interfaz;

import java.util.LinkedList;

public class Opcion {
    private String titulo;
    private DivLateral lateral;
    
    public Opcion(String titulo,DivLateral lateral){
        this.titulo=titulo;
        this.lateral=lateral;
    }
    
    public void settitulo(String titulo){
        this.titulo=titulo;
    }
    public void setlateral(DivLateral lateral){
        this.lateral=lateral;
    }
    
    public String gettitulo(){
        return titulo;
    }
    public DivLateral getlateral(){
        return lateral;
    }
    
}
