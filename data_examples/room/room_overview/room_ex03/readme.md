# 시작하며...

이 샘플 프로젝트는 [room_ex02](../room_ex02/readme.md) 프로젝트의 문제점을 해결하기 위해 만들었습니다. 
문제점은 다음과 같습니다.

* 뷰모델 클래스들의 관심사들이 여전히 혼재함: 비즈니스 로직과 데이터 읽기/쓰기

---

# 해결 방법

* 안드로이드 개발자 사이트에서 제시하는 아키텍처 활용
  * Repository 패턴 활용: 데이터베이스 관련 코드는 이 곳으로 이동

---

# 아키텍처 적용 후 좋아진 점

* 뷰모델의 관심사는 비즈니스 로직만 남길 수 있어 보다 나아진 가독성
* 각 뷰모델에 구현되었던 데이터베이스 입출력 코드는 MemberRepository 클래스로 통합

---

# 아키텍처 적용 후 안 좋아진 점

* 클래스 개수가 늘어나서 앱 구조가 복잡하게 보일 수 있음
* insert/update/delete/select를 하기 위해 뷰모델 클래스에도, MemberRepository 클래스에도 각각 메소드를 정의해야 함
* 만약 Repository 패턴에 DataSource 패턴까지 적용하면, 구현해야 할 메소드는 더 늘어남

---

# 그럼에도 아키텍처가 필요한 이유는?

* 클래스들의 관심사가 명확히 분리 되어 본연의 역할에만 충실할 수 있음
  * Main3ViewModel 클래스의 search 메소드
    * 이름/점수로 Member 조회를 MemberRepository에 요청 (DB 객체를 알 필요 없음)
    * 조회 결과를 _flowMemberList 플로우에 emit
* 분리된 관심사별로 단위 테스트 가능 -> 부분 기능의 조기 검증 가능

---

# 여전한 문제점

* 현재 구현한 뷰모델 클래스들은 Application 객체를 참조하고 있으며, 안드로이드에서는 가급적 이를 지향할 것을 권한다.
  * 그러나 MemberRepository 클래스에서는 Room을 활용하기 위해 Application 객체 필요
* 여러 클래스들에 각각 (중복) 정의된 객체 초기화 또는 길어지는 초기화 코드는 코드의 복잡도를 높인다.

---

# 개선책

room_ex4 모듈에서는
 
* Hilt를 활용한 의존성 주입을 적용하여 객체 초기화 코드들도 분리할 예정
  * MemberRepository 객체 생성시 Application 객체 주입 예정
  * 뷰모델 객체도 액티비티 객체에 주입 예정

---

# 레퍼런스

* From developer.android.com
  * Save data in a local database using Room
    * [English](https://developer.android.com/training/data-storage/room)
    * [한국어](https://developer.android.com/training/data-storage/room?hl=ko)
  * ViewModel overview
    * [English](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [한국어](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ko)
  * Android architecture guide
    * [English](https://developer.android.com/topic/architecture)
    * [한국어](https://developer.android.com/topic/architecture?hl=ko)