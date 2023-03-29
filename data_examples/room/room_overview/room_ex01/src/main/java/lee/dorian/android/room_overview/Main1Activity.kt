package lee.dorian.android.room_overview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lee.dorian.android.room_common.AppDatabase
import lee.dorian.android.room_common.Member
import lee.dorian.android.room_common.MemberListAdapter
import lee.dorian.android.room_common.OnMemberItemLongClickListener
import lee.dorian.android.room_common.databinding.ActivityMainBinding

class Main1Activity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val memberListAdapter: MemberListAdapter by lazy {
        MemberListAdapter(memberItemLongClickListener)
    }

    private val db by lazy {
        AppDatabase.getInstance(this)
    }

    private val memberDao by lazy {
        db.memberDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvMemberList.layoutManager = LinearLayoutManager(this)
        binding.rvMemberList.adapter = memberListAdapter

        // Set listeners.
        binding.btnAddMember.setOnClickListener(btnAddMemberClickListener)
        binding.btnSearchMembers.setOnClickListener(btnSearchMembersClickListener)
    }

    private val btnAddMemberClickListener = View.OnClickListener {
        val name = binding.etNewName.text.toString()
        val point = binding.etNewPoint.text.toString()

        if (name.isEmpty() || point.isEmpty()) {
            Toast.makeText(this, "Input name and point.", Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }

        val newMember = Member(name, point.toInt())
        lifecycleScope.launch(Dispatchers.IO) {
            val insertCount = memberDao.insertMember(newMember)

            launch(Dispatchers.Main) {
                when (insertCount) {
                    -1L -> Toast.makeText(this@Main1Activity, "Cannot insert.", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(this@Main1Activity, "Inserted new member.", Toast.LENGTH_SHORT).show()
                }

                resetMemberInputForm()
            }
        }
    }

    private val btnSearchMembersClickListener = View.OnClickListener {
        searchMember()
    }

    private val memberItemLongClickListener = OnMemberItemLongClickListener { v, member ->
        val popupMenu = PopupMenu(this@Main1Activity, v)
        menuInflater.inflate(R.menu.menu_member, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuEdit -> {
                    val intent =
                        Intent(this@Main1Activity, EditMember1Activity::class.java).apply {
                            putExtra(EditMember1Activity.EXTRAKEY_MEMBER, member)
                        }
                    editMemberActivityResultLauncher.launch(intent)
                }
                R.id.menuDelete -> lifecycleScope.launch(Dispatchers.IO) {
                    memberDao.deleteMember(member)
                    searchMember()
                }
            }
            false
        }

        popupMenu.show()
        false
    }

    val editMemberActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            searchMember()
        }
    }

    fun resetMemberInputForm() {
        binding.etNewName.text.clear()
        binding.etNewPoint.text.clear()
    }

    fun searchMember() = lifecycleScope.launch(Dispatchers.IO) {
        val name = binding.svName.query.toString()
        val point = binding.svPoint.query.toString()
        val newMemberList: List<Member> = when {
            (name.isEmpty() && point.isEmpty()) -> memberDao.selectAll()
            (name.isNotEmpty() && point.isEmpty()) -> memberDao.selectByName(name)
            (name.isEmpty() && point.isNotEmpty()) -> memberDao.selectByPoint(point.toInt())
            else -> memberDao.select(name, point.toInt())
        }

        launch(Dispatchers.Main) {
            memberListAdapter.memberList = newMemberList
        }
    }

}