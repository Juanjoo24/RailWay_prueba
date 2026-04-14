package com.example.tienda_zapas.Controlador;

import com.example.tienda_zapas.entidad.Usuario;
import com.example.tienda_zapas.Repositorio.UsuarioRepositorio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/")
    public String index() {
        return "html/index";
    }

    @GetMapping("/bienvenida")
    public String paginaBienvenida(HttpSession session, Model model) {
        String usuario = (String) session.getAttribute("nombreUsuario");
        if (usuario == null) return "redirect:/login";
        model.addAttribute("nombreUsuario", usuario);
        return "html/bienvenida";
    }

    @GetMapping("/confirmacion")
    public String verConfirmacion(HttpSession session, Model model) {
        String usuario = (String) session.getAttribute("nombreUsuario");
        if (usuario == null) return "redirect:/login";
        model.addAttribute("nombreUsuario", usuario);
        return "html/confirmacion";
    }


    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(required = false) String error, Model model) {
        if (error != null) model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "html/login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session) {

        // Buscamos al usuario en la base de datos
        Usuario u = usuarioRepositorio.findByUsuario(username);

        // Verificamos si existe y si la contraseña coincide
        if (u == null || !u.getContrasena().equals(password)) {
            return "redirect:/login?error";
        }

        // Guardamos los datos en la sesión para usarlos en el AdminControlador
        session.setAttribute("nombreUsuario", u.getUsuario());
        session.setAttribute("usuarioId", u.getId());
        session.setAttribute("rol", u.getRol()); // Guardamos el rol 

        // REDIRECCIÓN SEGÚN ROL
        if ("ADMIN".equals(u.getRol())) {
            return "redirect:/admin/dashboard"; 
        }

        return "redirect:/bienvenida";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate(); 
        return "redirect:/";
    }

    @GetMapping("/register")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuarioForm", new Usuario());
        return "html/register";
    }

    @PostMapping("/usuarios/registrar")
    public String guardarUsuario(@ModelAttribute("usuarioForm") Usuario usuario, HttpSession session) {
        // Por defecto los nuevos registros son CLIENTE
        if (usuario.getRol() == null) {
            usuario.setRol("CLIENTE");
        }
        
        usuarioRepositorio.save(usuario);
        session.setAttribute("nombreUsuario", usuario.getUsuario());
        session.setAttribute("usuarioId", usuario.getId());
        session.setAttribute("rol", usuario.getRol());
        
        return "redirect:/bienvenida";
    }
}