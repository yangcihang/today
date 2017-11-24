package com.hrsoft.today.mvp.view.mine.activity

import android.content.Intent
import android.text.InputType
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.hrsoft.today.App
import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.mvp.contract.MineContract
import com.hrsoft.today.mvp.model.User
import com.hrsoft.today.mvp.presenter.MineActivityPresenter
import com.hrsoft.today.mvp.view.mine.fragment.MineFragment
import com.hrsoft.today.mvp.view.mine.fragment.UpdatePswFragment
import com.hrsoft.today.util.DialogUtils
import com.hrsoft.today.util.FragmentUtil
import com.hrsoft.today.util.ImagePickerUtil
import com.hrsoft.today.util.ToastUtil
import com.lwkandroid.imagepicker.ImagePicker
import com.lwkandroid.imagepicker.data.ImageBean
import com.lwkandroid.imagepicker.data.ImagePickType
import jp.wasabeef.glide.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_mine.*
import kotlinx.android.synthetic.main.fragment_description.*

/**
 * @author YangCihang
 * @since  17/10/24.
 * email yangcihang@hrsoft.net
 */
class MineActivity : NoBarActivity(), MineContract.View {
    override var mPresenter: MineContract.Presenter? = MineActivityPresenter(this)
    private lateinit var mineFragment: MineFragment
    private lateinit var updatePswFragment: UpdatePswFragment
    var onUpdateSignCallback: ((String) -> Unit) = { txt_user_sign.text = it }
    private var picturePath = ""
    private var RCODE = 1 shl 2
    override fun initVariable() {
        mineFragment = MineFragment()
        updatePswFragment = UpdatePswFragment()
    }

    override fun initView() {
        initUserInfo()
        initFrame()
        txt_back.setOnClickListener { this.finish() }
        img_user_avatar.setOnClickListener {
            ImagePicker()
                    .pickType(ImagePickType.SINGLE)
                    .needCamera(true)
                    .cachePath(App.instance.cachePath)
                    .displayer(ImagePickerUtil())
                    .doCrop(1, 1, 480, 480)
                    .start(this, RCODE)
        }
    }

    /**
     * 初始化中间的fragment
     */
    private fun initFrame() {
        FragmentUtil.replace(this, R.id.frame_mine, mineFragment.apply {
            onUpdatePswClickedListener = {
                supportFragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_mine, updatePswFragment.apply {
                            onUpdatePswSuccessListener = {
                                supportFragmentManager.popBackStack()
                            }
                        })
                        .commit()
            }
        }, null)
    }

    private fun initUserInfo() {
        Glide.with(this)
                .load(User.avatar)
                .placeholder(R.drawable.ic_default_avatar)
                .error(R.drawable.ic_default_avatar)
                .bitmapTransform(CropCircleTransformation(this))
                .into(img_user_avatar)
        txt_user_name.text = User.name
        txt_user_sign.text = User.signature
    }

    override fun loadData() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_mine
    }


    override fun onPictureUploadSuccess(data: String) {
        ToastUtil.showToast(R.string.toast_update_avatar_success)
        User.avatar = data
    }

    override fun onPictureUploadFailed() {
        ToastUtil.showToast(R.string.toast_update_avatar_failed)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RCODE && data != null) {
            val resultList = data.getParcelableArrayListExtra<ImageBean>(ImagePicker.INTENT_RESULT_DATA)
            picturePath = resultList[0].imagePath
            Glide.with(this).load(picturePath).placeholder(R.mipmap.ic_launcher).into(img_user_avatar)
            if (!TextUtils.isEmpty(picturePath)) {
                mPresenter?.upLoadPicture(picturePath)
            } else ToastUtil.showToast(R.string.toast_image_load_error)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}