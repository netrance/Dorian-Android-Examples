# 시작하며...

이 샘플 모듈의 개발 목적은 다음과 같습니다.

* TextView의 텍스트에 가로 방향 그라데이션 색상을 적용하는 방법을 이해한다.

---

# 스크린샷

이 샘플 앱은 단순히 가로 방향 그라데이션 색상이 적용된 텍스트를 아래 스크린샷과 같이 보여 줍니다.

<img src="https://steemitimages.com/480x0/https://cdn.steemitimages.com/DQmRHvNa8Fd2Aq8pVa2Dxp8FfXwiJA2Mi2NoDPs5fVTCRAt/image.png" width="480" height="720">

---

# 그라데이션 색상을 정의하는 확장 메소드 정의

텍스트에 가로 방향 그레데이션 색상을 정의하는 확장 메소드를 아래와 같이 정의하였습니다.

```
fun TextView.setTextColorAsLinearGradient(colors: Array<Int>) {
    if (colors.isEmpty()) {
        return
    }

    setTextColor(colors[0])
    this.paint.shader = LinearGradient(
        0f,
        0f,
        paint.measureText(this.text.toString()),
        this.textSize,
        colors.toIntArray(),
        arrayOf(0f, 1f).toFloatArray(),
        Shader.TileMode.CLAMP
    )
}
```

---

# 텍스트에 그라데이션 색상 적용

아래 코드를 실행하면 왼쪽에서는 빨간색, 오른쪽에서는 파란색인 그라데이션 색상이 TextView 객체에 적용됩니다.

```
// 가정 #1: 이 코드는 액티비티 안에서 실행된다.
// 가정 #2: tvExample의 데이터 타입은 TextView이다.
tvExample.setTextColorAsLinearGradient(arrayOf(
    Color.parseColor("#FF0000"),
    Color.parseColor("#0000FF")
))
```

---

# 여담

**2022년 7월 15일 (금)**<br/>
이번 프로젝트에서 시작 화면 중앙에 앱 이름을 배치하고, 
거기에 그라데이션 색상을 적용해 달라는 요청을 
디자인팀으로부터 받았습니다. 
이미지가 나을지, 텍스트가 나을지 
고민 끝에 텍스트가 낫겠다고 결정했습니다. 
왜냐면 추후 색상 변경시 이미지 변경보다 텍스트 색을 변경하기 더 쉽기 때문입니다. 
이미지로 결정하면, 나중에 이를 바꿔 달라고 디자인팀에 요청해야 합니다. 
텍스트로 결정하면, 나중에 색만 변경하면 됩니다.

---

# 레퍼런스

이번 샘플 코드를 만들기 위해 아래 레퍼런스를 참고했습니다.

* [How to make textview gradient color in Android](https://captaindroid.com/how-to-make-textview-gradient-color-in-android/)