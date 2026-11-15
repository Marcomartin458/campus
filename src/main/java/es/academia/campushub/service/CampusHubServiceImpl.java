package es.academia.campushub.service;

import es.academia.campushub.controller.dto.ActividadDetalleResponse;
import es.academia.campushub.controller.dto.ActividadHistorialResponse;
import es.academia.campushub.controller.dto.ActividadResponse;
import es.academia.campushub.controller.dto.ContactoResponse;
import es.academia.campushub.controller.dto.CreateActividadRequest;
import es.academia.campushub.controller.dto.CreateDepartamentoRequest;
import es.academia.campushub.controller.dto.CreateDocenteRequest;
import es.academia.campushub.controller.dto.CreateEstudianteRequest;
import es.academia.campushub.controller.dto.CreateEtiquetaRequest;
import es.academia.campushub.controller.dto.CreateGrupoRequest;
import es.academia.campushub.controller.dto.CreateInscripcionRequest;
import es.academia.campushub.controller.dto.CreateSeguimientoRequest;
import es.academia.campushub.controller.dto.DepartamentoResponse;
import es.academia.campushub.controller.dto.DocenteResponse;
import es.academia.campushub.controller.dto.EstudianteResponse;
import es.academia.campushub.controller.dto.EtiquetaResponse;
import es.academia.campushub.controller.dto.GrupoResponse;
import es.academia.campushub.controller.dto.InscripcionResponse;
import es.academia.campushub.controller.dto.SeguimientoResponse;
import es.academia.campushub.controller.dto.UpsertContactoRequest;
import es.academia.campushub.entity.ActividadEntity;
import es.academia.campushub.entity.DepartamentoEntity;
import es.academia.campushub.entity.DocenteEntity;
import es.academia.campushub.entity.EstudianteEntity;
import es.academia.campushub.entity.GrupoEntity;
import es.academia.campushub.repository.ActividadRepository;
import es.academia.campushub.repository.DepartamentoRepository;
import es.academia.campushub.repository.DocenteRepository;
import es.academia.campushub.repository.EstudianteRepository;
import es.academia.campushub.repository.EtiquetaRepository;
import es.academia.campushub.repository.FichaContactoRepository;
import es.academia.campushub.repository.GrupoRepository;
import es.academia.campushub.repository.InscripcionesRepository;
import es.academia.campushub.repository.SeguimientoRepository;
import es.academia.campushub.util.exception.BusinessConflictException;
import es.academia.campushub.util.exception.InvalidInputException;
import es.academia.campushub.util.exception.PersistenciaPendienteException;
import es.academia.campushub.util.exception.ResourceNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Punto de entrada de la tarea. Sustituye cada stub por una implementación real. NO hay entidades,
 * repositorios ni documentos entregados.
 */
@Service
public class CampusHubServiceImpl implements CampusHubService {
  private final DepartamentoRepository departamentos;
  private final DocenteRepository docentes;
  private final GrupoRepository grupos;
  private final EstudianteRepository estudiantes;
  private final ActividadRepository actividades;
  private final InscripcionesRepository inscripciones;
  private final FichaContactoRepository contactos;
  private final EtiquetaRepository etiquetas;
  private final SeguimientoRepository seguimiento;

  public CampusHubServiceImpl(
      DepartamentoRepository departamentos,
      DocenteRepository docentes,
      GrupoRepository grupos,
      EstudianteRepository estudiantes,
      ActividadRepository actividades,
      InscripcionesRepository inscripciones,
      FichaContactoRepository contactos,
      EtiquetaRepository etiquetas,
      SeguimientoRepository seguimiento) {
    this.departamentos = departamentos;
    this.docentes = docentes;
    this.grupos = grupos;
    this.estudiantes = estudiantes;
    this.actividades = actividades;
    this.inscripciones = inscripciones;
    this.contactos = contactos;
    this.etiquetas = etiquetas;
    this.seguimiento = seguimiento;
  }

  @Override
  @Transactional
  public GrupoResponse crearGrupo(CreateGrupoRequest request) {
    requireRequest(request);
    String nombre = require(request.nombre(), "nombre");
    String nivel = require(request.nivel(), "nivel");

    DocenteEntity tutor =
        docentes
            .findById(requiredId(request.tutorDocenteId(), "tutorDocenteId"))
            .orElseThrow(() -> new ResourceNotFoundException("No existe el docente tutor"));

    GrupoEntity grupo = new GrupoEntity(null, nombre, nivel, tutor);
    GrupoEntity guardado = grupos.save(grupo);
    return grupoResponse(guardado);
  }

  @Override
  @Transactional
  public DepartamentoResponse crearDepartamento(CreateDepartamentoRequest request) {
    requireRequest(request);
    String nombre = require(request.nombre(), "nombre");

    if (departamentos.findByNombre(nombre).isPresent()) {
      throw new BusinessConflictException("Ya existe un departamento con ese nombre");
    }

    DepartamentoEntity guardado = departamentos.save(new DepartamentoEntity(null, nombre));
    return new DepartamentoResponse(guardado.getDepartamentoId(), guardado.getNombre());
  }

  @Override
  @Transactional
  public EstudianteResponse crearEstudiante(CreateEstudianteRequest request) {
    requireRequest(request);
    String nombre = require(request.nombre(), "nombre");
    String apellido = require(request.apellidos(), "apellidos");
    String correo = require(request.email(), "email");
    LocalDate nacimiento = parseDate(request.fechaNacimiento(), "fechaNacimiento");

    if (!nacimiento.isBefore(LocalDate.now())) {
      throw new InvalidInputException("La fecha de nacimiento debe ser anterior a hoy");
    }

    if (estudiantes.existsByCorreo(correo)) {
      throw new BusinessConflictException("Ya existe un estudiante con ese correo");
    }

    GrupoEntity grupo =
        grupos
            .findById(requiredId(request.grupoId(), "grupoId"))
            .orElseThrow(() -> new ResourceNotFoundException("No existe el grupo indicado"));

    EstudianteEntity nuevo =
        new EstudianteEntity(null, nombre, apellido, correo, nacimiento, grupo);
    return estudianteResponse(estudiantes.save(nuevo));
  }

  @Override
  @Transactional(readOnly = true)
  public EstudianteResponse obtenerEstudiante(Long id) {
    return estudianteResponse(estudiante(id));
  }

  @Override
  @Transactional
  public DocenteResponse crearDocente(CreateDocenteRequest request) {
    requireRequest(request);
    String nombre = require(request.nombre(), "nombre");
    String apellido = require(request.apellidos(), "apellidos");
    String email = require(request.email(), "email");

    if (docentes.existsByEmail(email)) {
      throw new BusinessConflictException("Ya existe un docente con ese correo");
    }

    DepartamentoEntity departamento =
        departamentos
            .findById(requiredId(request.departamentoId(), "departamentoId"))
            .orElseThrow(
                () -> new ResourceNotFoundException("No existe el departamento indicado"));

    DocenteEntity docente =
        new DocenteEntity(null, nombre, apellido, email, departamento);
    DocenteEntity guardado = docentes.save(docente);
    return docenteResponse(guardado);
  }

  @Override
  @Transactional
  public ActividadResponse crearActividad(CreateActividadRequest request) {
    requireRequest(request);
    String titulo = require(request.titulo(), "titulo");
    String descripcion = require(request.descripcion(), "descripcion");
    LocalDate fecha = parseDate(request.fechaPrevista(), "fechaPrevista");

    if (request.maxParticipantes() == null || request.maxParticipantes() < 1) {
      throw new InvalidInputException("maxParticipantes debe ser mayor que cero");
    }

    ActividadEntity.TipoActividad tipo = parseTipo(request.tipo());
    DocenteEntity docente =
        docentes
            .findById(requiredId(request.docenteId(), "docenteId"))
            .orElseThrow(
                () -> new ResourceNotFoundException("No existe el docente responsable"));

    ActividadEntity actividad =
        new ActividadEntity(
            null,
            titulo,
            descripcion,
            tipo,
            fecha,
            request.maxParticipantes(),
            docente);
    return actividadResponse(actividades.save(actividad));
  }

  @Override
  @Transactional(readOnly = true)
  public ActividadResponse obtenerActividad(Long id) {
    return actividadResponse(actividad(id));
  }

  @Override
  public InscripcionResponse inscribir(Long actividadId, CreateInscripcionRequest request) {
    throw new PersistenciaPendienteException("inscribir");
  }

  @Override
  public List<EstudianteResponse> participantes(Long actividadId) {
    throw new PersistenciaPendienteException("participantes");
  }

  @Override
  public SeguimientoResponse registrarSeguimiento(
      Long actividadId, CreateSeguimientoRequest request) {
    throw new PersistenciaPendienteException("registrarSeguimiento");
  }

  @Override
  public ActividadDetalleResponse detalle(Long id) {
    throw new PersistenciaPendienteException("detalle");
  }

  @Override
  public List<ActividadHistorialResponse> historial(Long estudianteId) {
    throw new PersistenciaPendienteException("historial");
  }

  @Override
  public ContactoResponse guardarContacto(Long estudianteId, UpsertContactoRequest request) {
    throw new PersistenciaPendienteException("guardarContacto");
  }

  @Override
  @Transactional(readOnly = true)
  public ContactoResponse contacto(Long estudianteId) {
    estudiante(estudianteId);
    var ficha =
        contactos
            .findByEstudiante_EstudianteId(estudianteId)
            .orElseThrow(() -> new ResourceNotFoundException("El estudiante no tiene ficha"));

    return new ContactoResponse(
        ficha.getEstudiante().getEstudianteId(),
        ficha.getNombreTutorLegal(),
        ficha.getTelefono(),
        ficha.getCorreoElectronico(),
        ficha.getAutorizaFueraCentro(),
        ficha.getFechaAutorizacion().toString());
  }

  @Override
  @Transactional
  public EtiquetaResponse crearEtiqueta(CreateEtiquetaRequest request) {
    requireRequest(request);
    String nombre = require(request.nombre(), "nombre");

    if (etiquetas.findByNombre(nombre).isPresent()) {
      throw new BusinessConflictException("Ya existe una etiqueta con ese nombre");
    }

    var guardada = etiquetas.save(new es.academia.campushub.entity.EtiquetaEntity(null, nombre));
    return new EtiquetaResponse(guardada.getId(), guardada.getNombre());
  }

  @Override
  @Transactional
  public void asociarEtiqueta(Long actividadId, Long etiquetaId) {
    ActividadEntity actividad = actividad(actividadId);
    es.academia.campushub.entity.EtiquetaEntity etiqueta =
        etiquetas
            .findById(requiredId(etiquetaId, "etiquetaId"))
            .orElseThrow(() -> new ResourceNotFoundException("No existe la etiqueta indicada"));

    boolean yaAsociada =
        actividad.getEtiquetas().stream()
            .anyMatch(actual -> java.util.Objects.equals(actual.getId(), etiquetaId));

    if (!yaAsociada) {
      actividad.addEtiqueta(etiqueta);
      actividades.save(actividad);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<ActividadResponse> actividadesPorEtiqueta(Long etiquetaId) {
    Long id = requiredId(etiquetaId, "etiquetaId");
    if (!etiquetas.existsById(id)) {
      throw new ResourceNotFoundException("No existe la etiqueta indicada");
    }

    return actividades.findAllByEtiquetaId(id).stream()
        .map(CampusHubServiceImpl::actividadResponse)
        .toList();
  }

  private static void requireRequest(Object request) {
    if (request == null) {
      throw new InvalidInputException("La solicitud es obligatoria");
    }
  }

  private static String require(String value, String field) {
    if (value == null || value.isBlank()) {
      throw new InvalidInputException(field + " es obligatorio");
    }
    return value.trim();
  }

  private static Long requiredId(Long id, String field) {
    if (id == null || id <= 0) {
      throw new InvalidInputException(field + " debe ser un identificador válido");
    }
    return id;
  }

  private static LocalDate parseDate(String value, String field) {
    try {
      return LocalDate.parse(require(value, field));
    } catch (DateTimeParseException exception) {
      throw new InvalidInputException(field + " debe tener formato YYYY-MM-DD");
    }
  }

  private static ActividadEntity.TipoActividad parseTipo(String value) {
    String tipo = require(value, "tipo").toUpperCase(Locale.ROOT);
    try {
      return ActividadEntity.TipoActividad.valueOf(tipo);
    } catch (IllegalArgumentException exception) {
      throw new InvalidInputException("tipo no es válido");
    }
  }

  private EstudianteEntity estudiante(Long id) {
    return estudiantes
        .findById(requiredId(id, "id"))
        .orElseThrow(() -> new ResourceNotFoundException("No existe el estudiante indicado"));
  }

  private ActividadEntity actividad(Long id) {
    return actividades
        .findById(requiredId(id, "id"))
        .orElseThrow(() -> new ResourceNotFoundException("No existe la actividad indicada"));
  }

  private static GrupoResponse grupoResponse(GrupoEntity grupo) {
    return new GrupoResponse(
        grupo.getGrupoId(),
        grupo.getNombre(),
        grupo.getNivel(),
        grupo.getTutorDocente().getId());
  }

  private static EstudianteResponse estudianteResponse(EstudianteEntity estudiante) {
    return new EstudianteResponse(
        estudiante.getEstudianteId(),
        estudiante.getNombre(),
        estudiante.getApellido(),
        estudiante.getCorreo(),
        estudiante.getFechaNacimiento().toString(),
        estudiante.getGrupo().getGrupoId());
  }

  private static DocenteResponse docenteResponse(DocenteEntity docente) {
    return new DocenteResponse(
        docente.getId(),
        docente.getNombre(),
        docente.getApellido(),
        docente.getEmail(),
        docente.getDepartamento().getDepartamentoId());
  }

  private static ActividadResponse actividadResponse(ActividadEntity actividad) {
    return new ActividadResponse(
        actividad.getActividadId(),
        actividad.getTitulo(),
        actividad.getDescripcion(),
        actividad.getTipoActividad().name(),
        actividad.getFechaPrevista().toString(),
        actividad.getNumeroMaximoParticipantes(),
        actividad.getDocenteResponsable().getId());
  }
}
