1. 모든작업은 dba(root)로
```sh
# mysql -u root -p
```

2. 데이터베이스 생성
```sh
MariaDB [(none)]> create database webdb;
MariaDB [(none)]> show databases;
```

3. 사용자 생성
```sh
MariaDB [(none)]> create user 'webdb'@'192.168.%' identified by 'webdb';
MariaDB [(none)]> create user 'webdb'@'localhost' identified by 'webdb';
```

4. 권한주기
```sh
MariaDB [(none)]> grant all privileges on webdb.* to 'webdb'@'localhost';
grant all privileges on webdb.* to 'webdb'@'localhost';
flush privileges;
```

5. 확인하기
```sh
# mysql -u webdb -D webdb -p
password: webdb
```

6. workbench에서 load 명령어 쓸 때, 세팅 필요
- Database > Manage Server Connections > Connection > Advanced > Others:
```sh
...
OPT_LOCAL_INFILE=1
...
````
<img width="397" alt="image" src="https://github.com/YeJi222/mariadb-practices/assets/70511859/d69e6b86-645a-4a54-89ce-5f49bfdba28c">
