package com.example.securitydemo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private Map<String, CustomUser> data;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Database() {
        data = new HashMap<>();

        CustomUser ichiro = new CustomUser(1,"ichiro",getPassword("ichiro123"), getGrants("ROLE_USER"));
        CustomUser jiro = new CustomUser(2,"jiro",getPassword("jiro123"), getGrants("ROLE_EDITOR"));
        CustomUser saburo = new CustomUser(3,"saburo",getPassword("saburo123"), getGrants("ROLE_REVIEWER"));
        CustomUser shiro = new CustomUser(4,"shiro",getPassword("shiro123"), getGrants("ROLE_ADMIN"));
        data.put("ichiro", ichiro);
        data.put("jiro", jiro);
        data.put("saburo", saburo);
        data.put("shiro", shiro);
    }

    public Map<String, CustomUser> getDatabase() {
        return data;
    }

    private String getPassword(String raw) {
        return passwordEncoder.encode(raw);
    }

    private Collection<GrantedAuthority> getGrants(String role) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }
}
