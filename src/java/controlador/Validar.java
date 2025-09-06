package controlador;

import com.kinalitosclothes.modelo.Usuarios;
import com.kinalitosclothes.modelo.UsuariosDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Validar", urlPatterns = {"/Validar"})
public class Validar extends HttpServlet {
    UsuariosDAO usuarioDao = new UsuariosDAO();
    Usuarios usuario = new Usuarios();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String accion = request.getParameter("accion");
            if (accion.equals("Ingresar")) {
                String correo = request.getParameter("txtUsuario");
                String pass = request.getParameter("txtPassword");
                usuario = usuarioDao.validar(correo, pass);
                if (usuario != null) {
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
                } else {
                    request.setAttribute("mensaje", "Usuario o contraseña incorrectos");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error en processRequest de Validar: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
