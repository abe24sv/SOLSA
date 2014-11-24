package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CrudDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Producto;
import com.model.Categoria;

public class Controller extends HttpServlet {
private static final long serialVersionUID = 1L;
private CrudDao dao;

public Controller() {
	dao = new CrudDao();
}

protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
}

protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
                String action = request.getParameter("action");
                List<Producto> productList = new ArrayList<Producto>();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                response.setContentType("application/json");

                if (action != null) {
                        if (action.equals("list")) {
                        try {
                                // Fetch Data from Student Table
                                productList = dao.getAllProducts();
                                // Convert Java Object to Json
                                String jsonArray = gson.toJson(productList);

                                // Return Json in the format required by jTable plugin
                                jsonArray = "{\"Result\":\"OK\",\"Records\":"+ jsonArray + "}";
                                response.getWriter().print(jsonArray);
                                } catch (Exception e) {
                                        String error = "{\"Result\":\"ERROR\",\"Message\":" + e.getMessage()+ "}";
                                        response.getWriter().print(error);
                                        System.err.println(e.getMessage());
                                }
                        } else if (action.equals("create") || action.equals("update")) {
                                Producto producto = new Producto();
                                if (request.getParameter("id") != null) {
                                int productId = Integer.parseInt(request.getParameter("id"));
                                producto.setid(productId);
                                }

                                if (request.getParameter("nombre") != null) {
                                        String name = request.getParameter("nombre");
                                        producto.setnombre(name);
                                }
                                
                                if (request.getParameter("descripcion") != null) {
                                        String description = request.getParameter("descripcion");
                                        producto.setdescripcion(description);
                                }

                                if (request.getParameter("cantidad") != null) {
                                        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                                        producto.setcantidad(cantidad);
                                }
                                
                                if (request.getParameter("id_subcategoria") != null) {
                                        Categoria subcategoria = (Categoria)request.getAttribute("id_subcategoria");
                                        producto.setid_subcategoria(subcategoria);
                                }
                                
                                if (request.getParameter("id_categoria") != null) {
                                        int categoria = Integer.parseInt(request.getParameter("id_categoria"));
                                        producto.setcantidad(categoria);
                                }
                                
                                if (request.getParameter("imagen") != null) {
                                        String image = request.getParameter("imagen");
                                        producto.setimagen(image);
                                }
                                
                                if (request.getParameter("id_marca") != null) {
                                        String brand = request.getParameter("id_marca");
                                        producto.setmarca(brand);
                                }

                                try {
                                        if (action.equals("create")) {
                                                // Create new record
                                                dao.addProduct(producto);
                                                // Convert Java Object to Json
                                                String json = gson.toJson(producto);
                                                // Return Json in the format required by jTable plugin
                                                String jsonData = "{\"Result\":\"OK\",\"Record\":"+ json + "}";
                                                response.getWriter().print(jsonData);
                                        } else if (action.equals("update")) {
                                                // Update existing record
                                                dao.updateProduct(producto);
                                                // Convert Java Object to Json
                                                String json = gson.toJson(producto);
                                                String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
                                                response.getWriter().print(jsonData);
                                        }
                                } catch (Exception e) {
                                        String error = "{\"Result\":\"ERROR\",\"Message\":" + e.getMessage()+ "}";
                                        response.getWriter().print(error);
                                }

                        } else if (action.equals("delete")) {
                                try {
                                        // Delete record
                                        if (request.getParameter("id") != null) {
                                                int productId = Integer.parseInt(request
                                                                .getParameter("id"));
                                                dao.deleteProduct(productId);
                                                String jsonData = "{\"Result\":\"OK\"}";
                                                response.getWriter().print(jsonData);
                                        }
                                } catch (Exception e) {
                                        String error = "{\"Result\":\"ERROR\",\"Message\":" + e.getMessage()+ "}";
                                        response.getWriter().print(error);
                                }
                        }
                }
                }
                }