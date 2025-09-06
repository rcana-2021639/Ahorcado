package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public Usuarios validar(String correo, String contraseña) {
        String sql = "call sp_validarusuario(?, ?);";
        Usuarios usuario = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuarios();
                usuario.setCodigoUsuario(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setApellidoUsuario(rs.getString(3));
                usuario.setCorreoUsuario(rs.getString(4));
                usuario.setContraseñaUsuario(rs.getString(5));
                usuario.setFechaRegistro(rs.getDate(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public int agregar(Usuarios usuario) {
        String sql = "call sp_agregarusuario(?, ?, ?, ?);";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getApellidoUsuario());
            ps.setString(3, usuario.getCorreoUsuario());
            ps.setString(4, usuario.getContraseñaUsuario());
            ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public Usuarios buscarPorCorreo(String correo) {
        String sql = "call sp_buscarusuariocorreo(?);";
        Usuarios usuario = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuarios();
                usuario.setCodigoUsuario(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setApellidoUsuario(rs.getString(3));
                usuario.setCorreoUsuario(rs.getString(4));
                usuario.setContraseñaUsuario(rs.getString(5));
                usuario.setFechaRegistro(rs.getDate(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
