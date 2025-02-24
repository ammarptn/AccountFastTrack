package com.ammarptn.accountFastTrack.di

import android.app.Application
import androidx.room.Room
import com.ammarptn.accountFastTrack.data.local.AccountDatabase
import com.ammarptn.accountFastTrack.data.repository.AccountRepositoryImpl
import com.ammarptn.accountFastTrack.domain.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShortcutDatabase(app: Application): AccountDatabase {
        return Room.databaseBuilder(
            app,
            AccountDatabase::class.java,
            "account_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAccountRepository(db: AccountDatabase): AccountRepository {
        return AccountRepositoryImpl(db.accountDao())
    }
}