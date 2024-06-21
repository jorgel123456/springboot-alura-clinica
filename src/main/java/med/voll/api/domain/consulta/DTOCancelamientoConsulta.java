package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DTOCancelamientoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamiento motivo
) {

}
