package med.voll.api.domain.consulta.validacion;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DTOAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void validar(DTOAgendarConsulta datos){
        var medicoConConsulta=consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());

        if (medicoConConsulta) {
            throw new RuntimeException("El Medico ya tiene una cita programada para ese fecha y hora");
        }
    }
}
