package med.voll.api.domain.consulta.validacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DTOAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeAtencionClinica implements ValidadorDeConsultas {
    @Override
    public void validar(DTOAgendarConsulta datos){
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeLaAntencion=datos.fecha().getHour()<7;
        var despuesDeLaAntencion=datos.fecha().getHour()>19;
        if(domingo || antesDeLaAntencion || despuesDeLaAntencion){
            throw new ValidationException("El horario de atencion de la clinica es de lunes a viernes de 07:00 a 19:00");
        }

    }
}
