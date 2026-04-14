package com.example.tienda_zapas.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "productos") 
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    private String marca;
    private String modelo;
    private Double precio;
    private String talla;

    public Producto() {
    }

    public Producto(String nombre, String marca, String modelo, Double precio, String talla) {
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.talla = talla;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
}