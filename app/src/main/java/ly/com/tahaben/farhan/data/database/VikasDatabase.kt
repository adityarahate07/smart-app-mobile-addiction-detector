package ly.com.tahaben.farhan.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [UnlockEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(LocalDateTypeConverter::class)
abstract class VikasDatabase : RoomDatabase() {
    abstract val unlockDao: UnlockDao
}
