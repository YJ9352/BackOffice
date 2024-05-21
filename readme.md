# 배달 시스템 Backend Server
![Title Image](https://github.com/YJ9352/BackOffice/blob/main/image/Delivery%20Service.gif)
## 프로젝트 개요
- 개발인원 : BE 5명
- 개발기간 : 24.01.22 ~ 24.01.29 (1주일)
- 프로젝트 설명 : 배달 시스템을 모방한 백엔드 시스템 개발

## 개발환경
- Backend : Kotlin, Spring Boot
- Data : Spring JPA, QueryDSL
- Security : Spring Security, JWT, OAuth 2.0
- DB : Supabase (PostgreSQL)
- Collaboration : IntelliJ IDEA, Git, GitHub Issues, Slack, IntelliJ IDEA

## ERD
![Title Image](https://github.com/YJ9352/BackOffice/blob/main/image/erd_7.png)
  
## 주요 개발 기능
### 데이터베이스 설계
- 기획 단계에서부터 설계를 책임지며 팀원들의 피드백과 회의를 통해 지속적으로 개선
- 효율성과 확장성을 고려하여 안정적인 데이터베이스 구조를 구축

### 가게 CRUD / 메뉴 CRUD
- 일반 사용자
  사용자 편의성을 고려한 가게 리스트 조회 및 메뉴의 전체/개별 조회 기능을 분리 구현
- 가게 관리자
  실제 서비스 중인 배달 어플리케이션 분석을 바탕으로 영업정지한 가게 또는 판매중지된 메뉴의 정보가 바로 삭제되지 않는다는 점을 발견
  해당 사실을 팀원과 공유하여 삭제를 어떤 방식으로 적용하는게 좋을지 회의를 진행하였고,
  1. 관리자가 가게 재영업 및 메뉴 재판매를 필요로 할 때 새로 등록하기 보다 복원이 쉽다.
  2. 이후 관련 데이터를 이용 할 가능성이 있다.
  
  위와 같은 이유로 영업 및 메뉴 상태변경을 추가해 Soft Delete 방식을 적용
- 공통부분
  1. 이용자 타입에 따른 권한 및 접근 제어를 구현하여 보안성을 강화
  2. CRUD기능을 안정적이며 확장 가능한 구조로 구축하여 기능 추가가 용이

## 프로젝트 설정
- DB 설정 환경변수 추가
```Kotlin
SPRING_DATASOURCE_URL=#{DB 주소}
ex) SPRING_DATASOURCE_URL=jdbc:postgresql://db.jrsvhsuhbgbvhmnyiovm.supabase.co:5432/postgres?user=postgres&password=#{password}
```
- application.yml 파일에 google client 설정 추가
```Kotlin
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

## 트러블 슈팅
### 테스트 중 결과값이 입력한 ID의 정보와 일치하지 않아 오류 발생
@PathVariable의 변수와 service의 변수가 둘 모두 Long 타입이라 순서를 바꿔 입력할 경우 다른 값이어도 그대로 인식하던 것을 올바른 순서로 수정하여 해결

### 메뉴 전체조회 테스트 중 결과값이 제대로 출력되지 않아 오류 발생
findByStoreId을 사용해야 했으나 findByIdOrNull을 사용했던 부분을 수정하여 해결
오류 수정 후 코드 전체를 리팩토링하여 중복되는 부분을 제거하고 알아보기 쉽게 정리

