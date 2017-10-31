package com.hrsoft.today.mvp.view.main

import android.support.v7.widget.LinearLayoutManager
import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.mvp.contract.MainContract
import com.hrsoft.today.mvp.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : NoBarActivity(), MainContract.View {
    override var mPresenter: MainContract.Presenter? = MainActivityPresenter(this)

    lateinit var adapter: TestAdapter
    override fun onDataLoadSuccess() {
    }

    override fun initVariable() {
        adapter = TestAdapter(this)
    }

    override fun initView() {
        rec_test.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    override fun loadData() {
        (0..12).mapTo(adapter.dataList) { "" + it }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

}
