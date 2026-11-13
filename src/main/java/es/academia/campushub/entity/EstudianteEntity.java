package es.academia.campushub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiante")

public class EstudianteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estudianteId;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String nombre;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String apellido;

    @NotBlank
    @Size(max = 254)
    @Column(unique = true, nullable = false, length = 254)
    private String correo;

    @Past
    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grupoId", nullable = false)
    private GrupoEntity grupo;

    @OneToOne(
        mappedBy = "estudiante",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY)
    private FichaContactoEntity fichaContacto;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<InscripcionesEntity> inscripciones = new ArrayList<>();

    public EstudianteEntity(
        Long estudianteId,
        String nombre,
        String apellido,
        String correo,
        LocalDate fechaNacimiento,
        GrupoEntity grupo) {
        this.estudianteId = estudianteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.grupo = grupo;
    }

    public EstudianteEntity() {
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public GrupoEntity getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoEntity grupo) {
        this.grupo = grupo;
    }

    public FichaContactoEntity getFichaContacto() {
        return fichaContacto;
    }

    public void setFichaContacto(FichaContactoEntity fichaContacto) {
        this.fichaContacto = fichaContacto;
        if (fichaContacto != null) {
            fichaContacto.setEstudiante(this);
        }
    }

    public List<InscripcionesEntity> getInscripciones() {
        return inscripciones;
    }
}
