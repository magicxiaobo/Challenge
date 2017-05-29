package challenge;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/h2-console/*").permitAll()
                .and()
                .authorizeRequests().antMatchers("/challenge/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/*").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

// global configuration: if need user name and password to login, and also role set up
//
//    @Autowired
//    protected void configureGolbal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("philip").password("password").roles("USER")
//                .and()
//                .withUser("bob").password("password").roles("USER", "ADMIN");
//    }
}