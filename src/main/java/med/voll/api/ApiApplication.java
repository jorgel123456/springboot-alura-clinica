package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}

//links del proyecto con spring boot concluido
//https://github.com/alura-es-cursos/Spring-Boot-3/tree/stage-4
//$ ls target
//java -jar target/api-0.0.1-SNAPSHOT.jar
//java -DATASOURCE_URL=jdbc:mysql://localhost:3306/vollmed_api -DATASOURCE_USER=root -DATASOURCE_PASSWORD= -jar target/api-0.0.1-SNAPSHOT.jar --spring.prefiles.active=prod
//https://github.com/alura-es-cursos/Spring-Boot-3/tree/stage-5