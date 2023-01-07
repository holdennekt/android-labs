package com.example.mobile_labs

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

data class Answer(val author: String, val years: String)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "FeedReader.db"
        private var mInstance: DatabaseHelper? = null
        fun getInstance(ctx: Context): DatabaseHelper {
            if (mInstance == null) {
                mInstance = DatabaseHelper(ctx.applicationContext)
            }
            return mInstance as DatabaseHelper
        }
    }

    object DatabaseContract {
        object Answers : BaseColumns {
            const val TABLE_NAME = "answers"
            const val COLUMN_NAME_AUTHOR = "author"
            const val COLUMN_NAME_YEARS = "years"
        }
    }

    private val sqlCreateEntries =
        "CREATE TABLE ${DatabaseContract.Answers.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.Answers.COLUMN_NAME_AUTHOR} TEXT," +
                "${DatabaseContract.Answers.COLUMN_NAME_YEARS} TEXT)"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlCreateEntries)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun insertAnswer(author: String, years: String): Long {
        val values = ContentValues().apply {
            put(DatabaseContract.Answers.COLUMN_NAME_AUTHOR, author)
            put(DatabaseContract.Answers.COLUMN_NAME_YEARS, years)
        }

        return writableDatabase.insert(DatabaseContract.Answers.TABLE_NAME, null, values)
    }

    fun selectAnswers(): MutableList<Answer> {
        val projection = arrayOf(
            BaseColumns._ID,
            DatabaseContract.Answers.COLUMN_NAME_AUTHOR,
            DatabaseContract.Answers.COLUMN_NAME_YEARS
        )

        val cursor = readableDatabase.query(
            DatabaseContract.Answers.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val answers = mutableListOf<Answer>()
        with(cursor) {
            while (moveToNext()) {
                val author = getString(getColumnIndexOrThrow(DatabaseContract.Answers.COLUMN_NAME_AUTHOR))
                val years = getString(getColumnIndexOrThrow(DatabaseContract.Answers.COLUMN_NAME_YEARS))
                answers.add(Answer(author, years))
            }
        }
        cursor.close()
        return answers
    }

    fun deleteAnswers() {
        writableDatabase.delete(DatabaseContract.Answers.TABLE_NAME, null, null)
    }
}

