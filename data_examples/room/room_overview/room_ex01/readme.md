# 시작하며...

이 샘플 프로젝트에는 Room 라이브러리로 데이터베이스를 활용하는 예를 담았습니다.

---

# 스크린샷, 멤버 추가/조회/수정/삭제

기존 SQLite 샘플과 동일하여 
[이 링크](https://github.com/netrance/Dorian-Android-Examples/blob/master/data_examples/sqlite/sqlite_overview/app/readme.md)로 
대신합니다.

---

# 문제점

이 샘플 모듈에서 Main1Activity 클래스는 관심사 분리가 적용되지 않습니다. 
이로 인해 여러 종류의 로직들이 뒤섞여 있죠. 
이를 어떻게 분리해야 할까요?

---

# 개선책

room_ex2 모듈에서는 뷰모델 클래스를 정의하여 Main1Activity 클래스의 일부 관심사를 그리로 옮길 예정입니다. 

---

# 레퍼런스

* From developer.android.com
  * Save data in a local database using Room
    * [English](https://developer.android.com/training/data-storage/room)
    * [한국어](https://developer.android.com/training/data-storage/room?hl=ko)
