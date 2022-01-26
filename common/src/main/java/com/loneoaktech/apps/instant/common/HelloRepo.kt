package com.loneoaktech.apps.instant.common

import android.content.Context
import com.google.android.gms.common.wrappers.InstantApps


class HelloRepo {

    fun getHello(context: Context) = "Hello from the common library,  instant=${InstantApps.isInstantApp(context)}"
}