package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.infra.ValidacionDeIntegridad;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;

@RestControllerAdvice
public class TratamientoDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity  tratarErrores404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity  tratarErrores400(MethodArgumentNotValidException m){
        var errores=m.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity  erroresHandlerValidacionesDeIntegridad(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity  erroresHandlerValidacionesDeNegocio(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    public record DatosErrorValidacion(String campos, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }


}
