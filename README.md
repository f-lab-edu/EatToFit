<div align="center">
  <img src="https://github.com/user-attachments/assets/419eeb62-c629-4e2d-937b-7bd9a89c63a9" width="200px" height="200px">
  <br>
  <b>EatToFit: 음식을 운동으로</b>
  <br>
  <p>EatToFit 서비스는 먹은 음식의 칼로리에 해당되는 운동 플랜을 생성해줌으로써 심리적 부담감을 낮춰줄 수 있는 서비스입니다.</p>
</div>

<br>
<h3>🍔 개발 기간</h3>
24.08.06 ~ ing

<br>
<br>
<h3>🍔 사용 기술</h3>
<ul>
  <li>Java 21 (LTS)</li>
  <li>Spring
    <ul>
      <li>Spring boot 3.3.2</li>
      <li>Spring Data JPA 3.3.2</li>
      <li>Spring AI 1.0.0 M2</li>
      <li>Spring REST Docs 3.0.1</li>
    </ul>
  </li>
  <li>Querydsl 5.1.0</li>
  <li>Jasypt 3.0.5</li>
  <li>JJWT 0.12.6</li>
  <li>Database
    <ul>
      <li>Redis (Spring Data Redis 3.3.2)</li>
      <li>MySQL 8.3.0</li>
      <li>Flyway-MySQL 10.8.1</li>
    </ul>
  </li>
  <li>Test
    <ul>
      <li>H2 2.2.224</li>
      <li>Mockito 5.11.0</li>
      <li>RestAssured 5.5.0</li>
    </ul>
  </li>
</ul>

<br>
<h3>🍔 실행 방법</h3>
<ol>
<li>jasypt 환경 변수를 관리자에게 전달받습니다.</li>
<li>인텔리제이의 환경 변수 안에 1번의 값을 <code>ENCRYPT_KEY={VALUE}</code> 형태로 넣습니다.</li>
<li>아래 링크를 통해 OAuth 코드를 얻습니다.</li>
<pre>
// kakao

https://kauth.kakao.com/oauth/authorize?response_type=code&amp;client_id=a4b1f62f6ee5ff7808de3fbdc9fd025d&amp;redirect_uri=http://localhost:8080/oauth/kakao
</pre>
<pre>
// google

https://accounts.google.com/o/oauth2/v2/auth?client_id=153893114774-fki8eqg7uiukvr0sqmk992odbb1l9rem.apps.googleusercontent.com&redirect_uri=http://localhost:8080/oauth/google&response_type=code&scope=openid%20email%20profile
</pre>
<li>OAuth 코드 및 provider를 선택하여 <code>/api/auth/login</code>에 아래처럼 request body를 요청하여 액세스 토큰 및 리프레시 토큰을 획득하고, 해당 토큰을 Bearer 방식으로 보내 다른 API들을 이용합니다.</li>
<pre><code class="language-json">
{
    "provider" : "kakao", // kakao | google
    "code": "3번 과정을 거쳐 얻은 OAuth 기관의 코드"
}
</code></pre>
</ol>

<br>
<h3>🍔 개발 과정</h3>
<a href="https://devwriter.tistory.com/category/%E2%9C%A8%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/EatToFit%20%5BF-Lab%5D">개인 블로그</a>에 프로젝트 소개 및 기술적 고민 과정을 담았습니다.
<br>
<ul>
  <li><a href="https://devwriter.tistory.com/52">프로젝트 아이디어 소개</a></li>
  <li><a href="https://devwriter.tistory.com/53">API 설계 과정에서의 고민 (1)</a></li>
    <ul>
      <li>사진 업로드 방식을 PresignedURL로 한 이유</li>
      <li>이미지 분석 과정을 POST 대신 GET으로 한 이유</li>
      <li>커서 페이지네이션을 선택한 이유</li>
      <li>삭제 방식을 Soft delete로 한 이유</li>
    </ul>
  <li><a href="https://devwriter.tistory.com/54">API 설계 과정에서의 고민 (2)</a></li>
    <ul>
      <li>DELETE 시 200 OK와 204 No Content의 사용 시점 고민</li>
      <li>PresignedURL 획득 API를 통일한 이유</li>
      <li>ChatGPT에게 전달할 쿼리 파라미터 값 선택 과정</li>
      <li>id 반환 방식과 response body 반환 방식 선택의 확실한 기준</li>
    </ul>
</ul>
