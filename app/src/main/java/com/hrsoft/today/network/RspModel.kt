package com.hrsoft.today.network


/**
 * @author YangCihang
 * @since  17/9/25.
 * email yangcihang@hrsoft.net
 */


open class RspModel <T>{
    var code: Int = 0 //返回码
    var data:T? = null
}
