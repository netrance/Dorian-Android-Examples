package lee.dorian.android.room_ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import lee.dorian.android.room_common.Member
import lee.dorian.android.room_common.databinding.ActivityEditMemberBinding
import lee.dorian.android.room_common.openOKAlertDialog

class EditMember3Activity : AppCompatActivity() {
    private val binding by lazy {
        ActivityEditMemberBinding.inflate(layoutInflater)
    }

    private val viewModel: EditMember3ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init data.
        viewModel.memberToEdit = intent.getSerializableExtra(EditMember3ViewModel.KEY_MEMBER) as Member
        if (null == viewModel.memberToEdit) {
            openOKAlertDialog("Error", "Invalid member.") {
                finish()
            }
        }

        // Init UI.
        setContentView(binding.root)
        binding.etCurrentName.setText(viewModel.memberToEdit?.name)
        binding.etCurrentPoint.setText(viewModel.memberToEdit?.point.toString())

        // Set listeners.
        binding.btnEditMember.setOnClickListener(btnEditMemberClickListener)
    }

    private val btnEditMemberClickListener = View.OnClickListener {
        val memberToUpdate = Member(
            binding.etCurrentName.text.toString(),
            binding.etCurrentPoint.text.toString().toInt()
        ).apply {
            this.id = viewModel.memberToEdit?.id ?: -1
        }

        lifecycleScope.launch {
            viewModel.update(memberToUpdate)
            setResult(RESULT_OK)
            finish()
        }
    }

}