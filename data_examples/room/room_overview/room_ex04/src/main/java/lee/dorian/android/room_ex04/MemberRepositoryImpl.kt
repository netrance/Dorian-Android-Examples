package lee.dorian.android.room_ex04

import android.app.Application
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lee.dorian.android.room_common.AppDatabase
import lee.dorian.android.room_common.Member
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    @HiltQualifier.AppDatabase
    private val db: AppDatabase,
    @HiltQualifier.CoroutineDispatcherIO
    private val dispatcher: CoroutineDispatcher
) : MemberRepository {

    //private val db = AppDatabase.getInstance(app)
    private val memberDao = db.memberDao()

    override suspend fun insert(member: Member): Long = withContext(dispatcher) {
        memberDao.insertMember(member)
    }

    override suspend fun update(member: Member) = withContext(dispatcher) {
        memberDao.updateMember(member)
    }

    override suspend fun delete(member: Member) = withContext(dispatcher) {
        memberDao.deleteMember(member)
    }

    override suspend fun search(name: String, point: String): List<Member> = withContext(dispatcher) {
        when {
            (name.isEmpty() && point.isEmpty()) -> memberDao.selectAll()
            (name.isNotEmpty() && point.isEmpty()) -> memberDao.selectByName(name)
            (name.isEmpty() && point.isNotEmpty()) -> memberDao.selectByPoint(point.toInt())
            else -> memberDao.select(name, point.toInt())
        }
    }

}
