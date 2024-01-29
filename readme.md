# <strong>🛵배달 시스템 Backend Server</strong>


## 🎁 프로젝트 개요


- **개발 기간** : 24.01.22 ~ 24.01.29 (1주)
- **개발 환경** : Kotlin, Spring Boot, Supabase, PostgreSql
- **프로젝트 이름** : 배달 프로젝트
- **프로젝트 설명 :** 배달 시스템을 모방한 백엔드 시스템 개발


## 👩 Team B05

- <strong>오재영</strong>
    - [github](https://github.com/JYOH3246)
    - 역할 - 와이어 프레임, 프로필 관리, 계좌 관리, 인증/인가
- <strong>박유진</strong>
    - [github](https://github.com/YJ9352)
    - 역할 - ERD, 가게 CRUD, 메뉴 CRUD
- <strong>김성현</strong>
    - [github](https://github.com/lazzzykim)
    - 역할 - ERD, 리뷰 / 리뷰 답글 CRUD
- <strong>윤승환</strong>
    - [github](https://github.com/lovelyunsh)
    - 역할 - API 명세, 주문 Flow Chart, 장바구니 / 주문 CRUD
- <strong>김현득</strong>
    - [github](https://github.com/KimHyuenDeuk)
    - 역할 - 가게 CRUD 초안 작성



## **📚기술스택**
<div>
  <img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white">
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/github-000000?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/IntelliJ-000000?style=for-the-badge&logo=Intellijidea&logoColor=white">
  <br>
</div>

### **Backend**

- Spring Boot: 3.2.1
- Kotlin: 1.9.22

- Data
    - Spring JPA: 3.2.3
    - QueryDsl: 5.0.0

- Security
    - Spring Security: 6.2.0
    - JWT: io.jsonwebtoken:jjwt-api:0.12.3
    - Oauth 2.0

### **DB**

- SupaBase(postgreSQL): [https://supabase.com/dashboard/projects]

### **collaboration**

- Git, GitHub Issue, Slack


## 🎈 주요기능

### 프로필 관리 / 계좌 관리
- 회원가입/로그인/유저 정보 확인
- Spring Security 활용
- 소셜 로그인 기능 추가

### 가게 관리
- 일반유저 : 가게 목록 조회/가게 정보 조회
- 가게주인 : 본인 가게 목록 조회/개별 조회/가게 생성/정보 수정/영업상태 변경(영업중 / 영업중지)
    - 본인이 개설한 가게만 조회 / 생성 / 수정 / 상태변경 가능

### 메뉴 관리
- 전체 메뉴 조회/메뉴추가/메뉴수정/메뉴 상태변경(판매중/품절/판매중단)
- 각각 기능에 ROLE에 따라 인가 처리

### 리뷰 관리
- 가게 조회 시 리뷰 정보 함께 조회 / 리뷰 CRUD
- 자신의 가게에 리뷰 생성 불가
- 리뷰 답글 기능

### 장바구니 / 주문
- 일반유저 : 장바구니 메뉴 추가/제거, 주문 시도(계좌 잔액 검사), 주문 취소
- 가게주인 : 주문 상태 변경(주문 취소,주문 확정, 조리 완료, 배달 완료)
- 주문 조회 시 QueryDsl로 동적 쿼리 활용

## 🚩프로젝트 설정

- DB 설정 환경변수 추가
```
SPRING_DATASOURCE_URL=#{DB 주소}
ex) SPRING_DATASOURCE_URL=jdbc:postgresql://db.jrsvhsuhbgbvhmnyiovm.supabase.co:5432/postgres?user=postgres&password=#{password}
```

- application.yml 파일에 google client 설정 추가
```
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: #{client-id}
            client-secret: #{client-secret}
            redirect-uri: http://localhost:8080/login/oauth2/code/google
            scope:
              - profile
              - email
```


## 🏆 프로젝트 산출물

- [프로젝트 S.A](https://www.notion.so/b-5-2755bc44d6374ef3875dbef83bb72a17)
- [와이어프레임](https://www.figma.com/file/uAcbPBbKkPxbrXF4JkjufO/Untitled?type=design&node-id=0%3A1&mode=design&t=gc0xvVmrFpIi8bWn-1)
- [API 명세서](https://docs.google.com/spreadsheets/d/1IBSx4MNMpBJp5ZjguWEJdB4CDQiS_cr2QuPW_KS_C_g/edit#gid=0)
- [주문 FlowChart](https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fe075f211-1fd8-4567-8710-8c6c3691fa9f%2F13f74065-2a40-4d70-8fd0-42f06aafe711%2F%25EC%25A3%25BC%25EB%25AC%25B8_%25ED%2594%2584%25EB%25A1%259C%25EC%2584%25B8%25EC%258A%25A4.jpg?table=block&id=b9fdf1ef-3b74-4e95-a5ad-dca40eb3bfda&spaceId=e075f211-1fd8-4567-8710-8c6c3691fa9f&width=2000&userId=60bdfa4d-0758-4916-9e55-b7298dfa44c5&cache=v2)
