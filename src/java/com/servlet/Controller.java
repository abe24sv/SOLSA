package com.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CrudDao;
import com.model.Producto;
import com.model.Categoria;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/product.jsp";
    private static String LIST_USER = "/ListProducts.jsp";
    private CrudDao dao;

    public Controller() {
        super();
        dao = new CrudDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            dao.deleteProduct(name,description);
            forward = LIST_USER;
            request.setAttribute("productos", dao.getAllProducts());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Producto producto = dao.getProductById(name,description);
            request.setAttribute("producto", producto);
        } else if (action.equalsIgnoreCase("ListProducts")){
            forward = LIST_USER;
            request.setAttribute("productos", dao.getAllProducts());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Producto producto = new Producto();
        String description;
        int cantidad;
        Categoria subcategoria;
        int categoria;
        String image;
        String brand;
                

            String name = request.getParameter("nombre");

                                
        if (request.getParameter("descripcion") != null) {
            description = request.getParameter("descripcion");
            producto.setdescripcion(description);
            }

        if (request.getParameter("cantidad") != null) {
            cantidad = Integer.parseInt(request.getParameter("cantidad"));
            producto.setcantidad(cantidad);
            }
                                
        if (request.getParameter("id_subcategoria") != null) {
            subcategoria = (Categoria)request.getAttribute("id_subcategoria");
            producto.setid_subcategoria(subcategoria);
            }
                                
        if (request.getParameter("id_categoria") != null) {
            categoria = Integer.parseInt(request.getParameter("id_categoria"));
            producto.setcantidad(categoria);
            }
                                
        if (request.getParameter("imagen") != null) {
            image = request.getParameter("imagen");
            producto.setimagen(image);
            }
                                
        if (request.getParameter("id_marca") != null) {
                brand = request.getParameter("id_marca");
                producto.setmarca(brand);
        }
        
        if (request.getParameter("nombre") != null) {
            producto.setnombre(request.getParameter(name));
            dao.addProduct(producto);
        }
        else
        {
            producto.setnombre(request.getParameter(name));
            dao.updateProduct(producto);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("productos", dao.getAllProducts());
        view.forward(request, response);
    }
}

