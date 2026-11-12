package es.academia.campushub.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "fichas_contacto")
public class FichaContactoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fichaContactoId;

    @Column(name = "nombre_tutor_legal", nullable = false)
    private String nombreTutorLegal;

    @Column(nullable = false)
    private String telefono;

    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @Column(name = "autoriza_fuera_centro", nullable = false)
    private Boolean autorizaFueraCentro;

    @Column(name = "fecha_autorizacion", nullable = false)
    private LocalDate fechaAutorizacion;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estudiante_id", nullable = false, unique = true)
    private EstudianteEntity estudiante;

    public FichaContactoEntity() {
    }

    public Long getFichaContactoId() {
        return fichaContactoId;
    }

    public void setFichaContactoId(Long fichaContactoId) {
        this.fichaContactoId = fichaContactoId;
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

    public Boolean getAutorizaFueraCentro() {
        return autorizaFueraCentro;
    }

    public void setAutorizaFueraCentro(Boolean autorizaFueraCentro) {
        this.autorizaFueraCentro = autorizaFueraCentro;
    }

    public LocalDate getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(LocalDate fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }
}