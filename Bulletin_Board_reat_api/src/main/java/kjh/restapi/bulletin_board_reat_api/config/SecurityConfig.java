package kjh.restapi.bulletin_board_reat_api.config;

import kjh.restapi.bulletin_board_reat_api.oauth2.CustomOAuth2LoginSuccessHandler;
import kjh.restapi.bulletin_board_reat_api.oauth2.CustomOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomOauth2UserService customOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.oauth2Login(oauth -> oauth
                .userInfoEndpoint(endpoint -> endpoint.userService(customOauth2UserService))
                .successHandler(new CustomOAuth2LoginSuccessHandler()));

        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/home").permitAll()
                .anyRequest().permitAll());
        return http.build();
    }
}
