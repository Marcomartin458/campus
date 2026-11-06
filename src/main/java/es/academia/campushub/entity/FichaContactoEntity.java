package es.academia.campushub.entity;

import jakarta.persistence.*;


@Entity
@Table
public class FichaContactoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nombreTutorLegal;

    @Column
    private String telefono;

    @Column
    private String correoElectronico;

    @Column
    private boolean autorizaParticipacion;

    @Column
    private String fechaAutorizacion;

    public FichaContactoEntity(Long id, String nombreTutorLegal, String telefono, String correoElectronico, boolean autorizaParticipacion, String fechaAutorizacion) {
        this.id = id;
        this.nombreTutorLegal = nombreTutorLegal;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.autorizaParticipacion = autorizaParticipacion;
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public FichaContactoEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTutorLegal() {
        return nombreTutorLegal;
    }

    public void setNombreTutorLegal(String nombreTutorLegal) {
        this.nombreTutorLegal = nombreTutorLegal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public boolean isAutorizaParticipacion() {
        return autorizaParticipacion;
    }

    public void setAutorizaParticipacion(boolean autorizaParticipacion) {
        this.autorizaParticipacion = autorizaParticipacion;
    }

    public String getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(String fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }
}

