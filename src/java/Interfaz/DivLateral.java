
package Interfaz;

import java.util.LinkedList;

public class DivLateral {
 
    private boolean searchbar;
    private String searchurl,advsearchurl;
    private LinkedList<Link> opciones;
    
    public DivLateral(boolean searchbar,String searchurl,String advsearchurl,Link... opciones){
        this.searchbar=searchbar;
        this.searchurl=searchurl;
        this.advsearchurl=advsearchurl;
        this.opciones=new LinkedList<Link>();
        for(int cont=0;cont<opciones.length;cont+=1){
            this.opciones.add(opciones[cont]);
        }
    }
    
    public DivLateral(boolean searchbar,Link... opciones){
        this.searchbar=searchbar;
        this.opciones=new LinkedList<Link>();
        for(int cont=0;cont<opciones.length;cont+=1){
            this.opciones.add(opciones[cont]);
        }
    }
    
    public void setsearchbar(boolean searchbar){
        this.searchbar=searchbar;
    }
    public void setsearchurl(String searchurl){
        this.searchurl=searchurl;
    }
    public void setadvsearchurl(String advsearchurl){
        this.advsearchurl=advsearchurl;
    }
    public void setopciones(LinkedList<Link> opciones){
        this.opciones=opciones;
    }
    
    public boolean getsearchbar(){
        return searchbar;
    }
    public String getsearchurl(){
        return searchurl;
    }
    public String getadvsearchurl(){
        return advsearchurl;
    }
    public LinkedList<Link> getopciones(){
        return opciones;
    }
    
}
