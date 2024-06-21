package med.voll.api.domain.consulta.validacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DTOAgendarConsulta;
import org.springframework.stereotype.Component;


import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAnticipacion implements ValidadorDeConsultas{
    @Override
    public void validar(DTOAgendarConsulta datos){
        var ahora= LocalDateTime.now();
        var horaDeConsulta=datos.fecha();

        var diferenciaTreintaMinutos= Duration.between(ahora,horaDeConsulta).toMinutes()<30;

        if (diferenciaTreintaMinutos) {
            throw new ValidationException("La consultas deben ser programadas con al menos 30 minutos de anticipacion ");

        }
    }
}
