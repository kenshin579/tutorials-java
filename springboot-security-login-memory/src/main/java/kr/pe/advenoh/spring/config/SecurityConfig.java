package kr.pe.advenoh.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user")
                // password = userpassword
                .password("$2a$10$nodSZsM7Lyi34l3/YEg61uTVDRIgH.DkM/WF4AM0BKTCGINBOnFOm")
                .authorities("ROLE_USER")
                .and()
                .withUser("admin")
                // password = adminpassword
                .password("$2a$10$0VRu6ZC4zcuXZiS34AaPF.gDcbq25Z5lX01gnf97iBNdl4WeCbCXC")
                .roles("ADMIN"); //자동으로 ROLE_를 붙혀줌 (.roles 함수)
    }
}