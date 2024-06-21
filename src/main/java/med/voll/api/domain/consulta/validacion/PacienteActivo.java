package med.voll.api.domain.consulta.validacion;

import med.voll.api.domain.consulta.DTOAgendarConsulta;
import med.voll.api.domain.paciente.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas{
    @Autowired
    private IPacienteRepository  pacienteRepository;
    @Override
    public void validar(DTOAgendarConsulta  datos){
        if (datos.idPaciente() == null) {
            return;
        }
        var pacienteActivo=pacienteRepository.findActivoById(datos.idPaciente());
        if (!pacienteActivo) {
            throw new RuntimeException("No se puede agendar citas con pacientes inactivos");
        }
    }


}
