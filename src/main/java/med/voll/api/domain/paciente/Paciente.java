package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Getter
@Entity
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Embedded
    private Direccion direccion;
    private boolean activo;
    public Paciente(DatosRegistroPaciente datosRegistroPaciente) {
        this.nombre= datosRegistroPaciente.nombre();
        this.email= datosRegistroPaciente.email();
        this.telefono=datosRegistroPaciente.telefono();
        this.documento= datosRegistroPaciente.documento();
        this.direccion=new Direccion(datosRegistroPaciente.direccion());
        this.activo=true;
    }

    public void actualizarDatos(DTOActualizarPaciente dtoActualizarPaciente){
        if (dtoActualizarPaciente.nombre() !=null){
            this.nombre= dtoActualizarPaciente.nombre();
        }
        if (dtoActualizarPaciente.documento() !=null){
            this.documento= dtoActualizarPaciente.documento();
        }
        if (dtoActualizarPaciente.direccion() !=null){
            this.direccion= direccion.actualizarMedico(dtoActualizarPaciente.direccion());
        }

    }
    public void desactivarPaciente() {
        this.activo=false;
    }

}
