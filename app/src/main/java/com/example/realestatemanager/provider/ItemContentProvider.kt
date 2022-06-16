package com.example.realestatemanager.provider

import android.content.ClipData.Item
import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.example.realestatemanager.database.RealEstateManagerDatabase


class ItemContentProvider : ContentProvider() {

    val AUTHORITY = "com.example.realestatemanager.provider"

    val TABLE_NAME = Item::class.java.simpleName

    val URI_PROPERTY= Uri.parse("content://$AUTHORITY/$TABLE_NAME")

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
        return "vnd.android.cursor.property/" + AUTHORITY + "." + TABLE_NAME;
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        if (getContext() != null && values != null) {
            /*var id: Long = RealEstateManagerDatabase.getDatabase(context!!).getPropertyDao().insert(Property(n).fromContentValues(values))

            if (!id.equals(0)) {

                getContext()?.getContentResolver()?.notifyChange(uri, null);

                return ContentUris.withAppendedId(uri, id);}*/
            return null
        }

        throw IllegalArgumentException("Failed to insert row into " + uri);
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