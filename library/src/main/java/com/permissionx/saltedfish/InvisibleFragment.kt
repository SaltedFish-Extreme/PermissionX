package com.permissionx.saltedfish

import androidx.fragment.app.Fragment


/**
 * Created by 咸鱼至尊 on 2021/11/24
 *
 * des: 权限申请Fragment
 */
class InvisibleFragment : Fragment() {
    //请求单个权限名称
    private lateinit var permission: String

    //请求多个权限名称集合
    private lateinit var permissions: Array<String>

    //请求单个权限结果回调
    private lateinit var mCallback: PermissionCallback

    //请求多个权限结果回调
    private lateinit var mCallbacks: PermissionsCallback

    /**
     * 请求单个权限
     *
     * @param cb 单个权限结果回调
     * @param permission 权限名称
     */
    internal fun requestNow(cb: PermissionCallback, permission: String) {
        //将权限和回调赋值
        mCallback = cb
        this.permission = permission
    }

    /**
     * 请求多个权限
     *
     * @param cb 多个权限结果回调
     * @param permissions 权限名称数组
     */
    internal fun requestNow(cb: PermissionsCallback, vararg permissions: String) {
        //将权限和回调赋值
        mCallbacks = cb
        this.permissions = arrayOf(*permissions)
    }

    /**
     * 发起单个权限请求
     */
    internal val requestPermission = requestPermission {
        //结果回调
        mCallback(it)
    }

    /**
     * 发起多个权限请求
     */
    internal val requestMultiplePermission = requestMultiplePermission { map, b ->
        //将<权限名称，授予与否>集合及请求权限是否全部授予结果返回
        mCallbacks(map, b)
    }
}