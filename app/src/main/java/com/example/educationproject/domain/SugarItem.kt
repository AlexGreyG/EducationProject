package com.example.educationproject.domain

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.example.educationproject.domain.SugarItem.CREATOR.UNDEFINED_ID

@SuppressLint("ParcelCreator")
data class SugarItem(var sugar: Float, var date: String, var isDeleted: Boolean, var id: Int = UNDEFINED_ID): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readFloat(), parcel.readString().toString(), parcel.readByte() != 0.toByte(), parcel.readInt()) {
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeFloat(sugar)
        parcel.writeString(date)
        parcel.writeByte(if (isDeleted) 1 else 0)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SugarItem> {
        const val UNDEFINED_ID = -1
        override fun createFromParcel(parcel: Parcel): SugarItem {
            return SugarItem(parcel)
        }

        override fun newArray(size: Int): Array<SugarItem?> {
            return arrayOfNulls(size)
        }
    }
}