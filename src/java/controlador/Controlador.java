package controlador;

import modelo.*;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.PrintWriter;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        
        Usuarios usuarios = new Usuarios();
        UsuariosDAO usuariosDao = new UsuariosDAO();
        Palabras palabras = new Palabras();
        PalabrasDAO palabrasDao = new PalabrasDAO();

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Index/Principal.jsp").forward(request, response);
        } else if (menu.equals("Index")) {
            if (accion.equals("Salir")) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else if (menu.equals("Usuarios")) {
            switch (accion) {
                case "Validar":
                    String correo = request.getParameter("txtUsuario");
                    String contraseña = request.getParameter("txtPassword");
                    usuarios = usuariosDao.validar(correo, contraseña);
                    if (usuarios != null) {
                        request.setAttribute("usuario", usuarios);
                        request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
                    } else {
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    break;
                    
                case "Agregar":
                    String nombreUsuario = request.getParameter("txtNombreUsuario");
                    String apellidoUsuario = request.getParameter("txtApellidoUsuario");
                    String correoUsuario = request.getParameter("txtCorreoUsuario");
                    String contraseñaUsuario = request.getParameter("txtPassword");
                    
                    usuarios.setNombreUsuario(nombreUsuario);
                    usuarios.setApellidoUsuario(apellidoUsuario);
                    usuarios.setCorreoUsuario(correoUsuario);
                    usuarios.setContraseñaUsuario(contraseñaUsuario);
                    
                    usuariosDao.agregar(usuarios);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
            }
        } else if (menu.equals("Palabras")) {
            Palabras palabraAleatoria = null;
            
            switch(accion) {
                case "ObtenerPalabra":
                    try {
                        palabraAleatoria = palabrasDao.obtenerPalabraAleatoria();
                        
                        if (palabraAleatoria != null) {
                            // Crear respuesta simple
                            StringBuilder json = new StringBuilder();
                            json.append("{");
                            json.append("\"palabra\":\"").append(palabraAleatoria.getPalabra()).append("\",");
                            json.append("\"pista\":\"").append(palabraAleatoria.getPista()).append("\"");
                            json.append("}");
                            
                            // Configurar respuesta
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            
                            // Enviar respuesta
                            try (PrintWriter out = response.getWriter()) {
                                out.print(json.toString());
                                out.flush();
                            }
                        } else {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontraron palabras");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al obtener palabra: " + e.getMessage());
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud");
                    }
                    break;
                    
                default:
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
            }
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    }
