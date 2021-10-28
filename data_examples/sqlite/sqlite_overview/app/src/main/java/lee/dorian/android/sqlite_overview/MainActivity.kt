package lee.dorian.android.sqlite_overview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import lee.dorian.android.sqlite_overview.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var memberListAdapter: MemberListAdapter

    private lateinit var dbHelper: SampleSQLiteOpenHelper
    private lateinit var memberDAO: MemberDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init fields.
        binding = ActivityMainBinding.inflate(layoutInflater)
        dbHelper = SampleSQLiteOpenHelper(this)
        memberDAO = MemberDAO(dbHelper)
        memberListAdapter = MemberListAdapter(memberItemLongClickListener)

        // Init UI.
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

        val newMember = Member.createNewMember(name, point.toInt())
        when (memberDAO.insert(newMember)) {
            -1L -> Toast.makeText(this, "Cannot insert.", Toast.LENGTH_SHORT).show()
            else -> {
                Toast.makeText(this, "Inserted new member.", Toast.LENGTH_SHORT).show()
            }
        }

        resetMemberInputForm()
    }

    private val btnSearchMembersClickListener = View.OnClickListener {
        searchMember()
    }

    private val memberItemLongClickListener = OnMemberItemLongClickListener { v, member ->
        val popupMenu = PopupMenu(this@MainActivity, v)
        menuInflater.inflate(R.menu.menu_member, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuEdit -> {
                    val intent = Intent(this@MainActivity, EditMemberActivity::class.java).apply {
                        putExtra(EditMemberActivity.EXTRAKEY_MEMBER, member)
                    }
                    editMemberActivityResultLauncher.launch(intent)
                }
                R.id.menuDelete -> {
                    memberDAO.delete(member.id)
                    binding.btnSearchMembers.performClick()
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

    fun searchMember() {
        val name = binding.svName.query.toString()
        val point = binding.svPoint.query.toString()
        lateinit var newMemberList: List<Member>

        if (name.isEmpty() && point.isEmpty()) {
            newMemberList = memberDAO.select()
        }
        else if (!name.isEmpty() && point.isEmpty()) {
            newMemberList = memberDAO.select(name)
        }
        else if (name.isEmpty() && !point.isEmpty()) {
            newMemberList = memberDAO.select(point.toInt())
        }
        else {
            newMemberList = memberDAO.select(name, point.toInt())
        }

        memberListAdapter.memberList = newMemberList
    }
}