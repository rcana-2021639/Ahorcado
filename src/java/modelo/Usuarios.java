package modelo;

import java.util.Date;

public class Usuarios {
    private int codigoUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String correoUsuario;
    private String contraseñaUsuario;
    private Date fechaRegistro;

    public Usuarios() {
    }

    public Usuarios(int codigoUsuario, String nombreUsuario, String apellidoUsuario, 
            String correoUsuario, String contraseñaUsuario, Date fechaRegistro) {
        this.codigoUsuario = codigoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.correoUsuario = correoUsuario;
        this.contraseñaUsuario = contraseñaUsuario;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "\nCodigo Usuario: " + codigoUsuario 
                + "\nNombre Usuario: " + nombreUsuario 
                + "\nApellido Usuario: " + apellidoUsuario 
                + "\nCorreo Usuario: " + correoUsuario 
                + "\nContraseña Usuario: " + contraseñaUsuario 
                + "\nFecha Registro: " + fechaRegistro + '}';
    }
}
