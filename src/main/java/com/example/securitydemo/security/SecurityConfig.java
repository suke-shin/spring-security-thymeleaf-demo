package com.example.securitydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true
)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String SECRET_KEY = "123456";

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    * Spring Securityを構成するには、次の点に注意する必要があります。
    * 1. Spring SecurityではデフォルトでCSRFが有効になっていますが、
    * 現時点では、送信するPOSTフォームにCSRFを渡すための非表示フィールドが必要です。
    * また、ログアウトでは、POSTでユーザーをログアウトする必要があります。
    * */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login") // カスタムユーザーログインページ
                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutUrl("/logout") // カスタムユーザーログアウトページ
                .logoutSuccessUrl("/")
                .and()
                .rememberMe() // パスワード記憶をオンにする
                .rememberMeServices(getRememberMeServices())
                .key(SECRET_KEY)
                .and()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

    private TokenBasedRememberMeServices getRememberMeServices() {
        TokenBasedRememberMeServices services = new TokenBasedRememberMeServices(SECRET_KEY, customUserDetailsService);
        services.setCookieName("remember-cookie");
        services.setTokenValiditySeconds(100);
        return services;
    }
}
