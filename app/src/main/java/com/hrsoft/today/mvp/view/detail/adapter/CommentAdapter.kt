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

/**
 * @author abtion.
 * @since 17/11/6 22:44.
 * email caiheng@hrsoft.net.
 */
class CommentAdapter(mContext: Context): BaseRecyclerAdapter<CommentModel>(mContext) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<CommentModel> {
        var view:View = inflater.inflate(R.layout.item_comment,parent,false)
        return ItemHolder(view)
    }

    inner class ItemHolder(var view:View): BaseViewHolder<CommentModel>(view) {
        var userAvatarImg:ImageView = view.findViewById(R.id.img_user_avatar)
        var userNameTxt:TextView = view.findViewById(R.id.txt_user_name)
        var createTimeTxt:TextView = view.findViewById(R.id.txt_time)
        var commentTxt:TextView = view.findViewById(R.id.txt_comment)
        var goodSumTxt:TextView= view.findViewById(R.id.txt_good_sum)
        var goodCommentImg:ImageView = view.findViewById(R.id.img_good_comment)

        override fun onBind(position: Int) {
            Glide.with(mContext).load(mData?.userAvatar).placeholder(R.mipmap.ic_launcher).into(userAvatarImg)
            userNameTxt.text = mData?.userName
            createTimeTxt.text = TimeUtil.setStampToString(mData?.createAt!!,TimeUtil.DATETIME_DEFAULT_FORMAT)
            commentTxt.text = mData?.comment
        }
    }
}