package com.example.newsappcompose.di

import android.content.Context
import androidx.room.Room
import com.example.newsappcompose.api.ApiService
import com.example.newsappcompose.room.ArticleDao
import com.example.newsappcompose.room.ArticleDataBase
import com.example.newsappcompose.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun retrofit(): ApiService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): ArticleDataBase {
        return Room.databaseBuilder(context, ArticleDataBase::class.java, "articleDB").build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: ArticleDataBase): ArticleDao {
        return movieDatabase.articleDao()
    }

}