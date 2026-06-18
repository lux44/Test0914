# SmartAppLab

안드로이드 핵심 개념을 하나의 프로젝트에 통합한 실습용 앱입니다.  
ViewBinding, Navigation Component, Fragment, Intent, Service, BroadcastReceiver, 내부 저장소 파일 처리, Dialog, Canvas 그래픽 변환을 모두 포함합니다.

---

## 1. 프로젝트 목적

이 프로젝트는 안드로이드 수업에서 배운 주요 개념들을 하나의 앱 안에서 연결해 보는 실습용 프로젝트입니다.

- Fragment 기반 화면 구성
- BottomNavigationView + Navigation Component
- ViewBinding 사용
- 명시적 / 암시적 Intent
- 결과 반환형 Activity
- DatePickerDialog / TimePickerDialog
- AlertDialog
- 내부 저장소 파일 저장 / 읽기
- Service
- BroadcastReceiver
- Canvas / Bitmap 이미지 처리

---

## 2. 화면 구성

### HomeFragment
- 서비스 시작 / 중지
- DiaryFragment로 이동
- GraphicsFragment로 이동

### DiaryFragment
- 날짜 선택
- 시간 선택
- 커스텀 Dialog로 일기 입력
- 내부 저장소에 파일 저장
- 저장된 일기 불러오기

### WidgetFragment
- CalendarView
- TimePicker
- Chronometer
- 위젯 실습용 화면

### IntentFragment
- 명시적 Intent로 SecondActivity 열기
- 암시적 Intent로 웹 브라우저 열기
- 전화 앱 열기
- 이미지 선택하기

### GraphicsFragment
- 이미지 불러오기
- Canvas 변환
  - 확대 / 축소
  - 회전
  - 이동
  - 기울이기
  - 회색 효과
  - 밝기 조절

### SecondActivity
- 메시지 전달받기
- 결과 반환하기

---

## 3. 사용한 주요 개념

### 3-1. Fragment
- 액티비티 안에서 화면 단위로 사용
- Navigation Component로 이동 관리

### 3-2. ViewBinding
- `findViewById()` 대신 binding 사용
- XML과 코드 연결을 쉽게 처리

### 3-3. Navigation Component
- `nav_graph.xml`로 Fragment 목적지 관리
- `BottomNavigationView`와 연결

### 3-4. Intent
- 명시적 Intent: 다른 Activity 열기
- 암시적 Intent: 웹, 전화 등 외부 앱 실행

### 3-5. File I/O
- `filesDir`를 이용한 내부 저장소 사용
- 날짜별 텍스트 파일 저장 / 읽기

### 3-6. Dialog
- `DatePickerDialog`
- `TimePickerDialog`
- `AlertDialog`
- 커스텀 레이아웃 다이얼로그 사용

### 3-7. Service
- 화면 없이 백그라운드 기능 실행

### 3-8. BroadcastReceiver
- 시스템 이벤트 수신 실습

### 3-9. Canvas / Bitmap
- 이미지 변형 및 색상 처리

---

## 4. 파일 구조

```text
com.example.smartapplab
├── MainActivity.kt
├── SecondActivity.kt
├── service/
│   └── LogService.kt
├── receiver/
│   └── BatteryLowReceiver.kt
├── data/
│   └── DiaryRepository.kt
├── ui/
│   ├── home/HomeFragment.kt
│   ├── diary/DiaryFragment.kt
│   ├── widget/WidgetFragment.kt
│   ├── intent/IntentFragment.kt
│   └── graphics/GraphicsFragment.kt
├── view/
│   └── GraphicView.kt
├── res/
│   ├── layout/
│   ├── menu/
│   ├── navigation/
│   └── drawable/
