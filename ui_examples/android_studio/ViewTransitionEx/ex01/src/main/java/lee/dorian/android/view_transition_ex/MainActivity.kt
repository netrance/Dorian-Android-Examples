package lee.dorian.android.view_transition_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {

    private lateinit var layoutFloating: ViewGroup
    private lateinit var layoutFloatingScene1: Scene
    private lateinit var layoutFloatingScene2: Scene
    private lateinit var layoutFloatingScene3: Scene
    private lateinit var layoutFloatingTransition: Transition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutFloating = findViewById<ViewGroup>(R.id.layoutFloating)
        initLayoutFloatingTransition()
        startLayoutFloatingAnimation()
    }

    private fun initLayoutFloatingTransition() {
        layoutFloatingScene1 = Scene.getSceneForLayout(layoutFloating, R.layout.layout_floating_1, this)
        layoutFloatingScene2 = Scene.getSceneForLayout(layoutFloating, R.layout.layout_floating_2, this)
        layoutFloatingScene3 = Scene.getSceneForLayout(layoutFloating, R.layout.layout_floating_3, this)
        layoutFloatingScene1.enter()
        layoutFloatingTransition = TransitionInflater.from(this).inflateTransition(R.transition.transition_of_layout_floating)
    }

    private fun startLayoutFloatingAnimation() {
        Handler().postDelayed({ TransitionManager.go(layoutFloatingScene2, layoutFloatingTransition)}, 1000)
        Handler().postDelayed({ TransitionManager.go(layoutFloatingScene3, layoutFloatingTransition)}, 4000)
        Handler().postDelayed({ TransitionManager.go(layoutFloatingScene1, layoutFloatingTransition)}, 7000)
    }
}