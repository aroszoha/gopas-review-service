package cz.gopas.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class ReviewServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewServiceApplication.class, args);
    }

    @Bean
    RestTemplate getBookServiceClient() {
        return new RestTemplate();
    }

    // This Bean method must be present here
    // If PasswordEncoder is specified directly in getUsersRegistry as local variable, then it's not used by Spring
    @Bean
    public PasswordEncoder passwordEncoder() {
        // or PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain getSecurityFilters(HttpSecurity httpSec) {
        // Make /private endpoint as protected (under login), other endpoints are public
        // Logged user must also have role ADMIN
        httpSec.authorizeHttpRequests(req ->
            req.requestMatchers(HttpMethod.GET, "/private").hasRole("ADMIN")
               .anyRequest().permitAll()
        );
        httpSec.formLogin(Customizer.withDefaults());

        return httpSec.build();
    }

    @Bean
    UserDetailsService getUsersRegistry(PasswordEncoder pwdEncoder) {
        UserDetailsManager userMngr = new InMemoryUserDetailsManager();

        UserDetails adminUser = User.withUsername("admin")
                                    .password(pwdEncoder.encode("Password"))
                                    .roles("ADMIN")
                                    .build();

        UserDetails regularUser = User.withUsername("user")
                                    .password(pwdEncoder.encode("Password"))
                                    .roles("USER")
                                    .build();

        userMngr.createUser(adminUser);
        userMngr.createUser(regularUser);

        return userMngr;
    }

}
