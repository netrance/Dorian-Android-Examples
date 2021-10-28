package lee.dorian.android.sqlite_overview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import lee.dorian.android.sqlite_overview.databinding.ActivityEditMemberBinding

class EditMemberActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditMemberBinding

    private lateinit var dbHelper: SampleSQLiteOpenHelper
    private lateinit var memberDAO: MemberDAO
    private lateinit var member: Member

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init fields.
        binding = ActivityEditMemberBinding.inflate(layoutInflater)
        dbHelper = SampleSQLiteOpenHelper(this)
        memberDAO = MemberDAO(dbHelper)
        member = if (null == savedInstanceState) {
            intent.getSerializableExtra(EXTRAKEY_MEMBER) as Member
        }
        else {
            savedInstanceState.getSerializable(EXTRAKEY_MEMBER) as Member
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
        val updatedMember = Member(
            member.id,
            binding.etCurrentName.text.toString(),
            binding.etCurrentPoint.text.toString().toInt()
        )
        memberDAO.update(updatedMember)
        setResult(RESULT_OK)
        finish()
    }

    companion object {
        const val EXTRAKEY_MEMBER = "member"
    }
}