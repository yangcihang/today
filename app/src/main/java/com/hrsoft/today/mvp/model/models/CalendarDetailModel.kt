package com.hrsoft.today.mvp.model.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * @author abtion.
 * @since 17/11/7 01:11.
 * email caiheng@hrsoft.net.
 */
class CalendarDetailModel : Serializable {
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
    var isSubscribed = false
    var preview: CalendarModel? = null

}