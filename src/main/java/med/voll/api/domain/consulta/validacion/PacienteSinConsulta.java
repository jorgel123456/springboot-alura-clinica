package med.voll.api.domain.consulta.validacion;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DTOAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void validar(DTOAgendarConsulta datos){
        var primerHorario=datos.fecha().withHour(7);
        var ultimoHorario=datos.fecha().withHour(18);

        var pacienteConConsulta=consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(),primerHorario,ultimoHorario);

        if (pacienteConConsulta) {
            throw new RuntimeException("El paciente ya tiene una cita para ese dia");
        }
    }

}
