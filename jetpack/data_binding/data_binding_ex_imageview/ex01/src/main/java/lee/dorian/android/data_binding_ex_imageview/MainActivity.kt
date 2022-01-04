package lee.dorian.android.data_binding_ex_imageview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import lee.dorian.android.data_binding_ex_imageview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 데이터 바인딩: 바인딩 객체의 변수 설정만으로도 이미지 설정이 처리된다.
        binding.imageResID = R.mipmap.ic_launcher
        binding.imageURL = "https://cdn.steemitimages.com/DQmQYdEvcXw9GU5N6UPrqR3vfWMcgefPzmM1UpeqVam7DcH/IMAGE%202021-04-25%2009:31:43.png"
    }

}