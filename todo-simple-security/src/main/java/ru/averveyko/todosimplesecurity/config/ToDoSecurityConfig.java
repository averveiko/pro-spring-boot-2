package ru.averveyko.todosimplesecurity.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ToDoSecurityConfig extends WebSecurityConfigurerAdapter {

    // Переопределить для настройки AuthenticationManagerBuilder (аутентификации)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("sashkam")
                .password(passwordEncoder().encode("keash"))
                .roles("ADMIN", "USER")
                .and()
                .withUser("123")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN", "USER");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Переопределить для настройки HttpSecurity (веб-безопастность)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .httpBasic();*/ // говорит само за себя (по-умолчанию спринговская bootstrap-web-форма)
        // настройки для кастомной web-login-формы
        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                    .permitAll()
                .anyRequest()
                    .fullyAuthenticated()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                .and()
                    .httpBasic(); // чтобы была возможность cURL'ом достучаться до API ($ curl -i localhost:8080/api/toDos -u sashkam:keash)
    }
}
