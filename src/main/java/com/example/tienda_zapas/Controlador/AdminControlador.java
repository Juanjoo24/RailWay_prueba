package com.example.tienda_zapas.Controlador;

import com.example.tienda_zapas.entidad.Provedor;
import com.example.tienda_zapas.entidad.Producto;
import com.example.tienda_zapas.Repositorio.ProvedorRepositorio;
import com.example.tienda_zapas.Repositorio.UsuarioRepositorio;
import com.example.tienda_zapas.Repositorio.ProductoRepositorio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private ProvedorRepositorio provedorRepo;

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private ProductoRepositorio productoRepo;

    @GetMapping("/dashboard")
    public String verDashboard(HttpSession session, Model model) {
        String rol = (String) session.getAttribute("rol"); 
        if (rol == null || !rol.equals("ADMIN")) {
            return "redirect:/login";
        }

        model.addAttribute("proveedores", provedorRepo.findAll());
        model.addAttribute("usuarios", usuarioRepo.findAll());
        model.addAttribute("productos", productoRepo.findAll());
        
        model.addAttribute("nuevoProvedor", new Provedor());
        model.addAttribute("nuevoProducto", new Producto());
        
        return "html/admin_dashboard";
    }

    // --- GESTIÓN PROVEEDORES ---
    @PostMapping("/provedor/guardar")
    public String guardarProvedor(@ModelAttribute Provedor provedor) {
        provedorRepo.save(provedor);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/provedor/eliminar/{id}")
    public String eliminarProvedor(@PathVariable Integer id) {
        provedorRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    // --- GESTION ZAPATILLAS ---
    @PostMapping("/producto/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoRepo.save(producto);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/producto/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        productoRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }
    
    // --- GESTIÓN USUARIOS ---
    @GetMapping("/usuario/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuarioRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }
}