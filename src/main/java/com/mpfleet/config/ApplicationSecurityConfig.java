package com.mpfleet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

    private final UserDetailsService userDetailsService;

    @Autowired
    public ApplicationSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers( "/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/login","/register","/users/addNew").permitAll()
                .requestMatchers("/security/**").hasAuthority("SUPER_ADMIN")
                .requestMatchers("/hr/**").hasAnyAuthority("SUPER_ADMIN", "ADMIN", "HR_ADMIN")
                .requestMatchers("/fleet/**").hasAnyAuthority("SUPER_ADMIN", "ADMIN", "FLEET_ADMIN")
                .requestMatchers("/accounts/**").hasAnyAuthority("SUPER_ADMIN", "ADMIN", "ACCOUNTS_ADMIN")
                .requestMatchers("/ticket/**", "/tickets").hasAnyAuthority("SUPER_ADMIN", "ADMIN", "TICKET_ADMINS")
                .requestMatchers("/admin/**").hasAnyAuthority("SUPER_ADMIN", "ADMIN")
                .requestMatchers("/fleet","/hr", "/accounts", "/tickets", "/admin").hasAnyAuthority("SUPER_ADMIN", "ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/index")
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
        ;

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);

        provider.setPasswordEncoder(bCryptPasswordEncoder());

        return provider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
