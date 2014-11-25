
package Interfaz;

import java.util.LinkedList;

public class ListaTitulada<tipo> {
    public String titulo,imagen;
    public LinkedList<tipo> registros;
    
    public ListaTitulada(String titulo,String imagen,LinkedList<tipo> registros){
        this.titulo=titulo;
        this.imagen=imagen;
        this.registros=registros;
    }
    
    public ListaTitulada(String titulo,LinkedList<tipo> registros){
        this.titulo=titulo;
        this.registros=registros;
    } 
    
    public void settitulo(String titulo){
        this.titulo=titulo;
    }
    public void setimagen(String imagen){
        this.imagen=imagen;
    }
    public void setregistros(LinkedList<tipo> registros){
        this.registros=registros;
    }
    
    public String gettitulo(){
        return titulo;
    }
    public String getimagen(){
        return imagen;
    }
    public LinkedList<tipo> getregistros(){
        return registros;
    }
    
}
