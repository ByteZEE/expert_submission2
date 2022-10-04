package com.rafiadly.otakubandung.core.di

import androidx.room.Room
import com.rafiadly.otakubandung.core.BuildConfig.DEBUG
import com.rafiadly.otakubandung.core.data.local.room.FavoriteAnimeDatabase
import com.rafiadly.otakubandung.core.data.remote.RemoteDataSource
import com.rafiadly.otakubandung.core.data.remote.network.ApiService
import com.rafiadly.otakubandung.core.domain.repository.IAnimeRepository
import com.rafiadly.otakubandung.core.utils.NetworkInfo.BASE_URL
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FavoriteAnimeDatabase>().favoriteAnimeDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("4lwanfauzy".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FavoriteAnimeDatabase::class.java,
            "FavoriteAnime.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "apiary.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/EixssQ+2iRjerS4nmUZVzfx24Ejralsi/2SAwH5TMw4=")
            .add(hostname, "sha256/Tye2VvGE3uGFQtY88GFKVsZtVHmpBuLj7jEJO9VapxQ=")
            .build()
        val loggingInterceptor =
            if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(loggingInterceptor))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.rafiadly.otakubandung.core.data.local.LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IAnimeRepository> { com.rafiadly.otakubandung.core.data.AnimeRepository(get(), get()) }
}