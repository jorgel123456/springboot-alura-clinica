package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import med.voll.api.domain.direccion.Direccion;
@Getter
@Entity(name="Medico")
@Table(name = "medicos")

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    Especialidad especialidad;
    @Embedded
    private Direccion direccion;
    private boolean activo;

    public Medico(){}
    public Medico(DatosRegistrarMedico datosRegistrarMedico) {
        this.nombre= datosRegistrarMedico.nombre();
        this.email= datosRegistrarMedico.email();
        this.telefono=datosRegistrarMedico.telefono();
        this.documento= datosRegistrarMedico.documento();
        this.especialidad=datosRegistrarMedico.especialidad();
        this.direccion=new Direccion(datosRegistrarMedico.direccion());
        this.activo=true;
    }
    public void actualizarDatos(DTOActualizarMedico dtoActualizarMedico){
        if (dtoActualizarMedico.nombre() !=null){
            this.nombre= dtoActualizarMedico.nombre();
        }
        if (dtoActualizarMedico.documento() !=null){
            this.documento= dtoActualizarMedico.documento();
        }
        if (dtoActualizarMedico.direccion() !=null){
        this.direccion= direccion.actualizarMedico(dtoActualizarMedico.direccion());
        }

    }

    public void desactivarMedico() {
        this.activo=false;
    }

}
