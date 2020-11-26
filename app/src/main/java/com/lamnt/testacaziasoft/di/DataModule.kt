package com.lamnt.testacaziasoft.di

import android.content.Context
import android.content.SharedPreferences
import com.lamnt.testacaziasoft.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {
    @Provides
    fun providePref(@ApplicationContext context: Context) : SharedPreferences
            = context.getSharedPreferences(Constant.PREF_NAME,Context.MODE_PRIVATE)

    @Provides
    fun providePrefEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor = sharedPreferences.edit()


}