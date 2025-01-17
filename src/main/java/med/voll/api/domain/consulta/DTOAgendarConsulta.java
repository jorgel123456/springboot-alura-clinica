package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DTOAgendarConsulta(
        Long id,
        Long idPaciente,
        Long idMedico,
        @NotNull @Future
        LocalDateTime fecha,
        Especialidad especialidad) {
        public DTOAgendarConsulta {
        }
}
