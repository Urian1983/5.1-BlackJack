package cat.itacademy.s05.t01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain (ServerHttpSecurity http){
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("game/new/**").permitAll()
                        .pathMatchers("/ranking").permitAll()
                        .pathMatchers("/game/**").authenticated()
                        .pathMatchers("/ranking/player/**").hasRole("ADMIN")


                        .anyExchange().authenticated()
                )
                .httpBasic(basic ->{})
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin1234"))
                .roles("ADMIN","PLAYER")
                .build();

        UserDetails player = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("player1234"))
                .roles("PLAYER")
                .build();

        return new MapReactiveUserDetailsService(player,admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
