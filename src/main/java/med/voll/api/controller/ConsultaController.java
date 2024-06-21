package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DTOAgendarConsulta;
import med.voll.api.domain.consulta.DTODetalleConsulta;
import med.voll.api.domain.medico.IMedicoRepository;
import med.voll.api.domain.paciente.IPacienteRepository;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Getter
@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService agendaDeConsultaService;
    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DTOAgendarConsulta dtoAgendarConsulta){
        var respuesta=agendaDeConsultaService.agendar(dtoAgendarConsulta);
        return ResponseEntity.ok(respuesta);
    }
}
