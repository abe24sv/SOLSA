
package Interfaz;

public class Link {
    private String texto,url;
    
    public Link(String texto,String url){
        this.texto=texto;
        this.url=url;
    }
    
    public void settexto(String texto){
        this.texto=texto;
    }
    public void seturl(String url){
        this.url=url;
    }
    public String gettexto(){
        return texto;
    }
    public String geturl(){
        return url;
    }
}
