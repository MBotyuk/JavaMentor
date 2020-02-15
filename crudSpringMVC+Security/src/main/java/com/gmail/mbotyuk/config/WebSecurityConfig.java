package com.gmail.mbotyuk.config;

import com.gmail.mbotyuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.gmail.mbotyuk")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final UserDetailsService userService;
//    private final AuthenticationSuccessHandler authenticationSuccessHandler;
//
//
//    @Autowired
//    public WebSecurityConfig(@Qualifier("userServiceImpl") UserDetailsService userService, AuthenticationSuccessHandler authenticationSuccessHandler) {
//        this.userService = userService;
//        this.authenticationSuccessHandler = authenticationSuccessHandler;
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                //Доступ только для не зарегистрированных пользователей
//                .antMatchers("/registration").not().fullyAuthenticated()
//                //Доступ только для пользователей с ролью Администратор
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
//                //Доступ разрешен всем пользователей
//                .antMatchers("/").permitAll()
//                //Все остальные страницы требуют аутентификации
//                .anyRequest().authenticated()
//                .and()
//                //Настройка для входа в систему
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error")
//                //Перенарпавление на главную страницу после успешного входа
//                .defaultSuccessUrl("/")
//                .successHandler(authenticationSuccessHandler)
//                .permitAll()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true)
//                .permitAll();
//    }
//
//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
//        auth.userDetailsService(userService);
//    }
}
