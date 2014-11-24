/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class modificarSal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String notify="",url="/index.jsp";
        
        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));
        
        Connection con=aux.connect();
        int venta = Integer.parseInt(request.getParameter("mod"));
        String idstring[] = request.getParameterValues("id[]");
        LinkedList<Integer> id = new LinkedList<Integer>();
        for(String s : idstring){
            id.add(Integer.parseInt(s));
        }
        String idstringn[] = request.getParameterValues("producto[]");
        LinkedList<Integer> idn = new LinkedList<Integer>();
        for(String s : idstringn){
            idn.add(Integer.parseInt(s));
        }
        String c[] = request.getParameterValues("cantidad[]");
        LinkedList<Integer> cantidad = new LinkedList<Integer>();
        for(String s : c){
            cantidad.add(Integer.parseInt(s));
        }
        if(con!=null){
            
            try{
                for(int i=0;i<id.size();i++){
                    if(id.get(i)!=0){
                        if(idn.get(i)!=0 && cantidad.get(i)>=1){
                            PreparedStatement query=con.prepareStatement("UPDATE compra_prod SET id_producto=?,cantidad=?  WHERE id_compra = ? AND id_producto = ?");
                             query.setInt(1, idn.get(i));
                             query.setInt(2, cantidad.get(i));
                             query.setInt(3, venta);
                             query.setInt(4, id.get(i));
                             query.executeUpdate();
                             query.close();
                        }else{
                                PreparedStatement query=con.prepareStatement("DELETE FROM compra_prod WHERE id_compra = ? AND id_producto = ?");
                                query.setInt(1, venta); 
                                query.setInt(2, id.get(i));
                                query.executeUpdate();
                                query.close();
                            
                        }
                    }else{
                        if(idn.get(i)!=0 && cantidad.get(i)>0){
                            PreparedStatement query=con.prepareStatement("INSERT INTO compra_prod(id_producto,id_compra,cantidad) VALUES ( ? , ? , ? );");
                             query.setInt(1, idn.get(i));
                             query.setInt(2, venta);
                             query.setInt(3, cantidad.get(i));
                             query.executeUpdate();
                             query.close();
                        }
                    }
                }
                
               url="/ListSalAll";  
                
                aux.disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            
        }else{
            notify="¡Ups! Tenemos problemas Técnicos";
            url="/index.jsp";
        }
        
        RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
        if(disp!=null){
            disp.forward(request, response);
        }
    }

}
