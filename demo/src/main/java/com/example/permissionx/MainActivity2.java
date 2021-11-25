package com.example.permissionx;

import android.Manifest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.permissionx.saltedfish.PermissionX;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private final Button requestPermissionBtn = findViewById(R.id.requestPermissionButton);
    private final Button requestPermissionsBtn = findViewById(R.id.requestPermissionsButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        requestPermissionBtn.setOnClickListener(v -> {
            //请求单个权限
            PermissionX.INSTANCE.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, b -> {
                if (b) {
                    //授予执行操作
                    Toast.makeText(this, "You granted permission", Toast.LENGTH_SHORT).show();
                } else {
                    //拒绝执行操作
                    Toast.makeText(this, "You denied granted permission", Toast.LENGTH_SHORT).show();
                }
                return null;
            });
        });
        requestPermissionsBtn.setOnClickListener(v ->
                //请求多个权限
                PermissionX.INSTANCE.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.CAMERA}, (map, b) -> {
                    //回调<权限名称，授予与否>集合以及是否全部授予
                    if (b) {
                        //权限全部授予执行的操作
                        Toast.makeText(this, "You granted all permissions", Toast.LENGTH_SHORT).show();
                    } else {
                        //否则遍历回调的<权限名称，授予与否>数组
                        for (String key : map.keySet()) {
                            if (!map.get(key)) {
                                //未授予的权限执行的操作 这里弹出未授予的权限名
                                Toast.makeText(this, "You denied " + Arrays.toString(key.split("\\.", 2)) + " permission", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    return null;
                }));
    }
}