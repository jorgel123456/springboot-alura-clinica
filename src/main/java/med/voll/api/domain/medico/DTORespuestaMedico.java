package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.DatosDireccion;

public record DTORespuestaMedico(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        DatosDireccion direccion
) {
}
