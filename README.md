# 📚 Study Helper - 학습 관리 웹 애플리케이션

> 효율적인 학습을 위한 올인원 스터디 도구

## 🎯 프로젝트 소개

Study Helper는 학습자들이 체계적으로 공부할 수 있도록 도와주는 웹 애플리케이션입니다. 
타이머를 통한 학습 시간 관리, 할 일 목록 작성, 학습 커뮤니티 게시판 등의 기능을 제공하여 
효과적인 학습 환경을 구축할 수 있습니다.

## ✨ 주요 기능

### 🔐 회원 관리
- **회원가입/로그인**: 이메일 기반 계정 시스템
- **MBTI 정보**: 사용자의 성향을 파악하여 개인화된 서비스 제공
- **세션 관리**: 안전한 로그인 상태 유지

### ⏰ 스터디 타이머
- **과목별 시간 측정**: 각 과목별로 독립적인 타이머 운영
- **누적 시간 관리**: 학습한 총 시간을 시간/분/초 단위로 정확히 기록
- **학습 통계**: 전체 학습 시간 통계 및 과목별 분석

### ✅ 할 일 관리 (To-Do List)
- **할 일 추가/수정/삭제**: 간편한 CRUD 기능
- **완료 상태 관리**: 체크박스를 통한 완료/미완료 상태 토글
- **개인별 관리**: 로그인한 사용자별 독립적인 할 일 목록

### 📝 학습 게시판
- **게시글 작성**: 학습 내용 공유 및 질문
- **게시글 조회/수정/삭제**: 완전한 CRUD 기능
- **사용자별 게시글**: 작성자 정보와 함께 게시글 관리

## 🛠 기술 스택

### Backend
- **Framework**: Spring Boot
- **Language**: Java
- **Database**: JPA/Hibernate
- **Architecture**: MVC Pattern

### Frontend
- **Template Engine**: Thymeleaf
- **Styling**: HTML/CSS
- **JavaScript**: 타이머 및 동적 기능 구현

### Database
- **ORM**: JPA (Java Persistence API)
- **Repository Pattern**: Spring Data JPA

## 📁 프로젝트 구조

```
src/main/java/com/twoteam/toyproject/
├── controller/          # 웹 컨트롤러
│   ├── MemberController.java    # 회원 관리
│   ├── TimerController.java     # 타이머 기능
│   ├── ToDoController.java      # 할 일 관리
│   └── BoardController.java     # 게시판 기능
├── entity/             # 데이터베이스 엔티티
│   ├── Member.java
│   ├── TimerEntity.java
│   ├── ToDoEntity.java
│   └── Board.java
├── dto/                # 데이터 전송 객체
├── repository/         # 데이터 접근 계층
├── service/           # 비즈니스 로직
└── api/               # REST API (개발 중)
```

## 🚀 시작하기

### 사전 요구사항
- Java 11 이상
- Maven 또는 Gradle
- IDE (IntelliJ IDEA, Eclipse 등)

### 설치 및 실행

1. **프로젝트 클론**
```bash
git clone [repository-url]
cd toyproject
```

2. **의존성 설치**
```bash
./mvnw clean install
```

3. **애플리케이션 실행**
```bash
./mvnw spring-boot:run
```

4. **브라우저에서 접속**
```
http://localhost:8080
```

## 📱 사용 방법

### 1. 회원가입 및 로그인
- 메인 페이지에서 회원가입 진행
- 이름, 이메일, 비밀번호, MBTI 정보 입력
- 로그인 후 모든 기능 이용 가능

### 2. 스터디 타이머 사용
- `/timer` 페이지에서 과목 추가
- 각 과목별로 독립적인 타이머 시작/정지
- `/total` 페이지에서 전체 학습 시간 확인

### 3. 할 일 관리
- `/todo` 페이지에서 할 일 추가
- 체크박스로 완료 상태 변경
- 수정/삭제 기능으로 할 일 관리

### 4. 게시판 이용
- `/board` 페이지에서 게시글 목록 확인
- 새 게시글 작성 및 기존 게시글 수정/삭제

## 🎨 주요 특징

### 📊 데이터 관리
- **개인화**: 모든 데이터가 사용자별로 독립적으로 관리
- **실시간 업데이트**: AJAX를 통한 실시간 데이터 갱신
- **데이터 무결성**: JPA를 통한 안전한 데이터베이스 연산

### 🔒 보안
- **세션 기반 인증**: 안전한 로그인 상태 관리
- **접근 제어**: 로그인하지 않은 사용자의 기능 접근 차단

### 📱 사용자 경험
- **직관적인 UI**: 간단하고 사용하기 쉬운 인터페이스
- **반응형 설계**: 다양한 화면 크기에 대응

## 🔄 개발 계획

### 현재 구현된 기능
- ✅ 회원 관리 시스템
- ✅ 스터디 타이머
- ✅ 할 일 관리
- ✅ 게시판 기능

### 추후 개발 예정
- 🔄 REST API 완성 (현재 주석 처리됨)
- 📝 댓글 시스템 (CommentApiController)
- 📊 학습 통계 대시보드
- 🎯 학습 목표 설정 기능

## 👥 팀 정보

- **팀명**: Two Team
- **프로젝트 타입**: Toy Project (사이드 프로젝트)

## 📞 문의사항

프로젝트에 대한 문의사항이나 개선 제안이 있으시면 언제든지 연락해주세요!

---

⭐ **효율적인 학습을 위한 첫 걸음, Study Helper와 함께 시작하세요!** ⭐
