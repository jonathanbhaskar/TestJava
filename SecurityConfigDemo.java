import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfigDemo extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DemoAuthenticationProvider demoAuthenticationProvider;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {        
                
        http
        .authorizeRequests()
            .antMatcher("/user")
                .addFilterBefore(new DemoAuthenticationFilter(), BasicAuthenticationFilter.class)                
        ;
                        
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {        
        auth.authenticationProvider(demoAuthenticationProvider);        
    }    
    
}
