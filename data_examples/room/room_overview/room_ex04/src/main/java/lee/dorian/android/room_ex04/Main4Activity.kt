package lee.dorian.android.room_ex04

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import lee.dorian.android.room_common.Member
import lee.dorian.android.room_common.MemberListAdapter
import lee.dorian.android.room_common.OnMemberItemLongClickListener
import lee.dorian.android.room_common.R
import lee.dorian.android.room_common.databinding.ActivityMainBinding

@AndroidEntryPoint
class Main4Activity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: Main4ViewModel by viewModels()

    private val memberListAdapter: MemberListAdapter by lazy {
        MemberListAdapter(memberItemLongClickListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Set views.
        binding.rvMemberList.layoutManager = LinearLayoutManager(this)
        binding.rvMemberList.adapter = memberListAdapter

        // Set listeners.
        binding.btnAddMember.setOnClickListener(btnAddMemberClickListener)
        binding.btnSearchMembers.setOnClickListener(btnSearchMembersClickListener)

        // Collect data.
        lifecycleScope.launch {
            viewModel.flowMemberList.collect(flowMemberListCollector)
        }
        lifecycleScope.launch {
            viewModel.flowInsertedRowID.collect(flowInsertedRowIDCollector)
        }
    }

    private val btnAddMemberClickListener = View.OnClickListener {
        val name = binding.etNewName.text.toString()
        val point = binding.etNewPoint.text.toString()

        if (name.isEmpty() or point.isEmpty()) {
            Toast.makeText(this, "Input name and point.", Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }

        val newMember = Member(name, point.toInt())
        lifecycleScope.launch {
            viewModel.insert(newMember)
        }
    }

    private val btnSearchMembersClickListener = View.OnClickListener {
        searchMember()
    }

    private val memberItemLongClickListener = OnMemberItemLongClickListener { v, member ->
        val popupMenu = PopupMenu(this@Main4Activity, v)
        menuInflater.inflate(R.menu.menu_member, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuEdit -> {
                    val intent = Intent(this@Main4Activity, EditMember4Activity::class.java).apply {
                        putExtra(EditMember4ViewModel.KEY_MEMBER, member)
                    }
                    editMemberActivityResultLauncher.launch(intent)
                }
                R.id.menuDelete -> lifecycleScope.launch {
                    viewModel.delete(member)
                    searchMember()
                }
            }
            false
        }

        popupMenu.show()
    }

    private val editMemberActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            searchMember()
        }
    }

    private fun resetMemberInputForm() {
        binding.etNewName.text.clear()
        binding.etNewPoint.text.clear()
    }

    private fun searchMember() = lifecycleScope.launch {
        val name = binding.svName.query.toString()
        val point = binding.svPoint.query.toString()
        viewModel.search(name, point)
    }

    private val flowInsertedRowIDCollector = FlowCollector<Long> {
        when (it) {
            -1L -> Toast.makeText(this@Main4Activity, "Cannot insert.", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this@Main4Activity, "Inserted new member.", Toast.LENGTH_SHORT)
                .show()
        }

        resetMemberInputForm()
    }

    private val flowMemberListCollector = FlowCollector<List<Member>> {
        memberListAdapter.memberList = it
    }

}