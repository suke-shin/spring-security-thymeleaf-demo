package com.example.securitydemo.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Database database = new Database();

    public CustomUser getUserByUsername(String username) {
        CustomUser originUser = database.getDatabase().get(username);
        if (originUser == null) {
            return null;
        }

        /*
        * Spring Securityがユーザーを取得した後、
        * ユーザーのパスワードフィールドを空白にしてセキュリティを確保する
        */

        return new CustomUser(originUser.getId(),
                originUser.getUsername(),
                originUser.getPassword(),
                originUser.getAuthorities());
    }
}
