package com.zemoso.tipster.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception{
        User.UserBuilder users= User.withDefaultPasswordEncoder();
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(users.username("Srivyshnavi").password("vyshnavi").roles("Admin"));
    }
    @Override
    protected void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/tipster").permitAll()
                .antMatchers("/tipster/admin/**").hasRole("Admin")
                .and().formLogin().loginPage("/tipsterlogin/showLoginPage")
                .loginProcessingUrl("/authenticateUser").permitAll().permitAll()
                .and().logout().logoutUrl("/tipster/logout").logoutSuccessUrl("/tipster/home").permitAll().and().exceptionHandling().accessDeniedPage("/tipsterlogin/accessDeniedPage");;
    }
}
