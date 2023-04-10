# 시작하며...

이 샘플 프로젝트는 [room_ex01](../room_ex01/readme.md) 프로젝트의 문제점을 해결하기 위해 만들었습니다. 
문제점은 다음과 같습니다.

* 액티비티 클래스들에 여러 종류의 관심사들이 섞여 있음

---

# 해결 방법

* 뷰모델 클래스를 정의하고, UI 외 로직은 그 곳으로 이동

---

# 좋아진 점

* 관심사가 액티비티, 뷰모델로 분리되어 보다 나아진 가독성 

---

# 여전한 문제점

* 뷰모델에는 여전히 관심사들이 혼재함: 비즈니스 로직과 데이터 읽기/쓰기

---

# 개선책

* room_ex3 모듈에서는 안드로이드 개발자 문서에서 제안하는 아키텍처를 적용하여 뷰모델에 혼재하는 두 관심사를 분리할 예정

---

# 레퍼런스

* From developer.android.com
  * Save data in a local database using Room
    * [English](https://developer.android.com/training/data-storage/room)
    * [한국어](https://developer.android.com/training/data-storage/room?hl=ko)
  * ViewModel overview
    * [English](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [한국어](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ko)