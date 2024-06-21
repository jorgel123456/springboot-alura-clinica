package med.voll.api.domain.paciente;

public record DatosListarPaciente(
        Long id,
        String nombre,
        String documento,
        String email
) {

    public DatosListarPaciente(Paciente paciente){
        this(paciente.getId(),paciente.getNombre(), paciente.getDocumento(), paciente.getEmail());
    }
}
