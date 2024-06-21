package med.voll.api.domain.consulta.validacion;

import med.voll.api.domain.consulta.DTOAgendarConsulta;
import med.voll.api.domain.medico.IMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultas{
    @Autowired
    private IMedicoRepository medicoRepository;
    @Override
    public void validar(DTOAgendarConsulta datos) {
        if (datos.idMedico() == null) {
            return ;
        }

        boolean medicoActivo=medicoRepository.findActivoById(datos.idMedico());
        if (!medicoActivo) {
            throw new RuntimeException("No se puede agendar citas con Medicos inactivos");
        }
    }
}
