package com.example.realestatemanager.provider

import android.content.ClipData.Item
import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.example.realestatemanager.database.RealEstateManagerDatabase


class ItemContentProvider : ContentProvider() {

    companion object{
        val AUTHORITY = "com.example.realestatemanager.provider"

        val TABLE_NAME = Item::class.java.simpleName

        var URI_PROPERTY= Uri.parse("content://$AUTHORITY/$TABLE_NAME")
    }



    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        if (context != null) {
            val propertiId = ContentUris.parseId(uri)
            val cursor: Cursor? =
                RealEstateManagerDatabase.getDatabase(context!!).getPropertyDao().getItemsWithCursor(propertiId)
            cursor?.setNotificationUri(context!!.contentResolver, uri)
            return cursor
        }

        throw IllegalArgumentException("Failed to query row for uri $uri")
    }

    override fun getType(uri: Uri): String {
        return "vnd.android.cursor.property/" + AUTHORITY + "." + TABLE_NAME
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {

            return null

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
            return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
       return 0
    }
}