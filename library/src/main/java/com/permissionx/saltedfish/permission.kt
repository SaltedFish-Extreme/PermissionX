package com.permissionx.saltedfish

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

/**
 * Created by 咸鱼至尊 on 2021/11/24
 *
 * des: Fragment权限申请扩展方法
 */
//单个权限申请结果的回调通知方式别名
internal typealias PermissionCallback = (Boolean) -> Unit
//多个权限申请结果的回调通知方式别名
internal typealias PermissionsCallback = (Map<String, Boolean>, Boolean) -> Unit

//请求权限是否全部授予
private var allGranted = true

//拒绝授权列表
private var deniedList = ArrayList<String>()

/**
 * 申请单个权限
 *
 * @param block 权限申请结果的回调通知方式
 * @return 启动器对象
 */
internal inline fun Fragment.requestPermission(crossinline block: (Boolean) -> Unit): ActivityResultLauncher<String> {
    return registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { result ->
        block(result)
    }
}

/**
 * 申请多个权限
 *
 * @param block 权限申请结果的回调通知方式
 * @return 启动器对象
 */
internal inline fun Fragment.requestMultiplePermission(crossinline block: PermissionsCallback): ActivityResultLauncher<Array<String>> {
    return registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    )
    { result ->
        //遍历权限申请结果集合
        result.entries.forEach {
            if (!it.value) {
                //拒绝授权的权限添加进列表
                deniedList.add(it.key)
            }
        }
        //列表为空则说明请求权限全部授予，否则未全部授予
        allGranted = deniedList.isEmpty()
        //结果回调
        block(result, allGranted)
        //最后要把拒绝授权集合清空，否则影响下次遍历
        deniedList.clear()
    }
}