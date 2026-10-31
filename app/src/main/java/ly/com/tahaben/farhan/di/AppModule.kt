package ly.com.tahaben.farhan.di

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import androidx.work.Configuration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ly.com.tahaben.farhan.data.database.DatabaseCombineHelper
import ly.com.tahaben.farhan.data.database.VikasDatabase
import ly.com.tahaben.farhan.data.database.UnlockRepositoryImpl
import ly.com.tahaben.usage_overview_domain.repository.UnlockRepository
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<androidx.datastore.preferences.core.Preferences>{
        return PreferenceDataStoreFactory.create(
            produceFile = {context.preferencesDataStoreFile("farhan_data_store")}
        )
    }

    @Provides
    @Singleton
    fun provideCacheWorkerFactory(): ly.com.tahaben.farhan.work.DefaultWorkerFactory {
        return ly.com.tahaben.farhan.work.DefaultWorkerFactory()
    }

    @Provides
    @Singleton
    fun provideCacheWorkerConfiguration(defaultWorkerFactory: ly.com.tahaben.farhan.work.DefaultWorkerFactory): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(defaultWorkerFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
        ): VikasDatabase{
        Timber.d("database path: ${ context.getDatabasePath("farhan_db") }")
        val farhanDatabase = Room
            .databaseBuilder(context, VikasDatabase::class.java, "farhan_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration(false)
            .build()
        return farhanDatabase
    }

    @Provides
    @Singleton
    fun provideDatabaseCombineHelper(
        @ApplicationContext context: Context,
        farhanDatabase: VikasDatabase,
        preferences: ly.com.tahaben.domain.preferences.Preferences
    ): DatabaseCombineHelper {
        return DatabaseCombineHelper(context, farhanDatabase, preferences)
    }

    @Provides
    @Singleton
    fun provideUnlockRepository(db: VikasDatabase): UnlockRepository {
        return UnlockRepositoryImpl(db.unlockDao)
    }
}
