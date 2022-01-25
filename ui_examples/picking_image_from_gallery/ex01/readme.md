# 시작하며...

이 샘플 프로젝트는 Galley로부터 이미지를 가져오고 이를 이미지 뷰로 보여주는 예를 보여 드립니다.

---

# 스크린샷

MainActivity에 1개의 텍스트 뷰가 있습니다. 그것을 클릭하면, 앱은 갤러리 화면으로 이동합니다. 이미지 선택 후  
앱은 이전 화면으로 복귀하고, 이를 이미지 뷰로 보여 줍니다.

<img src="./screenshot-01.gif" width="480" height="720">

---

# 갤러리 화면으로 이동하는 방법 그리고 화면 복귀시 이미지를 읽는 방법

1. 갤러리 화면으로부터 복귀할 때 이미지를 읽을 ActivityResultLauncher<Intent> 객체 정의

```
private val galleryActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
) { activityResult ->
    if (activityResult.resultCode != RESULT_OK) {
        return@registerForActivityResult
    }

    activityResult.data?.data.also { imageURI ->
        // data 객체는 선택한 이미지의 URI이며,
        // 이를 이미지 뷰의 imageURI로 설정하면,
        // 선택한 이미지를 보여줄 수 있음
        imageView.setImageURI(imageURI)
    }
}
```

2. 갤러리를 실행하기위한 인텐트 객체 정의

```
val intent = Intent(Intent.ACTION_PICK).apply {
    setType("image/*")
    putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
}
```

3. 1, 2에서 정의한 객체를 활용하여 갤러리 화면 실행

```
galleryActivityResultLauncher.launch(intent)
```

---

# 여담

**2021년 1월 24일 (월)**<br/>
빌드시 발생한 'Installed Build Tools revision 31.0.0 is corrupted.' 오류는 
build.gradle 파일에서 buildToolsVersion을 "30.0.3"으로 변경하니 해결된다. 
이렇게 하는 게 맞는 건지 잘 모르겠지만, 일단 해결된 것을 기뻐하자.

---

# 레퍼런스

* [[Android] Gallery에서 Image 가져오기](https://hello-bryan.tistory.com/65)
* [How to Select an Image from Gallery in Android?](https://geeksforgeeks.org/how-to-select-an-image-from-gallery-in-android)