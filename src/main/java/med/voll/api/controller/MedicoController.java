package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import med.voll.api.domain.direccion.DatosDireccion;


import med.voll.api.domain.medico.*;
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
@RequestMapping("/medico")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {


    @Autowired
    private IMedicoRepository medicoRepository;
    @PostMapping
    public ResponseEntity<DTORespuestaMedico> reistrarMedico(@RequestBody @Valid DatosRegistrarMedico datosRegistrarMedico, UriComponentsBuilder uriComponentsBuilder){
        Medico medico=medicoRepository.save(new Medico(datosRegistrarMedico));
        DTORespuestaMedico dtoRespuestaMedico= new DTORespuestaMedico(medico.getId(),medico.getNombre(),
                medico.getEmail(),medico.getTelefono(), medico.getDocumento(),
                new DatosDireccion(medico.getDireccion().getCalle(),medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),medico.getDireccion().getComplemento()
                ));
        URI url=uriComponentsBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(dtoRespuestaMedico);

    }
//    @GetMapping
//    public List<DatosListarMedicos> listarMedico(){
//        return medicoRepository.findAll().stream().map(DatosListarMedicos::new).toList();
//    }
        @GetMapping
        public ResponseEntity<Page<DatosListarMedicos>> listarMedico(@PageableDefault(size = 4) Pageable paginacion){
            //return medicoRepository.findAll(paginacion).map(DatosListarMedicos::new);
            return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListarMedicos::new));
        }



        @PutMapping
        @Transactional
        public ResponseEntity actualizarMedico(@RequestBody @Valid DTOActualizarMedico dtoActualizarMedico){
                Medico medico=medicoRepository.getReferenceById(dtoActualizarMedico.id());
                medico.actualizarDatos(dtoActualizarMedico);
                return ResponseEntity.ok(new DTORespuestaMedico(medico.getId(),medico.getNombre(),
                        medico.getEmail(),medico.getTelefono(), medico.getDocumento(),
                        new DatosDireccion(medico.getDireccion().getCalle(),medico.getDireccion().getDistrito(),
                                medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),medico.getDireccion().getComplemento()
                        )));
        }

        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity eliminarMedico(@PathVariable Long id){
            Medico medico=medicoRepository.getReferenceById(id);
            medico.desactivarMedico();
            return ResponseEntity.noContent().build();
        }
    @GetMapping("/{id}")
    public ResponseEntity<DTORespuestaMedico> mostrarDatosMedico(@PathVariable Long id){
        Medico medico=medicoRepository.getReferenceById(id);
        var datosMedico=new DTORespuestaMedico(medico.getId(),medico.getNombre(),
                medico.getEmail(),medico.getTelefono(), medico.getDocumento(),
                new DatosDireccion(medico.getDireccion().getCalle(),medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),medico.getDireccion().getComplemento()
                ));
        return ResponseEntity.ok(datosMedico);
    }
//

}
