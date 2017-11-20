package com.hrsoft.today.mvp.view.detail.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseRecyclerAdapter
import com.hrsoft.today.mvp.model.CommentModel
import com.hrsoft.today.util.TimeUtil
import com.hrsoft.today.widget.CropCircleTransformation

/**
 * @author abtion.
 * @since 17/11/6 22:44.
 * email caiheng@hrsoft.net.
 */
class CommentAdapter(mContext: Context): BaseRecyclerAdapter<CommentModel>(mContext) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<CommentModel> {
        val view: View = inflater.inflate(R.layout.item_comment, parent, false)
        return ItemHolder(view)
    }

    inner class ItemHolder(var view:View): BaseViewHolder<CommentModel>(view) {
        private var userAvatarImg:ImageView = view.findViewById(R.id.img_user_avatar)
        private var userNameTxt:TextView = view.findViewById(R.id.txt_user_name)
        private var createTimeTxt:TextView = view.findViewById(R.id.txt_time)
        private var commentTxt:TextView = view.findViewById(R.id.txt_comment)


        override fun onBind(position: Int) {
            Glide.with(mContext).load(mData?.userAvatar).bitmapTransform(CropCircleTransformation(mContext)).into(userAvatarImg)
            userNameTxt.text = mData?.userName
            createTimeTxt.text = TimeUtil.setStampToString(mData?.createdAt!!,TimeUtil.DATETIME_DEFAULT_FORMAT)
            commentTxt.text = mData?.comment
        }
    }
}