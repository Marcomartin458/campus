package es.academia.campushub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "docentes")

public class DocenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docenteId;

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
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departamentoId", nullable = false)
    private DepartamentoEntity departamento;

    public DocenteEntity() {
    }

    public DocenteEntity(Long DocenteId, String nombre, String apellido, String email, DepartamentoEntity departamento) {
        this.docenteId = DocenteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.departamento = departamento;
    }

    public Long getId() {
        return docenteId;
    }

    public void setId(Long id) {
        this.docenteId = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoEntity departamento) {
        this.departamento = departamento;
    }
}