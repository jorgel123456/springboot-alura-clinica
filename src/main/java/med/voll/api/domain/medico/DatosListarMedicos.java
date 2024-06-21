package med.voll.api.domain.medico;


public record DatosListarMedicos(
        Long id,
        String nombre,
        String especializacion,
        String documento,
        String email
) {

    public DatosListarMedicos(Medico medico){
        this(medico.getId(),medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
