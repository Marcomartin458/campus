package es.academia.campushub.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiante")
public class EstudianteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estudianteId;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column(unique = true)
    private String correo;
    @Column(unique = true)
    private LocalDate fechaNacimiento;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "grupoId", insertable = false, updatable = false, nullable = false)
    private GrupoEntity grupo;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private FichaContactoEntity fichaContacto;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<InscripcionesEntity> inscripciones = new ArrayList<>();


    public EstudianteEntity(Long estudianteId, String nombre, String apellido, String correo, LocalDate fechaNacimiento, Long grupoId) {
        this.estudianteId = estudianteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.grupo = new GrupoEntity();
        this.grupo.setGrupoId(grupoId);
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
}
