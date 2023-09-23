package com.ltdd14.FarmMarket.config;


import com.ltdd14.FarmMarket.handlers.LoginSuccessHandler;
import com.ltdd14.FarmMarket.handlers.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.ltdd14.FarmMarket.model",
        "com.ltdd14.FarmMarket.model.user",
        "com.ltdd14.FarmMarket.handlers",
})
@Order(2)
public class SpringSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LoginSuccessHandler loginHandler;
    @Autowired
    private MyLogoutSuccessHandler logoutHandler;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    //Cung cap thong tin de chung thuc
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }


    @Bean(name = "mvcHandlerMappingIntrospector")
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }

    //Phan quyen
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //  super.configure(http); //To change body of generated methods, choose Tools | Templates.
//        http.formLogin().loginPage("/user/login").
//                usernameParameter("username").
//                passwordParameter("password");
//
//
//        http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);
//
//
//        http.formLogin().successHandler(this.loginHandler).failureUrl("/user/login?error");
//
//        http.logout().logoutSuccessHandler(this.logoutHandler);
//
//        http.exceptionHandling().accessDeniedPage("/user/login?accessDenied");
//
//
//        http.csrf().disable();
//        http.cors();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin().loginPage("/user/login").
                usernameParameter("username").
                passwordParameter("password");

        http.formLogin().successHandler(this.loginHandler).failureUrl("/user/login?error");
        http.logout().logoutSuccessHandler(this.logoutHandler);
        http.exceptionHandling().accessDeniedPage("/user/login?accessDenied");



        http.authorizeHttpRequests()
                .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }
}
