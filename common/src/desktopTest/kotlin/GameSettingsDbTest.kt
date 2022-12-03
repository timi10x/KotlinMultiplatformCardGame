import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import me.aliyuolalekan.WhotPlusDatabase
import me.aliyuolalekan.common.data.db.mapper.toGameSettingsTableDbData
import me.aliyuolalekan.common.domain.model.GameSettings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GameSettingsDbTest {

    private val inMemorySqlDriver =
        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply { WhotPlusDatabase.Schema.create(this) }

    private val queries = WhotPlusDatabase(inMemorySqlDriver).gameSettingsQueries

    @Test
    fun `test insert`() {

        val emptyData = queries.selectAll().executeAsList()
        assertEquals(emptyData.size, 0)

        val testGameSettings = GameSettings.DEFAULT
        queries.insertOrReplace(1L, 5L, 1L, 1L)

        val result = queries.selectAll().executeAsOne()
        assertEquals(testGameSettings.toGameSettingsTableDbData(), result)
    }

    @Test
    fun `test delete`() {
        queries.insertOrReplace(2L, 5L, 1L, 0L)
        val data = queries.selectAll().executeAsList()
        assertEquals(data.size, 1)
        queries.deleteAll()
        queries.selectAll().executeAsList()
            .also { assertTrue(it.isEmpty()) }
    }

}