package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacion.ValidadorDeConsultas;
import med.voll.api.domain.consulta.validarconsulta.ValidarCancelacionDeConsulta;
import med.voll.api.domain.medico.IMedicoRepository;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.IPacienteRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.infra.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private IMedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    List<ValidadorDeConsultas> validadores;
    @Autowired
    private List<ValidarCancelacionDeConsulta> validarCancelacionDeConsultas;
    public DTODetalleConsulta agendar(DTOAgendarConsulta dtoAgendarConsulta){

        if(!pacienteRepository.findById(dtoAgendarConsulta.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("Este Id para paciente no fue encontrado");
        }

        if(dtoAgendarConsulta.idMedico()!=null && !medicoRepository.existsById(dtoAgendarConsulta.idMedico())){
            throw new ValidacionDeIntegridad("Este Id para medico no fue encontrado");
        }
        //validaciones respetando los principios SOLID
            validadores.forEach(v -> v.validar(dtoAgendarConsulta));

        var paciente=pacienteRepository.findById(dtoAgendarConsulta.idPaciente()).get();
        var medico= seleccionarMedico(dtoAgendarConsulta);
        if(medico==null){
            throw new ValidacionDeIntegridad("No existe medico para este horario y especialidad");
        }

        var consulta = new Consulta(medico,paciente, dtoAgendarConsulta.fecha());

        consultaRepository.save(consulta);

        return new DTODetalleConsulta(consulta);
    }

    private Medico seleccionarMedico(DTOAgendarConsulta dtoAgendarConsulta) {

        if(dtoAgendarConsulta.idMedico()!=null){
            return medicoRepository.getReferenceById(dtoAgendarConsulta.idMedico());
        }
        if(dtoAgendarConsulta.especialidad()==null){
            throw new ValidacionDeIntegridad("Debe selecionar una especialidad para el medico");
        }
        Medico medicos = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(dtoAgendarConsulta.especialidad(),dtoAgendarConsulta.fecha());
        return medicos;
    }
    public void cancelar(DTOCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionDeIntegridad("Id de la consulta informado no existe!");
        }

        validarCancelacionDeConsultas.forEach(v -> v.validar(datos));

        Consulta consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }
    public Page<DTODetalleConsulta> consultar(Pageable paginacion) {
        return consultaRepository.findAll(paginacion).map(DTODetalleConsulta::new);
    }

}
