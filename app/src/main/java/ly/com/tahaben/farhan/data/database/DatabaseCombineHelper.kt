package ly.com.tahaben.farhan.data.database

import android.content.Context
import ly.com.tahaben.domain.preferences.Preferences

/* Created by Taha https://github.com/tahaak67/ at 15/9/2024 */

class DatabaseCombineHelper(
    private val context: Context,
    private val farhanDatabase: VikasDatabase,
    private val preferences: Preferences) {

    suspend fun combineDatabases(): Boolean{
        // Missing database dependencies (NotificationDatabase, UsageDatabase, etc.)
        // Stubbed for build purposes.
        preferences.saveShouldCombineDb(false)
        return true
    }
}
