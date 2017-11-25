package com.hrsoft.today.mvp.model.models

import android.os.Parcel
import android.os.Parcelable

/**
 * @author abtion.
 * @since 17/11/7 01:11.
 * email caiheng@hrsoft.net.
 */
class CalendarDetailModel() : Parcelable {
    var id = 0
    var title = ""
    var description = ""
    var creatorId = 0
    var creatorName = ""
    var creatorAvatar = ""
    var subscribed = 0
    var picture = ""
    var goodPick = 0
    var badPick = 0


    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString()
        description = parcel.readString()
        creatorId = parcel.readInt()
        creatorName = parcel.readString()
        creatorAvatar = parcel.readString()
        subscribed = parcel.readInt()
        picture = parcel.readString()
        goodPick = parcel.readInt()
        badPick = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(creatorId)
        parcel.writeString(creatorName)
        parcel.writeString(creatorAvatar)
        parcel.writeInt(subscribed)
        parcel.writeString(picture)
        parcel.writeInt(goodPick)
        parcel.writeInt(badPick)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CalendarDetailModel> {
        override fun createFromParcel(parcel: Parcel): CalendarDetailModel {
            return CalendarDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<CalendarDetailModel?> {
            return arrayOfNulls(size)
        }
    }
}