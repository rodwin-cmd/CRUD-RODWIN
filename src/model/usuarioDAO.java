
package model;

import java.sql.Connection;
import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class usuarioDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public int Agregar(usuario u){
        String sql ="INSERT INTO users (Nombre, Apellido, Email, Telefono) VALUES (?,?,?,?) ";
        try {
            
            con =  conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getTelefono());
            ps.executeUpdate();
            
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    public List listar(){
        List<usuario> datos = new ArrayList<>();
        String sql = "SELECT * FROM users";
        
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                usuario u = new usuario();
                u.setIdusers(rs.getString(1));
                u.setNombre(rs.getString(2));
                u.setApellido(rs.getString(3));
                u.setEmail(rs.getNString(4));
                u.setTelefono(rs.getNString(5));
                datos.add(u);
            }
          
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return datos;
    }
    
    public void Eliminar(int idusers){
        String sql = "DELETE FROM users WHERE idusers = " +idusers;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
                    
        }
    }  
    
    public int Actualizar(usuario u){
        int r = 0;
        String sql = "UPDATE users SET Nombre = ?, Apellido = ?, Email = ?, Telefono = ? WHERE idusers = ? ";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getIdusers());
            
            r = ps.executeUpdate();
            
            if (r == 1){
                return 1 ;
            }else {
             return 0;   
            }
                  
        } catch (Exception e) {
            System.out.println(e);
            return 0;
                   
                    
        }
    }
    
        
}
