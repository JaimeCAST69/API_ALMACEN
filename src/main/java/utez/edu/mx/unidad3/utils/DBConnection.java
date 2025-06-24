package utez.edu.mx.unidad3.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/*
* @Configuration: le dice a spring que esta calse va a configurar algo
* , pero esta anotacion requiere almenos de 1 metodo que retorne dicha configuracion
*
* @Bean define el metodo que va a regresar dicha configuracion*/
@Configuration
public class DBConnection {

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;


    @Bean
    public DataSource getConnection()  {
        try{
            DriverManagerDataSource configuration = new DriverManagerDataSource();
            configuration.setUrl(DB_URL);
            configuration.setUsername(username);
            configuration.setPassword(password);
            configuration.setDriverClassName("com.mysql.jdbc.Driver");

            return configuration;
        }catch (Exception ex){
            System.out.println("Error de conexión a base de datos");
            ex.printStackTrace();
            return null;
        }
    }


}
