# 🚗 Vehicle Dispatch System

지도 기반으로 가장 가까운 차량을 탐색하고 배차하는 시스템입니다.  
카카오맵 API를 활용하여 차량 위치를 시각화하고, 거리 계산을 통해 최적의 차량을 선택합니다.

---

## 📌 주요 기능

- 📍 지도 클릭 / 주소 / 좌표 입력으로 위치 선택
- 🚗 전체 차량 위치 마커 표시
- 🎯 가장 가까운 차량 자동 배차
- ⏱ 예상 도착 시간(ETA) 계산
- 🚙 차량 이동 애니메이션 (실시간 이동)

---

## 🛠 기술 스택

- Backend: Spring Boot, JPA, MySQL
- Frontend: HTML, CSS, JavaScript
- API: Kakao Map API

---

## ⚙️ 핵심 로직

- Haversine 공식을 이용한 거리 계산
- 가장 가까운 차량 탐색 (Nearest Vehicle Selection)
- 시간 기반 ETA 계산 및 감소 로직
- 지도 위 차량 이동 애니메이션 구현

---

## 🚀 향후 개선 사항

- 도로 기반 경로 탐색 (Kakao Mobility API 적용)
- 차량 상태(Status) 기반 배차 로직 개선
- 호출 이력 저장 및 관리 기능 추가

---
