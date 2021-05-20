package tn.esprit.pidev.consommitounsi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import tn.esprit.pidev.consommitounsi.entities.user.UserType;

@EnableWebSecurity
public class UserSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/admin/**").hasAuthority(UserType.ADMIN.toString())
                .antMatchers("/deliverer/**").hasAnyAuthority(UserType.DELIVERER.toString(), UserType.ADMIN.toString())
                .antMatchers("/customer/**").hasAnyAuthority(UserType.CUSTOMER.toString(), UserType.DELIVERER.toString(),
                UserType.ADMIN.toString())
                .anyRequest().permitAll()
                .and().formLogin().successForwardUrl("/users/login").failureForwardUrl("/users/login")
                .and().exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean checkPassword(String clear, String encoded) {
        return new BCryptPasswordEncoder().matches(clear, encoded);
    }


}
