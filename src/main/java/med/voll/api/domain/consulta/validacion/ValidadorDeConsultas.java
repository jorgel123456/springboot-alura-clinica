package med.voll.api.domain.consulta.validacion;

import med.voll.api.domain.consulta.DTOAgendarConsulta;

public interface ValidadorDeConsultas {
    public void validar(DTOAgendarConsulta datos);
}
