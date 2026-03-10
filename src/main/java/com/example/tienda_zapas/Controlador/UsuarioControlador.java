package com.example.tienda_zapas.Controlador; 

import com.example.tienda_zapas.entidad.Usuario;
import com.example.tienda_zapas.Repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/register")
    public String mostrarFormulario(Model model) {
        if (!model.containsAttribute("usuario")) {
            model.addAttribute("usuario", new Usuario());
        }
        return "html/register"; 
    }

    @GetMapping("/login")
    public String login() {
        return "html/login"; 
    }

    @PostMapping("/usuarios/registrar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            System.out.println(">>> ERROR DE VALIDACIÓN: " + result.getAllErrors());
            return "html/register"; 
        }

        try {
            usuarioRepositorio.save(usuario);
            
            model.addAttribute("nombreUsuario", usuario.getUsuario());
            
            return "html/bienvenida"; 
            
        } catch (Exception e) {
            System.out.println(">>> ERROR AL GUARDAR: " + e.getMessage());
            model.addAttribute("error", "El nombre de usuario o email ya existe.");
            return "html/register";
        }
    }
}