package lee.dorian.android.room_overview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lee.dorian.android.room_common.AppDatabase
import lee.dorian.android.room_common.Member
import lee.dorian.android.room_common.databinding.ActivityEditMemberBinding

class EditMember1Activity : AppCompatActivity() {
    private val binding by lazy {
        ActivityEditMemberBinding.inflate(layoutInflater)
    }

    private lateinit var member: Member

    private val db by lazy {
        AppDatabase.getInstance(this)
    }

    private val memberDao by lazy {
        db.memberDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init fields.
        member = when {
            (null == savedInstanceState) -> intent.getSerializableExtra(EXTRAKEY_MEMBER) as Member
            else -> savedInstanceState.getSerializable(EXTRAKEY_MEMBER) as Member
        }

        // Init UI.
        setContentView(binding.root)
        binding.etCurrentName.setText(member.name)
        binding.etCurrentPoint.setText(member.point.toString())

        // Set listeners.
        binding.btnEditMember.setOnClickListener(btnEditMemberClickListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(EXTRAKEY_MEMBER, member)
    }

    private val btnEditMemberClickListener = View.OnClickListener {
        lifecycleScope.launch {
            updateMember()
            setResult(RESULT_OK)
            finish()
        }
    }

    private fun updateMember() = lifecycleScope.launch(Dispatchers.IO) {
        val name = binding.etCurrentName.text.toString()
        val point = binding.etCurrentPoint.text.toString().toInt()
        val updatedMember = Member(name, point).apply {
            this.id = member.id
        }
        memberDao.updateMember(updatedMember)
    }

    companion object {
        const val EXTRAKEY_MEMBER = "member"
    }
}