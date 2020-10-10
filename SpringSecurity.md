# Spring Security tips

### Задать настройки SpringSecurity можно 
* ничего не делая - спринг сам сгенерирует пароль и выведет в лог
* в application.properties:
```
spring.security.user.name=apress
spring.security.user.password=springboot2
spring.security.user.roles=ADMIN,USER
```
* программным способом:
```Java
@Configuration
public class ToDoSecurityConfig extends WebSecurityConfigurerAdapter {
 @Override
 protected void configure(
 AuthenticationManagerBuilder auth) throws Exception {
 auth.inMemoryAuthentication()
 .passwordEncoder(passwordEncoder())
.withUser("apress")
.password(passwordEncoder().encode("springboot2"))
.roles("ADMIN","USER");
// через .and() можно добавить еще юзеров
 }
 @Bean
 public BCryptPasswordEncoder passwordEncoder() {
 return new BCryptPasswordEncoder();
 }
}
```