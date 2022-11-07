package com.venturus.minimal.binder.consumer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.venturus.minimal.binder.IMyService

class MainActivity : AppCompatActivity(), ServiceConnection {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent().apply {
            component = ComponentName(
                "com.venturus.minimal.binder",
                "com.venturus.minimal.binder.MyService"
            )
        }
        bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
        IMyService.Stub.asInterface(p1)?.apply(IMyService::showAToast)?.also {
            unbindService(this)
        }
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        println(p0)
    }
}