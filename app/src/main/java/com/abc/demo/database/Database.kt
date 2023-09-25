package com.abc.demo.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.abc.demo.model.aaIncome

class Database(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")")
        db.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }

    fun addContact(contact: aaIncome) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, contact.name)
        values.put(KEY_PH_NO, contact.phoneNumber)
        db.insert(TABLE_CONTACTS, null, values)
        db.close()
    }

    fun getContact(id: Int): aaIncome {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_CONTACTS,
            arrayOf(
                KEY_ID,
                KEY_NAME, KEY_PH_NO
            ),
            KEY_ID + "=?",
            arrayOf(id.toString()),
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        return aaIncome(
            cursor!!.getString(0).toInt(),
            cursor.getString(1), cursor.getString(2)
        )
    }

    val allContacts: List<aaIncome>
        get() {
            val contactList: MutableList<aaIncome> = ArrayList()
            val selectQuery = "SELECT  * FROM " + TABLE_CONTACTS
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val contact = aaIncome()
                    contact.iD = cursor.getString(0).toInt()
                    contact.name = cursor.getString(1)
                    contact.phoneNumber = cursor.getString(2)
                    contactList.add(contact)
                } while (cursor.moveToNext())
            }
            return contactList
        }

    fun updateContact(contact: aaIncome): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, contact.name)
        values.put(KEY_PH_NO, contact.phoneNumber)
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?", arrayOf(contact.iD.toString()))
    }

    fun deleteContact(contact: aaIncome) {
        val db = this.writableDatabase
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?", arrayOf(contact.iD.toString()))
        db.close()
    }

    val contactsCount: Int
        get() {
            val countQuery = "SELECT  * FROM " + TABLE_CONTACTS
            val db = this.readableDatabase
            val cursor = db.rawQuery(countQuery, null)
            cursor.close()
            return cursor.count
        }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "incomeManager"
        private const val TABLE_CONTACTS = "income"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_PH_NO = "coin"
    }
}