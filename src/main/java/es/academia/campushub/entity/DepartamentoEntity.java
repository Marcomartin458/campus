package es.academia.campushub.entity;

import jakarta.persistence.*;

@Entity
@Table
public class DepartamentoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nombre;

    public DepartamentoEntity(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public DepartamentoEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}