# 시작하며...

이 샘플 프로젝트는 [room_ex03](../room_ex03/readme.md) 프로젝트를 베이스로 Hilt 라이브러리를 활용하여 
의존성 주입(dependency injection)을 구현하였습니다.

---

# Hilt 적용 후 좋아진 점

* 중복될 수 있는 객체 초기화 코드를 Module 클래스들로 모을 수 있음
* 클래스의 복잡한 초기화 코드를 보지 않아도 되므로 클래스 파악하기가 수월해짐
* 싱글톤 객체로 사용할 클래스를 정의하기가 수월해짐 (getInstance 같은 메소드를 정의할 필요 없음)

---

# Hilt 적용 후 안 좋아진 점

* 클래스 개수가 늘어나서 앱 구조가 복잡하게 보일 수 있음
* 객체 초기화 코드를 보려면, 약간의 검색 필요 (초기화 코드는 어느 모듈 클래스에 있는지 찾아야...)
* Hilt를 모르는 개발자는 관련 선행 학습이 필요함

---

# 다음 과제

현재 프로젝트를 베이스로 단위 테스트, UI 테스트 코드를 만들어 보자.

---

# 레퍼런스

* From developer.android.com
  * Dependency injection with Hilt
    * [English](https://developer.android.com/training/dependency-injection/hilt-android)
    * [한국어](https://developer.android.com/training/dependency-injection/hilt-android?hl=ko)
