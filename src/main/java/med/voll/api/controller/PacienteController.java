package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Getter
@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired
    private IPacienteRepository pacienteRepository;
    @PostMapping
    public ResponseEntity<DTORespuestaPaciente> reistrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente , UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente=pacienteRepository.save(new Paciente(datosRegistroPaciente));
        DTORespuestaPaciente dtoRespuestaPaciente= new DTORespuestaPaciente(paciente.getId(),paciente.getNombre(),
                paciente.getEmail(),paciente.getTelefono(), paciente.getDocumento(),
                new DatosDireccion(paciente.getDireccion().getCalle(),paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(),paciente.getDireccion().getNumero(),paciente.getDireccion().getComplemento()
                ));
        URI url=uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(dtoRespuestaPaciente);

    }

    @GetMapping
    public ResponseEntity<Page<DatosListarPaciente>> listarPacientes(@PageableDefault(size = 4) Pageable paginacion){
        //return medicoRepository.findAll(paginacion).map(DatosListarMedicos::new);
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue(paginacion).map(DatosListarPaciente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarPaciente(@RequestBody @Valid DTOActualizarPaciente dtoActualizarPaciente){
        Paciente paciente=pacienteRepository.getReferenceById(dtoActualizarPaciente.id());
        paciente.actualizarDatos(dtoActualizarPaciente);
        return ResponseEntity.ok(new DTORespuestaPaciente(paciente.getId(),paciente.getNombre(),
                paciente.getEmail(),paciente.getTelefono(), paciente.getDocumento(),
                new DatosDireccion(paciente.getDireccion().getCalle(),paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(),paciente.getDireccion().getNumero(),paciente.getDireccion().getComplemento()
                )));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPaciente(@PathVariable Long id){
        Paciente paciente=pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DTORespuestaPaciente> mostrarDatosPaciente(@PathVariable Long id){
        Paciente paciente=pacienteRepository.getReferenceById(id);
        var datosPaciente=new DTORespuestaPaciente(paciente.getId(),paciente.getNombre(),
                paciente.getEmail(),paciente.getTelefono(), paciente.getDocumento(),
                new DatosDireccion(paciente.getDireccion().getCalle(),paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(),paciente.getDireccion().getNumero(),paciente.getDireccion().getComplemento()
                ));
        return ResponseEntity.ok(datosPaciente);
    }


}
