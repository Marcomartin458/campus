package es.academia.campushub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "fichas_contacto")

public class FichaContactoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fichaContactoId;

    @NotBlank
    @Size(max = 160)
    @Column(name = "nombre_tutor_legal", nullable = false, length = 160)
    private String nombreTutorLegal;

    @NotBlank
    @Size(max = 32)
    @Column(nullable = false, length = 32)
    private String telefono;

    @NotBlank
    @Size(max = 254)
    @Column(name = "correo_electronico", nullable = false, length = 254)
    private String correoElectronico;

    @NotNull
    @Column(name = "autoriza_fuera_centro", nullable = false)
    private Boolean autorizaFueraCentro;

    @NotNull
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