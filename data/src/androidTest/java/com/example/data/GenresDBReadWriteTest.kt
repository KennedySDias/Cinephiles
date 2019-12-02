package com.example.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.local.GenresDataBase
import com.example.data.local.dao.GenresDao
import com.example.data.local.entity.GenreCache
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class GenresDBReadWriteTest {

    private lateinit var genresDao: GenresDao
    private lateinit var db: GenresDataBase

    private val genreCacheFull = GenreCache(
            id = 1,
            name = "Action"
    )

    private val genreCacheFull2 = GenreCache(
            id = 2,
            name = "Romance"
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, GenresDataBase::class.java
        ).build()
        genresDao = db.genresDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeGenreAndReadInList() {
        runBlocking { genresDao.insert(genreCacheFull) }
        runBlocking { genresDao.insert(genreCacheFull2) }

        val list = runBlocking { genresDao.getAll() }
        assertEquals(listOf(genreCacheFull, genreCacheFull2), list)
    }

    @Test
    @Throws(Exception::class)
    fun writeGenreAndDelete() {
        runBlocking { genresDao.insert(genreCacheFull) }
        runBlocking { genresDao.delete(genreCacheFull) }

        val list = runBlocking { genresDao.getAll() }
        assertEquals(listOf<GenreCache>(), list)
    }

    @Test
    @Throws(Exception::class)
    fun writeGenreAndDeleteAll() {
        runBlocking { genresDao.insert(genreCacheFull) }
        runBlocking { genresDao.deleteAll() }

        val list = runBlocking { genresDao.getAll() }
        assertEquals(listOf<GenreCache>(), list)
    }

    @Test
    @Throws(Exception::class)
    fun writeGenreAndUpdate() {
        runBlocking { genresDao.insert(genreCacheFull) }
        runBlocking { genresDao.insert(genreCacheFull2) }
        runBlocking { genresDao.update(listOf(genreCacheFull)) }

        val list = runBlocking { genresDao.getAll() }
        assertEquals(listOf(genreCacheFull), list)
    }

}