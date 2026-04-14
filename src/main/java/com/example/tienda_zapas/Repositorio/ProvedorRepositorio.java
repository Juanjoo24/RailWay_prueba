package com.example.tienda_zapas.Repositorio;

import com.example.tienda_zapas.entidad.Provedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvedorRepositorio extends JpaRepository<Provedor, Integer> {
}