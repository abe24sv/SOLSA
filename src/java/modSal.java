/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.model.Cliente;
import com.model.Producto;
import com.model.Categoria;
import com.model.Usuario;
import com.model.UsuarioAdministrador;
import com.model.UsuarioAlmacen;
import com.model.UsuarioAprobador;
import com.model.UsuarioCapturista;
import com.model.UsuarioVentas;
import com.model.Venta;
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


public class modSal extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        if(con!=null){
            
            try{
                PreparedStatement query=con.prepareStatement("SELECT * FROM venta WHERE id_compra = ?");
                query.setInt(1, id);
                LinkedList<Integer> compras = new LinkedList<Integer>();
                LinkedList<Producto> productos = new LinkedList<Producto>();
                HashMap<Integer,LinkedList<Producto>> compraProducto = new HashMap<Integer,LinkedList<Producto>>();
                HttpSession session=request.getSession();
                Venta venta = new Venta();
                ResultSet result=query.executeQuery();
                if(result.next()){
                     compras.add(result.getInt("venta.id_compra"));
                }
                query.close();  
                for(int i : compras){
                    PreparedStatement query2=con.prepareStatement("SELECT * FROM (((compra_prod JOIN producto ON compra_prod.id_producto = producto.id) JOIN categoria ON producto.id_categoria = categoria.id) JOIN subcategoria ON producto.id_subcategoria = subcategoria.id) JOIN marca ON producto.id_marca = marca.id WHERE id_compra = ?");
                    query2.setInt(1, i);
                    result=query2.executeQuery();
                    while(result.next()){
                        productos.add(new Producto(result.getInt("producto.id"),new Categoria(result.getInt("categoria.id"),result.getString("categoria.nombre")),new Categoria(result.getInt("subcategoria.id"),result.getString("subcategoria.nombre")),result.getString("producto.nombre"),result.getString("marca.nombre"),result.getString("producto.descripcion"),result.getString("producto.imagen"),result.getInt("compra_prod.cantidad")));
                    }
                    compraProducto.put(i, productos);
                    query2.close();
                }
               PreparedStatement query3=con.prepareStatement("SELECT * FROM (((venta JOIN compra ON venta.id_compra = compra.id) JOIN cliente ON compra.id_cliente = cliente.id) JOIN usuario ON compra.id_usuario = usuario.id),usuario AS vendedor WHERE venta.id_usuario = vendedor.id AND compra.id = ?;");
               query3.setInt(1, id);
               result=query3.executeQuery();
               if(result.next()){
                   Usuario comprador;
                   Usuario vendedor;
                   Cliente cliente = new Cliente(result.getInt("cliente.id"),result.getString("cliente.nombre"),result.getString("cliente.logo"));
                   switch(result.getInt("usuario.tipo")){
                       case 0:
                           comprador = new UsuarioAdministrador(result.getInt("usuario.id"),result.getString("usuario.nombre"),result.getString("usuario.apellido"),result.getInt("usuario.tipo"));
                           break;
                        case 1:
                           comprador = new UsuarioVentas(result.getInt("usuario.id"),result.getString("usuario.nombre"),result.getString("usuario.apellido"),result.getInt("usuario.tipo"));
                           break;
                        case 2:
                           comprador = new UsuarioAlmacen(result.getInt("usuario.id"),result.getString("usuario.nombre"),result.getString("usuario.apellido"),result.getInt("usuario.tipo"));
                           break;
                        case 3:
                           comprador = new UsuarioCapturista(result.getInt("usuario.id"),result.getString("usuario.nombre"),result.getString("usuario.apellido"),result.getInt("usuario.tipo"),cliente);
                           break;
                        case 4:
                            comprador = new UsuarioAprobador(result.getInt("usuario.id"),result.getString("usuario.nombre"),result.getString("usuario.apellido"),result.getInt("usuario.tipo"),cliente);
                            break;
                        default:comprador = new UsuarioCapturista(result.getInt("usuario.id"),result.getString("usuario.nombre"),result.getString("usuario.apellido"),result.getInt("usuario.tipo"),cliente);
                            
                            break;
                   }
                   switch(result.getInt("vendedor.tipo")){
                       case 0:
                           vendedor = new UsuarioAdministrador(result.getInt("vendedor.id"),result.getString("vendedor.nombre"),result.getString("vendedor.apellido"),result.getInt("vendedor.tipo"));
                           break;
                        case 1:
                           vendedor = new UsuarioVentas(result.getInt("vendedor.id"),result.getString("vendedor.nombre"),result.getString("vendedor.apellido"),result.getInt("vendedor.tipo"));
                           break;
                        case 2:
                           vendedor = new UsuarioAlmacen(result.getInt("vendedor.id"),result.getString("vendedor.nombre"),result.getString("vendedor.apellido"),result.getInt("vendedor.tipo"));
                           break;
                        case 3:
                           vendedor = new UsuarioCapturista(result.getInt("vendedor.id"),result.getString("vendedor.nombre"),result.getString("vendedor.apellido"),result.getInt("vendedor.tipo"),cliente);
                           break;
                        case 4:
                            vendedor = new UsuarioAprobador(result.getInt("vendedor.id"),result.getString("vendedor.nombre"),result.getString("vendedor.apellido"),result.getInt("vendedor.tipo"),cliente);
                            break;
                        default:
                            vendedor = new UsuarioCapturista(result.getInt("usuario.id"),result.getString("usuario.nombre"),result.getString("usuario.apellido"),result.getInt("usuario.tipo"),cliente);
                            break;
                   }
                     venta = new Venta(result.getInt("venta.id_compra"),result.getInt("compra.estado"),result.getString("compra.fecha_inicio"),result.getString("compra.fecha_entrega"),compraProducto.get(result.getInt("venta.id_compra")),cliente,comprador,vendedor);
                }
               query3.close();
               url="/main.jsp";  
                PreparedStatement query4=con.prepareStatement("SELECT * FROM ((producto JOIN categoria ON producto.id_categoria = categoria.id) JOIN subcategoria ON producto.id_subcategoria = subcategoria.id) JOIN marca ON producto.id_marca = marca.id");
                result = query4.executeQuery();
                LinkedList<Producto> p = new LinkedList<Producto>();
                while(result.next()){
                    p.add(new Producto(result.getInt("producto.id"),new Categoria(result.getInt("categoria.id"),result.getString("categoria.nombre")),new Categoria(result.getInt("subcategoria.id"),result.getString("subcategoria.nombre")),result.getString("producto.nombre"),result.getString("marca.nombre"),result.getString("producto.descripcion"),result.getString("producto.imagen"),result.getInt("producto.cantidad")));
                }
                aux.disconnect();
                request.setAttribute("modificacion",1);
                request.setAttribute("venta", venta);
                request.setAttribute("productos", p);
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
