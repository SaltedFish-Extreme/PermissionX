package com.permissionx.saltedfish

import androidx.fragment.app.FragmentActivity

/**
 * Created by 咸鱼至尊 on 2021/11/24
 *
 * des: 权限申请对外接口
 */
object PermissionX {
    //申请权限的Fragment
    private const val TAG = "InvisibleFragment"

    /**
     * 申请单个权限
     *
     * @param activity Activity
     * @param permission 请求的权限名
     * @param callback 是否授予结果回调
     */
    fun requestPermission(activity: FragmentActivity, permission: String, callback: PermissionCallback) {
        //获取Fragment管理器
        val fragmentManager = activity.supportFragmentManager
        //通过TAG查询目标Fragment
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        //判断是否已创建过该Fragment
        val fragment = if (existedFragment != null) {
            //已创建则重复使用
            existedFragment as InvisibleFragment
        } else {
            //否则新建并添加TAG返回
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        //请求权限
        fragment.requestNow(callback, permission)
        //发起请求
        fragment.requestPermission.launch(permission)
    }

    /**
     * 申请多个权限
     *
     * @param activity Activity
     * @param permissions 请求的多个权限名
     * @param callback <权限名称，授予与否>集合及请求权限是否全部授予结果回调
     */
    fun requestPermissions(activity: FragmentActivity, vararg permissions: String, callback: PermissionsCallback) {
        //获取Fragment管理器
        val fragmentManager = activity.supportFragmentManager
        //通过TAG查询目标Fragment
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        //判断是否已创建过该Fragment
        val fragment = if (existedFragment != null) {
            //已创建则重复使用
            existedFragment as InvisibleFragment
        } else {
            //否则新建并添加TAG返回
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        //请求权限
        fragment.requestNow(callback, *permissions)
        //发起请求
        fragment.requestMultiplePermission.launch(arrayOf(*permissions))
    }
}