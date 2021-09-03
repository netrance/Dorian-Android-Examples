# 시작하며...

이 샘플 프로젝트는 폰트를 가져오고 이를 텍스트 뷰에 적용하는 2번째 예를 보여 드립니다. 
1번째 예와 달리 리소스 파일을 정의하고 이를 통해 XML 파일에서 폰트를 설정할 수 있습니다.

---

# 스크린샷

MainActivity에는 텍스트 뷰가 있고, 그것에 나눔 고딕 폰트가 적용되어 있습니다. 

<img src="./screenshot-01.png" width="480" height="720">

---

# 폰트 설정 방법

1. res 폴더 아래 font 폴더 생성

2. font 폴더에 폰트 파일 복사 (예: font_nanum_gothic.ttf)

3. font 폴더에 폰트 패밀리 파일 생성 (예: nanum_gothic.xml)

4. 폰트 패밀리 파일 작성

```
<font
    android:fontStyle="normal"
    android:fontWeight="400"
    android:font="@font/font_nanum_gothic" />
```

  * fontWeight 값을 조절하여 글씨를 더 굵거나 가늘게 설정할 수 있습니다. 기본값은 400입니다.
  * 2에서 복사한 파일 이름을 android:font 속성의 값으로 설정하세요. (확장자 제외)

5. 폰트를 적용할 텍스트 뷰의 android:fontFamily 속성에 3에서 생성, 작성한 파일 이름으로 지정하세요. (확장자 제외)

```
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:fontFamily="@font/nanum_gothic"
    android:text="Hello World!"
    android:textSize="28sp"
    android:textStyle="bold" />

```
(from layout_toolbar.xml)

---

# 레퍼런스

* From developer.android.com
  * [글꼴 리소스](https://developer.android.com/guide/topics/resources/font-resource?hl=ko)
* [[안드로이드] TextView, EditText 폰트 변경 하기(font-family, fontStyle)](https://everyshare.tistory.com/8)
