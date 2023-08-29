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

OPT_LOCAL_INFILE=1
