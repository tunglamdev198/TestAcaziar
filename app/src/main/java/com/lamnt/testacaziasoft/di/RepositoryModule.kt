package com.lamnt.testacaziasoft.di

import com.lamnt.testacaziasoft.repository.PrefRepository
import com.lamnt.testacaziasoft.repository.UsersRepository
import com.lamnt.testacaziasoft.repository.impl.PrefImpl
import com.lamnt.testacaziasoft.repository.impl.UsersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPrefRepository(prefImpl: PrefImpl) : PrefRepository

    @Binds
    abstract fun bindUsersRepository(usersImpl: UsersImpl) : UsersRepository
}