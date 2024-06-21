package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.DatosDireccion;

public record DTORespuestaPaciente(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        DatosDireccion direccion
) {
}
