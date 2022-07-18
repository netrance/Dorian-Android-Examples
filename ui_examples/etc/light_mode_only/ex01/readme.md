# 시작하며...

이 샘플 모듈의 개발 목적은 다음과 같습니다.

* 앱이 다크 모드를 막고 라이트 모드만 허용하는 방법을 이해한다.

---

# 스크린샷

UI는 수정하지 않았으므로 스크린샷은 따로 첨부하지 않습니다. 
차이점은 안드로이드 기기에서 디스플레이 모드를 다크로 바꾸어도 
앱 화면은 바뀌지 않는다는 점입니다.

---

# 다크 모드 막고 라이트 모드만 허용 방법

(1) Application 클래스를 상속 받는 하위 클래스를 정의하세요.

```
class CustomApplication : Application() {
}
```

(2) 위 클래스의 onCreate 메소드에 나이트 모드를 막는 코드를 작성하세요.

```
class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
```

(3) AndroidManifest.xml 파일의 application 태그에서 name 속성을 위에 정의한 클래스로 선택하세요.

```
<application
    ...
    android:name=".CustomApplication"
    ...>

    ...

</application>
```

---

# 여담

**2022년 7월 17일 (일)**<br/>
이번 프로젝트의 디자인에서도 다크 모드는 고려되지 않았습니다. 
일정상 그거까지 반영하기가 사실상 불가니까요. 
그래서 이번 앱에서도 다크 모드는 막기로 했습니다.

---

# 레퍼런스

이번 샘플 코드를 만들기 위해 아래 레퍼런스들을 참고했습니다.

* [How to disable night mode in my application even if night mode is enable in android 9.0 (pie)? - StackOverflow](https://stackoverflow.com/questions/57175226/how-to-disable-night-mode-in-my-application-even-if-night-mode-is-enable-in-andr)
