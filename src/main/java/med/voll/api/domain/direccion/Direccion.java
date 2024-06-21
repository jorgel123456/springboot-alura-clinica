package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Embeddable
public class Direccion {
    private String calle;
    private String numero;
    private String complemento;
    private String distrito;
    private String ciudad;

    public Direccion(){}
    public Direccion(DatosDireccion direccion) {
        this.calle=direccion.calle();
        this.numero=direccion.numero();
        this.complemento=direccion.complemento();
        this.distrito= direccion.distrito();
        this.ciudad=direccion.ciudad();
    }

    public Direccion actualizarMedico(DatosDireccion direccion) {
        this.calle=direccion.calle();
        this.numero=direccion.numero();
        this.complemento=direccion.complemento();
        this.distrito= direccion.distrito();
        this.ciudad=direccion.ciudad();
        return this;
    }
}
