package sia.tacoCloud.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacoCloud.dao.UserRepository;
import sia.tacoCloud.security.UserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.builder()
                .username("buzz")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("woody")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public UserDetailService userDetailService(UserRepository userRepo) {
        return username -> {
            sia.tacoCloud.security.User user = userRepo.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User with name: " + username + " not found");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/design", "/orders").hasRole("USER")
                        .requestMatchers("/", "/**", "/register", "/login").permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                )
                .oauth2Login( auth -> auth.loginPage("/login"))
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .build();
    }

    @Bean
    public ApplicationRunner dataLoader(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepo.save(
                    new sia.tacoCloud.security.User("habuma", passwordEncoder.encode("password"), "ROLE_ADMIN"));
            userRepo.save(
                    new sia.tacoCloud.security.User("tacochef", passwordEncoder.encode("password"),"ROLE_ADMIN"));


            
        };
    }
}
