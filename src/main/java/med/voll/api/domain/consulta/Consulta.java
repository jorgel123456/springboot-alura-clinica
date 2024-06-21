package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Getter
@Entity(name="Consulta")
@Table(name = "consultas")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime fecha;


    @Enumerated(EnumType.STRING)
    private MotivoCancelamiento motivoCancelacion;

    public Consulta( Medico medico, Paciente paciente, LocalDateTime fecha) {
        this.medico=medico;
        this.paciente=paciente;
        this.fecha=fecha;
    }
    public void cancelar(MotivoCancelamiento motivo) {
        this.motivoCancelacion = motivo;
    }

}