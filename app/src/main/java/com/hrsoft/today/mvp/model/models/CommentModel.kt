package com.hrsoft.today.mvp.model.models

import android.os.Parcel
import android.os.Parcelable

/**
 * @author abtion.
 * @since 17/11/6 22:48.
 * email caiheng@hrsoft.net.
 */
class CommentModel() : Parcelable {
    var  id:Int=0
    var calendarId:Int = 0
    var userId:Int =0
    var userAvatar:String = ""
    var userName:String = ""
    var comment:String = ""
    var createdAt:Long = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        calendarId = parcel.readInt()
        userId = parcel.readInt()
        userAvatar = parcel.readString()
        userName = parcel.readString()
        comment = parcel.readString()
        createdAt = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(calendarId)
        parcel.writeInt(userId)
        parcel.writeString(userAvatar)
        parcel.writeString(userName)
        parcel.writeString(comment)
        parcel.writeLong(createdAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CommentModel> {
        override fun createFromParcel(parcel: Parcel): CommentModel {
            return CommentModel(parcel)
        }

        override fun newArray(size: Int): Array<CommentModel?> {
            return arrayOfNulls(size)
        }
    }


}