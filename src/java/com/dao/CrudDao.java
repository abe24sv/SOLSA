package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.DataAccessObject;
import com.model.Producto;
import com.model.Categoria;

public class CrudDao {

private Connection dbConnection;
private PreparedStatement pStmt;

public CrudDao() {
        dbConnection = DataAccessObject.getConnection();
}

public void addProduct(Producto producto) {
        String insertQuery = "INSERT INTO producto(nombre, descripcion, cantidad, id_categoria, id_subcategoria, imagen, id_marca ) VALUES (?,?,?,?,?,?,?)";
        try {
                pStmt = dbConnection.prepareStatement(insertQuery);
                pStmt.setString(1, producto.getnombre());
                pStmt.setString(2, producto.getdescripcion());
                pStmt.setInt(3, producto.getcantidad());
                pStmt.setObject(4,producto.getid_categoria());
                pStmt.setObject(5,producto.getid_subcategoria());
                pStmt.setString(6,producto.getimagen());
                pStmt.setString(7,producto.getmarca());
                pStmt.executeUpdate();
        } catch (SQLException e) {
                System.err.println(e.getMessage());
        }
}

public void deleteProduct(String name, String description) {
        String deleteQuery = "DELETE FROM producto WHERE nombre = ? AND descripcion";
        try {
                pStmt = dbConnection.prepareStatement(deleteQuery);
                pStmt.setString(1, name);
                pStmt.setString(2, description);
                pStmt.executeUpdate();
        } catch (SQLException e) {
                System.err.println(e.getMessage());
        }
}

public void updateProduct(Producto producto)  {
        String updateQuery = "UPDATE producto SET nombre = ?, " +
                        "descripcion = ?, cantidad = ?, id_categoría = ?, id_subcategoria = ?, imagen = ?, id_marca = ?  WHERE id = ?";
        try {
                pStmt = dbConnection.prepareStatement(updateQuery);
                pStmt.setString(1, producto.getnombre());
                pStmt.setString(2, producto.getdescripcion());
                pStmt.setInt(3, producto.getcantidad());
                pStmt.setObject(4,producto.getid_categoria());
                pStmt.setObject(5,producto.getid_subcategoria());
                pStmt.setString(6,producto.getimagen());
                pStmt.executeUpdate();

        } catch (SQLException e) {
                System.err.println(e.getMessage());
        }
}

public List<Producto> getAllProducts() {
       
        List<Producto> productos = new ArrayList<Producto>();
        
        String query = "SELECT * FROM producto ORDER BY id";
        try { 
                Statement stmt = dbConnection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                        Producto producto = new Producto();      
                        producto.setid_categoria((Categoria)rs.getObject("id_categoría")); 
                        producto.setid_subcategoria((Categoria)rs.getObject("id_subcategoria"));
                        producto.setdescripcion(rs.getString("descripcion"));
                        producto.setnombre(rs.getString("nombre"));
                        producto.setimagen(rs.getString("imagen"));
                        producto.setmarca(rs.getString("id_marca"));
                        producto.setcantidad(rs.getInt("cantidad"));
                        
                        productos.add(producto);
                }
        } catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return productos;
    }
public Producto getProductById(String name, String description) {
        Producto producto = new Producto();
        try {
            PreparedStatement preparedStatement = dbConnection.
                    prepareStatement("select * from producto where name=? AND description=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                        producto.setid_categoria((Categoria)rs.getObject("id_categoría")); 
                        producto.setid_subcategoria((Categoria)rs.getObject("id_subcategoria"));
                        producto.setdescripcion(rs.getString("descripcion"));
                        producto.setnombre(rs.getString("nombre"));
                        producto.setimagen(rs.getString("imagen"));
                        producto.setmarca(rs.getString("id_marca"));
                        producto.setcantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }
}

