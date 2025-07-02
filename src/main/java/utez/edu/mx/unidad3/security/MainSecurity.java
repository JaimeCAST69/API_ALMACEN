package utez.edu.mx.unidad3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class MainSecurity {
    @Bean
    public SecurityFilterChain doFilterInternal(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable()).cors(c-> c.configurationSource(corsRegistry()))
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/api/client/**").hasRole("ADMIN")
                        .requestMatchers("/api/cedes/**").hasRole("EMPLOYEE")
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }

    private CorsConfigurationSource corsRegistry() {
        //se genera la cofiguracion del cors
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(false);
    //aque rutas queremso aplicaar dihca configruacion
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

  /*  @Bean
    public UserDetailsService generateUsers() {
        UserDetails admin = User.builder()
                .username("root")
                .password(passwordEncoder().encode("root"))
                .roles("ADMIN")
                .build();

        UserDetails usuaro = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("123"))
                .roles("EMPLOYEE")
                .build();

        UserDetails swagger = User.builder()
                .username("swagger")
                .password(passwordEncoder().encode("123"))
                .roles("SWAGGER")
                .build();
        return new InMemoryUserDetailsManager(admin, usuaro, swagger);


    }
*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
