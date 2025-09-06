package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PalabrasDAO {
    // Variables para la conexión
    private Conexion conexion = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public Palabras obtenerPalabraAleatoria() {
        Palabras palabraEncontrada = null;
        String sql = "CALL sp_obtenerpalabrarandom()";
        
        try {
            con = conexion.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                palabraEncontrada = new Palabras();
                palabraEncontrada.setCodigoPalabra(rs.getInt("codigopalabra"));
                palabraEncontrada.setPalabra(rs.getString("palabra"));
                palabraEncontrada.setPista(rs.getString("pista"));
                palabraEncontrada.setCategoria(rs.getString("categoria"));
            }
            
        } catch (Exception e) {
            System.out.println("Error en obtenerPalabraAleatoria: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexiones: " + e.getMessage());
            }
        }
        return palabraEncontrada;
    }
}