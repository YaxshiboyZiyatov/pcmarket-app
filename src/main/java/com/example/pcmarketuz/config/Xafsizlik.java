package com.example.pcmarketuz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Xafsizlik extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("super").password(passwordEncoder().encode("super")).roles("SUPER_ADMIN").authorities("READ_ALL_PRODUCT","ADD_PRODUCT","EDIT_PRODUCT","DELETE_PRODUCT","READ_ONE_PRODUCT")
                .and()
                .withUser("moderator").password(passwordEncoder().encode("moderator")).roles("MODERATOR").authorities("ADD_PRODUCT","EDIT_PRODUCT","READ_ALL_PRODUCT","READ_ONE_PRODUCT")
                .and()
                .withUser("operator").password(passwordEncoder().encode("operator")).roles("OPERATOR").authorities("READ_ALL_ORDER","ADD_ORDER","READ_ONE_ORDER");

    }
//    SUPER_ADMIN, MODERATOR va OPERATOR rollari bo’lsin.
//    SUPER_ADMIN har qanday ishni qila oladi;
//    MODERATOR mahsulot qo’sha oladi va tahrirlay oladi ,ammo o’chira olmaydi;
//    OPERATOR buyurtmalar bilan ishlaydi, mahsulotni o'chira olmaydi va tahrirlay olmaydi.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/api/product/*").hasRole("USER")
//                .antMatchers(HttpMethod.GET,"/api/article/").hasAnyRole("DIRECTOR","MANAGER")
//                .antMatchers("/api/product/**").hasRole("DIRECTOR")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
