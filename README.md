Interceptor와 JwtToken을 이용한 API 인증 구현
- 테스트 방법 : localhost:8080/index
 
  1) 사용자 회원가입하면 auth0 JWT를 이용해 Token 생성하고, 사용자정보와 함께 저장
  2) 사용자가 ID, PW를 이용해 로그인을 하면 서버에서 사용자의 Token을 반환
  3) Client에 Token을 저장한 뒤, 검증이 필요한 서비스 이용 시 Header에 Token을 포함시켜 호출
  4) 서버에서 토큰을 검증한 후 API 응답을 수행
 
  구현환경 : IntelliJ, H2, JPA
 
 참고사이트 : https://galid1.tistory.com/638
