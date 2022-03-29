# Dungeon&Defence

## 발표 링크
- 1. [1차 발표]()
- 2. [2차 발표]()
- 3. [최종 발표]()

## 목차
- 1. 게임의 소개 
	- 1.1 High Concept
	- 1.2 핵심 메카닉
- 2. 개발 범위
- 3. 각 Scene 별 항목
	- <span style="color:red">3.1 메인 화면</span>
	- 3.2 플레이 화면
		- 3.2.1 노말 스테이지
		- <span style="color:red">3.2.2 보스 스테이지</span>
	- <span style="color:green">3.3 메뉴 화면</span>
- 4. 개발 범위
- 5. 필요한 기술
	- 5.1 맵 타일링 및 스크롤링
	- 5.2 플레이어 및 적 캐릭터 이동/공격/피격 이미지 출력
	- 5.3 플레이어 및 적 캐릭터 공격/피격/죽음 판정
	- 5.4 적 캐릭터 AI 구현
	- <span style="color:green">5.5 메뉴화면 출력</span>
	- <span style="color:red">5.6 게임 데이터 저장</span>
- 6. 추가 구현 사항
  
  
  
  
## 1. 게임의 소개
제목: Dungeon&Defence(던전 앤 디펜스) 
  
게임의 장르: 타워 디펜스 
![동일한 타워 디펜스 장르 게임]()  
![동일한 타워 디펜스 장르 게임2]()  
게임의 개요: 자신이 배치한 타워형의 오브젝트로 일정한 주기/라운드로 걸어오는 적들을 막아내는 게임  

  
### 1.1 High Concept  
**방치**

타워 디펜스 장르 중에서도 바쁘게 컨트롤을 해야 하는 경우도 있지만, 본 프로젝트에서는 '방치'에 초점을 두었다.
  
### 1.2 핵심 메카닉  
1. 바쁜 조작의 필요 없이, 간단하게 배치하기만 해도 진행되는 게임
2. 낮은 집중도를 요구하여, 게임을 진행하며 낮은 스트레스와 꾸준한 재미를 느낄 수 있다  
  
  
  
  
## 2. 개발 범위

### 2.1 게임 화면 구성
메인, 모드/라운드 선택, 게임 플레이, 일시정지, 캐릭터 정보, 장비 정보, 장비 조합, 장비 뽑기

### 2.2 주요 화면 기능 및 객체 구성

#### 1. 메인 화면
![메인 화면]()
게임 타이틀 이미지, 게임 시작 버튼, 캐릭터 정보 버튼, 장비 관리 버튼, 게임 종료 버튼

#### 2. 게임 플레이 화면(2분할)
![플레이 화면]()
화면 상단: 백그라운드 맵, 몬스터 객체, 플레이어 유닛 객체
화면 하단(좌우 스크롤): 캐릭터 리스트, 일시정지 버튼

#### 3. 캐릭터/장비 정보 화면(2분할)
![캐릭터 정보 화면]()
화면 상단: 캐릭터/장비 정보 이미지 및 텍스트
화면 하단(좌우 스크롤): 캐릭터/장비 리스트, 돌아가기 버튼

#### 4. 장비 조합 화면(2분할)
![장비 조합 화면]()
화면 상단: 조합할 장비/조합 결과 장비 이미지 및 텍스트
화면 하단(좌우 스크롤): 장비 리스트, 돌아가기 버튼

#### 5. 장비 뽑기 화면(2분할)
![장비 뽑기 화면]()
화면 상단: 뽑기 설명 텍스트 및 이미지, 뽑기 과정 및 결과 이미지
화면 하단(좌우 스크롤): 장비 리스트, 돌아가기 버튼


## 3. 예상 게임 실행 흐름
![DND 플로우차트]()  
  
  
  

## 4. 개발 일정  
|주차|개발 내용|
|---|-----|
|1주차|리소스 작업|  

made by. 2016180022 박찬얼