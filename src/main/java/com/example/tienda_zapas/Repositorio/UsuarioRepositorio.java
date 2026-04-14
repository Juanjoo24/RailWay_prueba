package com.example.tienda_zapas.Repositorio;

import com.example.tienda_zapas.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    
    Usuario findByUsuario(String usuario); 
    
}