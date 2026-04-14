package com.example.tienda_zapas.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos") 
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    private Double total;

    private String estado;

    private String direccionEntrega;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Usuario usuario;

    public Pedido() {
        this.fecha = LocalDateTime.now();
        this.estado = "PAGADO";
    }
    public Pedido(Usuario usuario, Double total, String direccionEntrega) {
        this.usuario = usuario;
        this.total = total;
        this.direccionEntrega = direccionEntrega;
        this.fecha = LocalDateTime.now();
        this.estado = "PAGADO";
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
