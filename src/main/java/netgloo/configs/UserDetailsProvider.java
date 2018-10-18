package netgloo.configs;

import org.springframework.context.annotation.Bean;
import netgloo.configs.CustomUserDetails;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsProvider {
    @Bean
    @Scope("prototype")
    public CustomUserDetails customUserDetails() {
    	System.out.println("im in usd provider");
        return (new CustomUserDetails());
    }
}
