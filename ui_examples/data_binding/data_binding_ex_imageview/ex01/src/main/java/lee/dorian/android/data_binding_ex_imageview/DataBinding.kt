package lee.dorian.android.data_binding_ex_imageview

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import java.io.IOException

// 이 메소드를 정의하면, ImageView의 src 속성과 정수형 데이터를 있음.
// 리소스 ID는 정수형이므로 이를 이용하여 내부 이미지 로딩 가능.
@BindingAdapter("android:src")
fun bindingImageView(iv: ImageView?, resID: Int) {
    iv?.setImageResource(resID)
}

// 이 메소드를 정의하면 ImageView의 src 속성과 문자열 데이터를 바인딩할 수 있으며,
// 외부 이미지 URL은 문자열이므로 이를 이용하여 외부 이미지 로딩 가능.
@BindingAdapter("android:src")
fun bindingImageView(iv: ImageView, url: String) {
    Thread({
        try {
            val inputStream1 = java.net.URL(url).openStream();
            val bitmap = BitmapFactory.decodeStream(inputStream1);

            // post 메소드 사용 이유는 이 스레드는 UI 스레드가 아니므로 화면 업데이트 못 하기 때문
            iv.post {
                iv.setImageBitmap(bitmap)
            }
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
    }).start()
}

