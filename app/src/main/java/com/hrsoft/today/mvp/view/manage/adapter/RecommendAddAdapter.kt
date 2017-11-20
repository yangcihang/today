package com.hrsoft.today.mvp.view.manage.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseRecyclerAdapter

/**
 * @author YangCihang.
 * @since 17/11/18 17:19.
 * email yangcihang@hrsoft.net.
 */
class RecommendAddAdapter(mContext: Context) : BaseRecyclerAdapter<String>(mContext) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<String> {
        return ItemHolder(inflater.inflate(R.layout.item_recommend_input, parent, false))
    }

    inner class ItemHolder(itemView: View) : BaseRecyclerAdapter.BaseViewHolder<String>(itemView) {
        private var deleteImg: ImageView = itemView.findViewById(R.id.img_delete)
        //TODO(最多15字符)
        private var contentEdit: EditText = itemView.findViewById(R.id.edit_recommend_content)

        override fun onBind(position: Int) {
            deleteImg.visibility = if (position > 1) View.VISIBLE else View.GONE
            contentEdit.let {
                //TODO(此时的position应该为adapterPosition)
                deleteImg.setOnClickListener { remove(adapterPosition) }
                it.setText(dataList[adapterPosition])
                it.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        Log.d("123", "" + adapterPosition)
                        dataList[adapterPosition] = contentEdit.text.toString().trim()
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }
                })
            }
        }

    }
}