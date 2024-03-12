# 참고
- resource폴더에 PostMan으로 API 요청응답 샘플을 확인 할 수 있게 json파일을 넣어놓았습니다
- resource폴더에 테이블 sql문 파일을 넣어놓았습니다.
- 회원가입 기능에 스크랩핑 API 로직이 있습니다. => 테스트 샘플에 나와있는 유저 목록만 회원가입 하기위해 추가하였습니다.

# 구현 현황
> 계획한 단위 기능이 모두 체크된 것은 기능 완성률 100%
### Http Response
- ## 2xx
- [x] success 2xx 상태코드, 메세지를 담는 HttpResponseVO 생성: enum -> 상태 정의
- [x] Response body DTO(HttpResponseDTO) 생성 -> 응답 할 객체
- ## 4xx Error
> - Type: 비즈니스 로직 에러, db 에러, valid 에러, 외부API 에러, invalid token 에러 => Exception처리
- [x] Error 4xx, 5xx 상태코드, 메세지를 담는 HttpResponseVO 생성: enum -> 상태 정의
- [x] ExceptionResolver: exception -> exceptionHandler
- [x] Exception ErrorResponse DTO

### common: 로그인 기능 제외한 모든 기능은 공통으로 jwt검증
- [x] JWT 검증: JWT payload - 회원 타입, 회원 아이디

### 1. 회원가입
#### - 비즈니스 로직
- [x] DB MEMBER 테이블에 회원 저장
- [x] 스크랩핑 호출로 과제에 있는 샘플만 저장 할 수 있도록 조건 추가
- [x] 주민등록번호 정규식표현 패턴 valid check 로직 추가
- [ ] 비밀번호 주민등록번호 암호화 처리
#### - Exception 에러 처리: false인 경우
- [x] validation 사용해서 request body로 들어오는 필드 MethodArgumentNotValidException 처리
- [x] 주민등록번호 정규식표현 패턴 적용 -> false -> InValidPatternTypeException

### 2. 로그인
#### - 비즈니스 로직
- [x] DB MEMBER 테이블에서 아이디와 비밀번호 일치하는 회원 조회
- [x] JWT 인증 토큰 발급

### 3. 스크랩핑
#### - 비즈니스 로직
- [x] 외부 API로 회원의 소득 정보 요청 응답
- [x] 주민등록번호와 이름 매칭된 유저 없을 경우 예외 처리
- [x] 외부 API로부터 받은 스크랩 데이터에 저장
    - [x] income_tax Table: 근로소득, 산출 세액, 주민등록번호, expense 조회할 FK
    - [x] expense Table: 보험료납입액, 의료비, 교육비, 기부금, 퇴직연금납부액
#### - Exception 에러 처리
- [x] 주민등록번호와 이름 매칭된 유저 없을 경우 예외 처리
- [x] 인증 토큰 필터

### 4. 결정 세액 조회
#### - 비즈니스 로직
- [x] 주민등록번호와 일치하는 스크랩 데이터를 조회
- [x] 결정세액 계산할 Util 메소드 추가
- [x] 퇴직 연금 세액 공제액 계산
#### - Exception 에러 처리: false인 경우
- [x] 인증 토큰 필터

### 5. 회원 거래 이력 요청
#### - 비즈니스 로직
- [x] DB remittance_log 테이블에서 user_id와 일치하는 레코드들 조회
- [x] 유저의 이름 조회

# DB 테이블
> member: 회원
> income_tax: 소득정보 세금 => 조인 => expense<br>
> table expense 참조하기 income_tax에 expense_id FK 지정
> sql 파일은 resources 폴더에 있습니다.
```Table
CREATE TABLE member (
    INDEX Long AUTO_INCREMENT,
    REG_NO VARCHAR(255) PRIMARY KEY NOT NULL,
    USER_ID VARCHAR(255) UNIQUE NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    NAME VARCHAR(255) NOT NULL
);


CREATE TABLE income_tax (
    income_tax_id Long AUTO_INCREMENT,
    reg_no varchar(255) UNIQUE not null,
    earned_income decimal(10, 0) not null,
    calculated_tax decimal(10, 0) not null,
    expense_id Long,
    FOREIGN KEY (expense_id) REFERENCES expense(expense_id)
);


CREATE TABLE expense (
    expense_id Long AUTO_INCREMENT PRIMARY KEY,
    insurance_premium decimal(10, 0) not null,
    medical_expenses decimal(10, 0) not null,
    education_expenses decimal(10, 0) not null,
    donation_amount decimal(10, 0) not null,
    retirement_fund decimal(10, 0) not null
);
```

# 이슈

Springboot Security를 도입하여 인증 인가를 관리하려 했으나 filter 부분에서 지속적으로
authenticationManager 객체를 불러오지 못하여 해결하기위해 여러 시도를 해보았지만 실패했다가
AuthenticationManager 클래스를 살펴보던중 메소드 하나를 발견했는데 이 메소드를 실행해야
객체를 사용할 수 있다 판단하여 코드를 추가하였습니다.
```dtd
@Autowired
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        
        // 이 부분!!!!
        super.setAuthenticationManager(authenticationManager);

        log.info("authenticationManager" + authenticationManager);
        setFilterProcessesUrl(JwtConstants.AUTH_LOGIN_END_POINT);
    }
```
인스턴스만 불러오고 authenticationManager를 세팅하지 않아 발생한 문제점이였습니다.
실행에 성공은 하였지만 시간상 Springboot Security로 인증 인가를 구축하기에는 해결해야할 에러들이 많아서
급하게 was단에 인증처리를 비즈니스 로직에 추가 하였습니다. 또한 Springboot Security bcrypt를
사용하여 암호화하여 저장했지만 springboot security로 인해 발생하는 에러 떄문에 암호화 부분을 과감하게 삭제하고
기능 구현 위주로 과제를 마무리하였습니다. <br>
과제는 끝났지만 제출 기간 이후에는 Springboot Security를 도입하여 인증인가 부분을 완성시킬 예정입니다.<br>
현재 마무리 못한 로직들은 개인적으로 추가할 예정입니다. <br>
테스트 코드도 추가해보고 여러 가지 시도해 볼 생각입니다.<br>
일주일 동안 좋은과제 할 수 있어서 감사헀습니다.<br>
지원 기회가 또 찾아온다면 더 성장된 개발자로 또 지원하겠습니다.
