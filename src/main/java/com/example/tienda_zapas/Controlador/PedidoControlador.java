package com.example.tienda_zapas.Controlador;

import com.example.tienda_zapas.entidad.Pedido;
import com.example.tienda_zapas.entidad.Usuario;
import com.example.tienda_zapas.Repositorio.PedidoRepositorio;
import com.example.tienda_zapas.Repositorio.UsuarioRepositorio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoControlador {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPedido(@RequestBody Pedido pedido, HttpSession session) {
        try {
            Integer usuarioId = (Integer) session.getAttribute("usuarioId");
            if (usuarioId == null) return ResponseEntity.status(401).body("Sesión no válida");

            Usuario user = usuarioRepositorio.findById(usuarioId).orElse(null);
            if (user == null) return ResponseEntity.badRequest().body("Usuario no encontrado");

            pedido.setUsuario(user); 
            pedido.setFecha(LocalDateTime.now());
            pedido.setEstado("PAGADO");

            pedidoRepositorio.save(pedido);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}