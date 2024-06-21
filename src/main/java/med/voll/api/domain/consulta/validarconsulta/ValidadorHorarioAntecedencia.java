package med.voll.api.domain.consulta.validarconsulta;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DTOCancelamientoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidarCancelacionDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void validar(DTOCancelamientoConsulta datos) {
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(ahora, consulta.getFecha()).toHours();

        if (diferenciaEnHoras < 24) {
            throw new ValidationException("Consulta solamente puede ser cancelada con antecedencia mínima de 24h!");
        }

    }
}
