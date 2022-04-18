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
	- 2.1 게임 화면 구성
	- 2.2 주요 화면 기능 및 객체 구성
- 3. 예상 게임 실행 흐름
- 4. 개발 일정
- 5. 추가 개발 사항
  
  
  
## 1. 게임의 소개
제목: Dungeon&Defence(던전 앤 디펜스) 
  
게임의 장르: 타워 디펜스 

![동일한 타워 디펜스 장르 게임](https://github.com/2016180022/SGP/blob/master/tdgame1.jpg?raw=true)  
[킹덤 러쉬: 인게임 이미지]

![동일한 타워 디펜스 장르 게임2](https://github.com/2016180022/SGP/blob/master/tdgame2.jpg?raw=true)  
[명일방주: 인게임 이미지]

게임의 개요: 자신이 배치한 타워형의 오브젝트로 일정한 주기/라운드로 걸어오는 적들을 막아내는 게임  

  
### 1.1 High Concept  
**'방치'**

타워 디펜스 장르 중에서도 바쁘게 컨트롤을 해야 하는 경우도 있지만, 본 프로젝트에서는 '방치'에 초점을 두었다.
  
### 1.2 핵심 메카닉  
1. 바쁜 조작의 필요 없이, 간단하게 배치하기만 해도 진행되는 게임
2. 낮은 집중도를 요구하여, 게임을 진행하며 낮은 스트레스와 꾸준한 재미를 느낄 수 있다  
  
  
  
  
## 2. 개발 범위

### 2.1 게임 화면 구성
메인, 모드/라운드 선택, 게임 플레이, 일시정지, 캐릭터 정보, 장비 정보, 장비 조합, 장비 뽑기

### 2.2 주요 화면 기능 및 객체 구성

#### 1. 메인 화면
![메인 화면](https://github.com/2016180022/SGP/blob/master/dMain.png?raw=true)  
게임 타이틀 이미지, 게임 시작 버튼, 캐릭터 정보 버튼, 장비 관리 버튼, 게임 종료 버튼

#### 2. 게임 플레이 화면(2분할)
![플레이 화면](https://github.com/2016180022/SGP/blob/master/dPlay.png?raw=true)  
화면 상단: 백그라운드 맵, 몬스터 객체, 플레이어 유닛 객체  
화면 하단(좌우 스크롤): 캐릭터 리스트, 일시정지 버튼

#### 3. 캐릭터/장비 정보 화면(2분할)
![캐릭터 정보 화면](https://github.com/2016180022/SGP/blob/master/dCharInfo.png?raw=true)  
화면 상단: 캐릭터/장비 정보 이미지 및 텍스트  
화면 하단(좌우 스크롤): 캐릭터/장비 리스트, 돌아가기 버튼

#### 4. 장비 조합 화면(2분할)
![장비 조합 화면](https://github.com/2016180022/SGP/blob/master/dEquipSynth.png?raw=true)  
화면 상단: 조합할 장비/조합 결과 장비 이미지 및 텍스트  
화면 하단(좌우 스크롤): 장비 리스트, 돌아가기 버튼

#### 5. 장비 뽑기 화면(2분할)
![장비 뽑기 화면](https://github.com/2016180022/SGP/blob/master/dEquipGacha.png?raw=true)  
화면 상단: 뽑기 설명 텍스트 및 이미지, 뽑기 과정 및 결과 이미지  
화면 하단(좌우 스크롤): 장비 리스트, 돌아가기 버튼
* 수정: 뽑기 기능 추가 기능으로 이관, 별도의 뷰 사용 예정


## 3. 예상 게임 실행 흐름
![DND 플로우차트](https://github.com/2016180022/SGP/blob/master/dndFlowchart.png?raw=true)  
- **메인화면을 제외한 모든 씬은 상/하단이 나뉘어져 있는 구조** - 모두 동일한 액티비티를 사용, 메인 화면만 다른 뷰 이용.  
  
  

## 4. 개발 일정  
|주차|개발 내용|
|---|-----|
|1주차|리소스 작업|
|2주차|프레임워크 제작(메인, 게임 플레이, 일시정지)|
|3주차|프레임워크 제작(캐릭터 및 장비 정보, 장비 조합)|
|4주차|게임 플레이 구현|
|5주차|게임 플레이 구현|
|6주차|게임 플레이 구현|
|7주차|캐릭터 별 스킬 및 착용 장비 스탯에 적용|
|8주차|장비 조합 및 뽑기 구현|
|9주차|디버깅 및 테스트|

* 개발 일정 수정사항
	(22.04.18) 캐릭터 뽑기 추가 구현 사항으로 이전

## 5. 추가 개발 사항

### 5.1 도전 모드, 스토리 모드 추가
도전 의식을 자극할 수 있는 높은 난이도의 모드 추가
게임에 몰입을 더해줄 수 있는 스토리 모드 추가

### 5.2 캐릭터의 액티브 스킬 추가
도전 모드와 함께, 방치형 게임이 잘 맞지 않는 사람들에게도 게임의 재미 부여

made by. 2016180022 박찬얼
