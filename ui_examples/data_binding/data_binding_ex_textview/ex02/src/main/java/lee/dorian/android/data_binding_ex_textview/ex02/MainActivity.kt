package lee.dorian.android.data_binding_ex_textview.ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import lee.dorian.android.data_binding_ex_textview.ex02.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var disposableTimer: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.hour = 0
        binding.minute = 0
        binding.second = 0

        initTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        deinitTimer()
    }

    fun initTimer() {
        disposableTimer = Observable.interval(0, 1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())
            .subscribe { count ->
                binding.hour = count / Constants.SECONDS_PER_HOUR
                binding.minute = (count / Constants.SECONDS_PER_MINUTE) % Constants.MINUTES_PER_HOUR
                binding.second = count % Constants.SECONDS_PER_MINUTE
            }
    }

    fun deinitTimer() {
        disposableTimer.dispose()
    }

}