# Spring Boot + Spring Security + Thymeleaf チュートリアル

## 目的
Spring Boot、Spring Security、Thymeleafを使った認証・認可のデモアプリケーションの実装。

## 要件
- Spring Boot 2
- Spring Security 5
- Thymeleaf 2
- JDK 8

## 定義

- 認証
  - ユーザーとパスワードを使ってフォーム認証する
  - ユーザまたはパスワードが違っていたらエラーメッセージを返す
- 認可
  - 管理者・レビュアー・編集者・一般ユーザーでロールを分ける
    - 一般ユーザーは /user/home のみアクセス可能
    - 編集者は /user/home と /user/editorにアクセス可能
    - レビュアーは /user/home と /user/reviewer にアクセス可能
    - 管理者はすべてにアクセス可能

HashMapでデータベースを構築しました。

| ID | ユーザー名  | パスワード   | 権限     |
| --- | ---------- | ---------- | -------- |
| 1  | ichiro     | ichiro123  | user     |
| 2  | jiro       | jiro123    | editor   |
| 3  | saburo     | saburo123  | reviewer |
| 4  | shiro      | shiro123   | admin    |

- user: もっとも基本的な権限
- editor: 編集者。ユーザー権限に編集者権限を追加しました
- reviewer: レビュアー。編集者と同じレベルの権限を持っています
- admin 管理者。すべての権限を持っています。

APIの定義

| Url            | 説明               | アクセシブル     |
| -------------- | ------------------ | ---------------- |
| /              |                    | 誰でも           |
| /login         |                    | 誰でも           |
| /logout        |                    | ログイン時誰でも |
| /user/home     |                    | user             |
| /user/editor   |                    | editor, admin    |
| /user/reviewer |                    | reviewer, admin  |
| /user/admin    |                    | admin            |
| /403           | アクセス権限がない | 誰でも           |
| /404           | not found          | 誰でも           |
| /500           | サーバー内部エラー | 誰でも           |

-----------------------------------------------------------
## 参考

これらは以下の情報を参考にしました。

- [Spring Boot2 + Spring Security + Thymeleaf 简单教程](https://www.inlighting.org/archives/spring-boot-security-thymeleaf)
- [Spring Security＆Thymeleafで認証・認可](https://qiita.com/gushernobindsme/items/dc97f80754b7d481a16a)


