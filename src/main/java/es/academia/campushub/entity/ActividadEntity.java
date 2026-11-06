package es.academia.campushub.entity;

import jakarta.persistence.*;

@Entity
@Table
public class ActividadEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String titulo;

    @Column
    private String descripcion;

    @Column
    private String tipoActividad;

    @Column
    private String fechaPrevista;

    @Column
    private int numeroMaximoParticipantes;

    public ActividadEntity(Long id, String titulo, String descripcion, String tipoActividad, String fechaPrevista, int numeroMaximoParticipantes) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoActividad = tipoActividad;
        this.fechaPrevista = fechaPrevista;
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
    }

    public ActividadEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getFechaPrevista() {
        return fechaPrevista;
    }

    public void setFechaPrevista(String fechaPrevista) {
        this.fechaPrevista = fechaPrevista;
    }

    public int getNumeroMaximoParticipantes() {
        return numeroMaximoParticipantes;
    }

    public void setNumeroMaximoParticipantes(int numeroMaximoParticipantes) {
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
    }

}