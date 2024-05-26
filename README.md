# GASIP : 가천대학교 재학생/졸업생 대상 가천대 교수님 익명 리뷰 서비스

<div align="center">
  <br>
  <img width="234" alt="image" src="https://github.com/GASIP-PROJECT/gasip-backend/assets/114489245/55e77169-b2fa-4220-ba23-349dcb8d41ed">
  <br><br>
  
  ![logo](https://img.shields.io/badge/GASIP-40AEF0.svg?style=for-the-badge)
</div>

## ⭐️ 앱 다운로드 링크
> 테스트 플라이트로 알파 테스트 진행중
<br>

## 💡 프로젝트 주제

- **익명의 가천대학교 교수님 및 강의 리뷰 서비스**
- **교수님에 대한 학생들의 진솔한 평가 공유 및 자유로운 피드 작성이 가능한 모바일 플랫폼 개발**

<br>

## 🏗 아키텍처
> 작성중

<br>

## 📝 프로젝트 개요

- [목적] 사용자가 특정 교수에 대한 정보와 평가를 한눈에 파악함으로서 수강신청 시 고려할 정보를 온전히 얻도록 함
- [배경] 수강신청/대학원 지도 교수 선택 등 교수님에 대한 정보가 필요한 상황에서 교수관련 정보 부족으로 올바른 선택 불가

<br>

## 📝 프로젝트 목표
- 가천대학교 학생 사이에서 지속적으로 사용되는 커뮤니티 앱 개발 (일 평균 10명 이상)
- 서버 운영 비용 최소화 (AWS 청구서 기준 월 3만원 이하)
- 사용자 초기 접근성 및 인지도 강화

<br>

## 🚀 프로젝트 인원 및 기간

- **개발 인원**: FE 2명 & BE 2명 & 디자인 1명
- **프로젝트 기간**: 23.08.01 ~ 진행중

<br>

## ⭐️ 핵심 기능

### ❗️강조하고 싶은 기능!
- [조회수 중복 증가 방지 로직](https://hyem5019.tistory.com/entry/Gasip-%EA%B2%8C%EC%8B%9C%EA%B8%80-%EC%A1%B0%ED%9A%8C%EC%88%98-%EC%A4%91%EB%B3%B5-%EB%B0%A9%EC%A7%80-%EC%B2%98%EB%A6%AC-%EB%A1%9C%EC%A7%81-%EA%B5%AC%ED%98%84) : 5분 이내 동일 게시글 접근 시 조회수 증가 방지 적용
- [조회수 동시성 보장 로직](https://hyem5019.tistory.com/entry/Gasip-Redis-Sync-Schedule-%EC%9D%84-%EC%A0%81%EC%9A%A9%ED%95%B4-%EC%A1%B0%ED%9A%8C%EC%88%98-%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%B2%98%EB%A6%AC) : 유튜브 조회수 알고리즘에서 착안한 Sync Schedule 기법 적용

<br>

### 회원

> 회원 가입을 통해 각자 고유의 아이디를 생성하여 로그인을 진행한다. <br>
> 회원 가입 시 가천대학교 이메일로 인증번호를 인증한 사람만 회원으로 가입할 수 있다. <br>
> JWT 토큰을 이용하여 인증,인가를 진행하며 인가 시간이 초과한 경우 재로그인을 해야 한다.<br>
> 아이디와,비밀번호를 통하여 인증을 진행하며 일치하지 않을 경우 서비스 사용을 할 수 없다.<br>
> 회원 가입시 회원의 비밀번호는 암호화하여 DB에 저장한다.<br>

### 마이페이지

> 사용자 별 작성 글에 대한 리스트를 확인할 수 있다.<br>
> 닉네임 변경을 할 수 있다.<br>
> 비밀번호 변경을 할 수 있다.<br>
> 로그아웃 및 회원탈퇴를 할 수 있다. <br>
> 문의하기를 클릭해 서비스 공식 카카오톡 계정에 문의할 수 있다. <br>

### 게시글

> 사용자는 전체 피드 또는 특정 교수에 대한 피드를 작성할 수 있다. <br>
> 사용자는 다른 사람이 작성한 피드를 제한없이 확인하고 조회할 수 있다. <br>
> 사용자가 작성한 글이 좋아요를 5개 이상 받는 경우, 인기글로 분류된다. <br>
> 사용자는 본인이 작성한 게시글을 수정하거나 삭제할 수 있다. <br>
> 사용자가 특정 게시글을 조회할 경우, 조회수가 증가한다. <br>
> 단, 사용자가 5분 이내 특정 게시글을 재조회 시 조회수는 증가하지 않는다. <br>
> 동시에 여러 사용자가 게시글을 조회할 경우, 동시성이 보장된다. <br>
> 사용자가 탈퇴해도 게시글은 보존된다. <br>

### 교수

> 사용자는 특정 교수 페이지에 집근하여 교수 기본 정보를 확인할 수 있다. <br>
> 사용자는 교수 이름을 통해 교수를 검색할 수 있다. <br>


### 좋아요

> 사용자는 특정 게시글, 댓글에 대해 좋아요를 등록할 수 있다. <br>
> 사용자는 기존에 등록한 좋아요를 취소할 수 있다. <br>
> 동시에 여러 사용자가 특정 게시글 좋아요를 누를 경우, 동시성이 보장된다. <br>

### 댓글

> 사용자는 특정 게시글에 댓글을 작성할 수 있다. <br>
> 사용자는 다른 사용자가 작성한 댓글을 제한없이 조회할 수 있다. <br>
> 사용자는 자신이 작성한 댓글을 수정하거나 삭제할 수 있다. <br>
> 사용자가 탈퇴해도 댓글은 보존된다. <br>
> 댓글이 삭제될 경우, 하위 대댓글 또한 삭제된다. <br>
> 대댓글이 삭제될 경우, 상위 댓글은 삭제되지 않는다. <br>

### 평점

> 사용자는 특정 교수에 대한 평점을 등록할 수 있다. <br>
> 사용자는 기존 등록한 평점을 수정할 수 있다. <br>
> 사용자는 특정 교수에 대한 평균 평점을 조회할 수 있다. <br>
> 사용자는 본인이 해당 교수에 대해 평점을 등록했는지 여부를 확인할 수 있다.<br>

### 검색

> 사용자는 특정 교수 이름으로 교수를 검색할 수 있다. <br>
> 사용자는 특정 학과 이름으로 학과를 검색할 수 있다. <br>

<br>

## ⚙️ 프로젝트 세팅

> 1. 자바 버전 : 17
> 2. 스프링부트 버전 : 3.1.4
> 3. 빌드 & 빌드 도구 : Gradle
> 4. Git 브랜치 전략 : Feature Branch → Develop Branch → Main Branch(배포)

<br>

## 🛠️ 기술 스택
#### Framework
![springboot](https://img.shields.io/badge/springboot-6DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)
![springsecurity](https://img.shields.io/badge/springsecurity-6DB33F.svg?style=for-the-badge&logo=springsecurity&logoColor=white)

#### DB
![mysql](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![redis](https://img.shields.io/badge/redis-DC382D.svg?style=for-the-badge&logo=redis&logoColor=white)

#### Library
![JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![queryDSL](https://img.shields.io/badge/querydsl-0078D4.svg?style=for-the-badge&logo=polkadot&logoColor=white)
![Lombok](https://img.shields.io/badge/lombok-E50005.svg?style=for-the-badge&logo=lospec&logoColor=white)
![jwt](https://img.shields.io/badge/jwt-000000.svg?style=for-the-badge&logo=jsonwebtokens&logoColor=white)

#### Communication
![notion](https://img.shields.io/badge/notion-000000.svg?style=for-the-badge&logo=notion&logoColor=white)
![discord](https://img.shields.io/badge/discord-5865F2.svg?style=for-the-badge&logo=discord&logoColor=white)
![swagger](https://img.shields.io/badge/swagger-85EA2D.svg?style=for-the-badge&logo=swagger&logoColor=white)

#### Server
![ec2](https://img.shields.io/badge/AWS_EC2-FF9900.svg?style=for-the-badge&logo=amazonec2&logoColor=white)
![rds](https://img.shields.io/badge/AWS_rds-527FFF.svg?style=for-the-badge&logo=amazonrds&logoColor=white)
![s3](https://img.shields.io/badge/AWS_S3-569A31.svg?style=for-the-badge&logo=amazons3&logoColor=white)
![route53](https://img.shields.io/badge/AWS_route53-8C4FFF.svg?style=for-the-badge&logo=amazonroute53&logoColor=white)
![loadBalancer](https://img.shields.io/badge/AWS_LB-8C4FFF.svg?style=for-the-badge&logo=awsorganizations&logoColor=white)
![codeDeploy](https://img.shields.io/badge/AWS_codeDeploy-41AD48.svg?style=for-the-badge&logo=codeblocks&logoColor=white)
![elasticcache](https://img.shields.io/badge/AWS_elasticcache-4053D6.svg?style=for-the-badge&logo=amazonaws&logoColor=white)
![Jmeter](https://img.shields.io/badge/Apache_JMeter-D22128.svg?style=for-the-badge&logo=apachejmeter&logoColor=white)

<br>

## 🧑‍🤝‍🧑 조원 & 역할

| 이름  | 역할                           |
|----- |--------------------------------|
| 정혜민  | 회원 도메인 개발, 게시글 도메인 개발, 댓글 도메인 개발, 교수 도메인 개발, 평점 도메인 개발, 서버와 DevOps 설정관리, 디스코드 웹훅, 피그마 와이어프레임 초안 작성, 서비스 기획|
| 김지훈  | 검색 도메인 개발, 게시글 도메인 개발, 댓글 도메인 개발, 좋아요 도메인 개발, 교수 도메인 개발, 카테고리 도메인 개발, 서비스 기획|

<br>

## 📐 ERD 설계도

<img width="984" alt="image" src="https://github.com/GASIP-PROJECT/gasip-backend/assets/114489245/11471c0a-56c1-4ea5-a3e2-70c2aa8c0ba8">

