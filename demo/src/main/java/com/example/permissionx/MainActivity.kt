package com.example.permissionx

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.permissionx.saltedfish.PermissionX

class MainActivity : AppCompatActivity() {
    private val requestPermissionsBtn: Button by lazy { findViewById(R.id.requestPermissionsBtn) }
    private val requestPermissionBtn: Button by lazy { findViewById(R.id.requestPermissionBtn) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissionBtn.setOnClickListener {
            //请求单个权限
            PermissionX.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) {
                //授予执行操作
                if (it) Toast.makeText(this, "You granted permission", Toast.LENGTH_SHORT).show()
                //拒绝执行操作
                else Toast.makeText(this, "You denied granted permission", Toast.LENGTH_SHORT).show()
            }
        }
        requestPermissionsBtn.setOnClickListener {
            //请求多个权限
            PermissionX.requestPermissions(
                this,
                //多个权限用，隔开
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.CAMERA
            ) { map, b ->
                //回调<权限名称，授予与否>集合以及是否全部授予
                if (b) {
                    //权限全部授予执行的操作
                    Toast.makeText(this, "You granted all permissions", Toast.LENGTH_SHORT).show()
                } else {
                    //否则遍历回调的<权限名称，授予与否>数组
                    map.entries.forEach { entry ->
                        if (!entry.value) {
                            //未授予的权限执行的操作 这里弹出未授予的权限名
                            Toast.makeText(this, "You denied ${entry.key.substringAfterLast(".")} permission", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}