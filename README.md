# BE Stack
| 구분 | 사용 기술 |
| --- | --- |
| 언어 | Java 11 (Spring Boot 2.7.15) |
| DB | AWS-RDS(Oracle), Mysql  |
| API | Rest api  |
| 보안 및 인증 | OAuth 2.0, JWT, Spring Security |
| 서버 배포 및 관리 | AWS |
| 패키지 관리 매니저 | Gradle |
| 테스트 프레임워크 | Junit5 |
| 로깅 및 모니터링 | SLF4J |
| api 문서화 | rest docs + swagger |


### **Back-end**

<img width="559" alt="backend" src="https://github.com/Al-ways/Al-ways-BE/assets/91714677/ec25544c-5fd3-4edb-9223-78cdd4d39105">

- **Springboot** 로 웹 어플리케이션 서버를 구축해요
- **Spring Data JPA(Hibernate)** 로 객체 지향 데이터 로직을 작성해요.
- **QueryDSL** 로 컴파일 시점에 SQL 오류를 감지해요. 가독성 높은 코드를 작성할 수 있어요.
- **Swagger**로 초기단계에 개발자와 클라이언트 팀 간의 원활한 의사 소통해요.
- **RestDocs로** 작성하여 API 문서를 정확하게 유지할 수 있어요.

### Auth
<img width="246" alt="auth" src="https://github.com/Al-ways/Al-ways-BE/assets/91714677/3965cf97-1f6a-4445-a353-14e8f013f426">

- **oauth2.0** 로 소셜 미디어 로그인 및 api 접근 권한 부여를 도와줘요.
- **JWT** 로 클라이언트와 서버간의 정보를 안전하게 전달해요.

### **Infra Structure**
<img width="123" alt="auth" src="https://github.com/Al-ways/Al-ways-BE/assets/91714677/36fa3db2-3e98-47f0-9755-2c926a1e1bd6">

- **AWS EC2** 를 사용해 서버를 구축해요.

### **DB**
<p>
  <img src="https://github.com/Al-ways/Al-ways-BE/assets/91714677/06215b4e-5238-4672-8c8e-8a038e2e4d94" align ="center" width="15%">
  <img src="https://github.com/Al-ways/Al-ways-BE/assets/91714677/57e9e30c-260f-485c-be87-1daed7d7e228" align ="center" width="15%">
</p>

- 공용 데이터 베이스는 **AWS-RDS**를 사용해요.
- Test 데이터 베이스는 **Mysql**를 사용해요.

### **TEST**
<img width="123" alt="auth" src="https://github.com/Al-ways/Al-ways-BE/assets/91714677/ad261b1f-e4e2-4574-ad61-a1dc3138830e">

- Junit5로 테스트 코드를 작성해요. 자신감 있는 리팩토링으로 기능 개발을 할 수 있어요.

