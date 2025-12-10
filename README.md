# 🤔 Agree - 정말 동의해?

<img width="1788" height="1006" alt="image" src="https://github.com/user-attachments/assets/d89748f9-cfc2-440d-8709-f1b9e44b7e30" />

>Agree는 뉴스 기반 토론 플랫폼으로, 사용자들이 사회적 이슈에 대해 찬성/반대 의견을 제시하고 토론할 수 있는 서비스입니다. 복잡한 사회 문제를 간단한 투표와 토론을 통해 다양한 관점을 공유하고 이해할 수 있도록 돕습니다.


<br /> <br /><br /> <br />

## 🛠 Project Duration
**2025.11.22 -23**

<br /> <br /><br /> <br />


## 👥 Backend Team
- subin930 <a href="https://github.com/subin930" target="_blank">(@subin930)</a>
- HarryBae1011 <a href="https://github.com/HarryBae1011" target="_blank">(@HarryBae1011)</a>
- hyukkimm <a href="https://github.com/hyukkimm" target="_blank">(@hyukkimm)</a>


<br /> <br /><br /> <br />


## 🚀 Getting Started

### Requirements
- [JDK 17, Mysql]

### Backend
```bash
# Clone the repo
git clone https://github.com/Ne-o-rdinary-TeamN/Backend.git
cd Backend

# Check JDK version - requires JDK 17 or higher.
java -version

# Check build tool - This project uses Gradle. Check if Gradle is installed
gradle -v

# Configure environment
# Use application-example.yml.sample as a reference.
# Create a new file application.yml and configure your local settings (DB, ports, etc.).

# Run the development server
```

<br /> <br /><br /> <br />

## 🌟 Main features
<ul>
  <li><strong>🔐 사용자 인증</strong>: 로그인/회원가입 기능 </li>
  <li><strong>💡 뉴스 기사 추천</strong>: 실시간 인기, 토론 주제와 관련된 뉴스 기사 추천</li>
  <li><strong>📰 뉴스 기반 토론</strong>: 뉴스 링크를 기반으로 한 토론 주제 생성</li>
  <li><strong>🗳️ 투표 시스템</strong>: 찬성/반대 투표 및 실시간 결과 확인</li>
  <li><strong>🗨️ 댓글 시스템</strong>: 토론 참여 댓글 작성</li>
</ul>


<br /> <br /><br /> <br />
## 📡 Infrastructure Diagram
<img width="1201" height="1125" alt="N팀_인프라구성도" src="https://github.com/user-attachments/assets/565b5137-a3c1-46c0-9843-d1c4ecc3bd72" />

<br /> <br /><br /> <br />
## 🗂️ ERD
<img width="1470" height="632" alt="N팀_ERD" src="https://github.com/user-attachments/assets/2509bc2c-7dd9-4ffd-9d6e-41a91804718b" />

<br /> <br /><br /> <br />
## 📄 Project Documentation(PDF)
👉 [프로젝트 설명서 다운로드](./docs/AGREE_참고자료.pdf)

<br /> <br /><br /> <br />
## 🌳 Git branch 전략 및 PR 규칙
```bash
├─main
│  └─hotfix/긴급패치#1
├─develop
│  └─feature/기능#1 #(각자의 로컬에서 develop 브랜치에서 파생)
│  └─feature/기능#2 #(각자의 로컬에서 develop 브랜치에서 파생)
``` 

- `main` : 배포용 브랜치
- `develop` : 개발용 통합 브랜치
- `feature/기능명` : 개별 기능 개발용 브랜치
- `hotfix/패치명` : 긴급 버그 수정용 브랜치

1. 모든 기능 개발은 `develop` 브랜치에서 `feature/기능명` 브랜치를 생성해 작업합니다.
2. 작업 완료 후, `develop` 브랜치로 Pull Request를 생성합니다.
3. 긴급 버그 수정은 `main` 브랜치에서 `hotfix/패치명` 브랜치를 생성하여 수정 후 `main` 과 `develop` 브랜치에 모두 merge합니다.
4. 팀원중 1명 이상의 approve를 받으면, pr을 머지하고 해당 pr을 생성한 브랜치는 삭제합니다.

<br /> <br /><br /> <br />
## 🔖 Commit 컨벤션
모든 커밋 메세지는 아래와 같은 규칙을 따릅니다:

|  Message   | 설명             |
|:----------:|:---------------|
|   [feat]   | 새로운 기능 추가      |
|   [fix]    | 버그 수정          |
| [refactor] | 리팩토링(기능 변화 없음) |
|   [docs]   | 문서 수정          |
| [comment]  | 주석 추가 및 변경     |
|   [test]   | 테스트 코드 추가      |
|  [rename]  | 파일 혹은 폴더명 수정   |
|  [remove]  | 파일 혹은 폴더 삭제    |
|  [chore]   | 기타 변경사항        |
