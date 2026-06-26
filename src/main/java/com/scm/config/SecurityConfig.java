package com.scm.config;

import com.scm.services.SecurityCustomUserDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;


@Configuration
public class SecurityConfig {

   /* private final UserDetailsPasswordService userDetailsPasswordService;

    public SecurityConfig(UserDetailsPasswordService userDetailsPasswordService) {
        this.userDetailsPasswordService = userDetailsPasswordService;
    }*/
    //user create and login using Java code with in memory service
    // private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    /*   @Bean
       public UserDetailsService userDetailsService() {

           UserDetails user1 = User
                   .withDefaultPasswordEncoder()
                   .username("admin123")
                   .password("admin123")
                   .roles("ADMIN", "USER")
                   .build();
           UserDetails user2 = User
                   .withDefaultPasswordEncoder()
                   .username("user123")
                   .password("password")
                   // .roles("ADMIN","USER")
                   .build();
           var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2);
           return inMemoryUserDetailsManager;
       }*/
    @Autowired
    private SecurityCustomUserDetailsService userDetailsService;

    //configuration of authentication provider spring security
    @Bean
    public AuthenticationProvider authenticationProvider(SecurityCustomUserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        //Optional set your password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {

        //configuration
        httpSecurity.authorizeHttpRequests((authorize) -> {
            // authorize.requestMatchers("/home","/register","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        //form default login
        //agar hame kuch bhi change krna hua to hama yaha ayenge form login se related
        // httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.formLogin(
                formLogin -> {
                    formLogin.loginPage("/login");
                    formLogin.loginProcessingUrl("/authenticate");
                    formLogin.successForwardUrl("/user/dashboard");
                    formLogin.failureForwardUrl("/login?error=true");
                    //formLogin.defaultSuccessUrl("/home");
                    formLogin.usernameParameter("email");
                    formLogin.passwordParameter("password");
                    //formLogin.permitAll();
                    /*formLogin.failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

                        }
                    });
                    formLogin.successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

                        }
                    });*/

                }
        );
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(formLogout -> {
            formLogout.logoutUrl("/logout");
            formLogout.logoutSuccessUrl("/login?logout=true");
        });
        return httpSecurity.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
