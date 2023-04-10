# 시작하며...

이 모듈에는 Room을 활용한 데이터베이스 그리고 기타 공용 코드를 담았습니다. 
다른 샘플 모듈들이 이를 활용할 예정입니다.

---

# 데이터베이스 관련 클래스들

* AppDatabase
  * 데이터베이스 담당
  * 구현 조건
    * RoomDatabase 클래스 상속
    * @Database 애노테이션 추가
      * entities: 엔티티 클래스들을 추가해야 함 (이 예에서는 Member 클래스)
      * version: 데이터베이스 버전
* MemberDao
  * Member 테이블의 자료를 조회, 추가, 갱신, 삭제
  * AppDatabase 구현 객체를 통해 접근 가능
* Member
  * Member 엔티티 정의
  * 엔티티 클래스 정의하면, Member 테이블이 데이터베이스 내에서 생성

---

# UI 관련 클래스들

* MemberListAdapter
  * 멤버 리스트를 보여 줄 RecyclerView 뷰와 연결할 어댑터
* MemberListViewHolder
  * 멤버 리스트 항목의 뷰들을 담는 클래스
* OnMemberItemLongClickListener
  * 멤버 리스트 항목을 길게 터치할 때 작동하는 리스너

---

# 코루틴 관련 확장 함수들

* StateFlowExt.kt 파일
  * StateFlow 또는 하위 클래스/인터페이스의 확장 함수 정의

---

# 레퍼런스

* From developer.android.com
  * Save data in a local database using Room
    * [English](https://developer.android.com/training/data-storage/room)
    * [한국어](https://developer.android.com/training/data-storage/room?hl=ko)
