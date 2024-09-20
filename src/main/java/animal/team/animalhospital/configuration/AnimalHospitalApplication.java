package animal.team.animalhospital.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "animal.team.animalhospital")
@MapperScan(basePackages = "animal.team.animalhospital", annotationClass = Mapper.class)
public class AnimalHospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalHospitalApplication.class, args);
    }

}
