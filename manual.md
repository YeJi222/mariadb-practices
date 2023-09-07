## Assignment02 - Bookmall Program
### [파일 위치]
- main 파일 : mariadb-practices/jdbc-practices/src/main/java/bookmall/main/BookMall.java
- dao 파일 : mariadb-practices/jdbc-practices/src/main/java/bookmall/dao/[dao 파일들]
- vo 파일 : mariadb-practices/jdbc-practices/src/main/java/bookmall/vo/[vo 파일들]
- dao test 파일 : mariadb-practices/jdbc-practices/src/main/java/bookmall/dao/test/[dao test 파일들]
- bookmall 모델 mwb 파일 : mariadb-practices/models/bookmall.mwb

(+) delete & initialize auto increment sql file : mariadb-practices/jdbc-practices
/deleteAlldataBookmall.sql

### 실행방법
- 레포지토리 클론 
```sh
$ git clone https://github.com/YeJi222/mariadb-practices.git
```

- bookmall.mwb
<img width="614" alt="image" src="https://github.com/YeJi222/mariadb-practices/assets/70511859/8b7130e0-719b-4112-9325-e7602e1e00fb">

#### [실행 결과 조건]
1. 회원 리스트(이름, 전화번호, 이메일, 비밀번호) – 2명
3. 카테고리 리스트(카테고리명) – 3개
4. 상품리스트(제목, 가격) – 3개
5. 카트 리스트(도서 제목, 수량, 가격) – 2개
6. 주문 리스트(주문 번호, 주문자(이름/이메일), 결제금액, 배송지) – 1개
7. 주문 도서 리스트(도서번호, 도서제목, 수량) – 2개

#### [BookMall.java 실행 결과]
<img width="974" alt="image" src="https://github.com/YeJi222/mariadb-practices/assets/70511859/f51cb1b7-0887-4797-b0b4-c0bbf98f0848">


