package com.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;



@Configuration
@ComponentScan(basePackageClasses= com.services.database.realization.UserServiceImpl.class)
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(UserDetailsService userDetailsService, AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    //@Autowired
    // private AccessDeniedExceptionHandler accessDeniedExceptionHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/users/sign-up").permitAll()
                .antMatchers("/account/forgot-password").permitAll()
                .antMatchers("/account/forgot-password").permitAll()
                .antMatchers("/upload").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/sign-in")
                .defaultSuccessUrl("/account")
                .permitAll()
                .and()
                .logout()
                .permitAll();
             /*   .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedExceptionHandler);*/
    }
}