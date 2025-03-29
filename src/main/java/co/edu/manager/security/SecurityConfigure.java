package co.edu.manager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfigure{


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(
                (csrf) -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/edu-api/v1/health").permitAll()
                                .requestMatchers("/edu-api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers("/edu-api/v1/student/**").hasRole("STUDENT")
                                .anyRequest().authenticated()
                ).httpBasic(withDefaults());
		 return http.build();
		
	}


}
