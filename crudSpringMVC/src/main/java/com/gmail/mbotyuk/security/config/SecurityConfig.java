package com.gmail.mbotyuk.security.config;

import com.gmail.mbotyuk.security.details.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.gmail.mbotyuk")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(bCryptPasswordEncoder().encode("admin")).roles("ADMIN")
                .and()
                .withUser("userDefault").password(bCryptPasswordEncoder().encode("user")).roles("USER");
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .usernameParameter("name")
                .passwordParameter("password")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/user/**", "/admin/**").authenticated()
                .antMatchers("/", "/res/**").permitAll()
                .antMatchers("/user").hasRole("USER") //.access("hasAuthority('USER')")
                .antMatchers("/admin", "/admin/**", "/user").hasRole("ADMIN") //.access("hasAuthority('ADMIN')")
                .antMatchers("/registration").not().fullyAuthenticated()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/logout") ///login?logout или вообще убрать
                .logoutSuccessUrl("/")
                .permitAll()
                .invalidateHttpSession(true) //убрать
                .deleteCookies("JSESSIONID") //убрать
                //.and()
                //.exceptionHandling()
                //.accessDeniedPage("/403")
        ;
    }
}