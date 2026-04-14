package com.example.tienda_zapas.Repositorio;

import com.example.tienda_zapas.entidad.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {
}