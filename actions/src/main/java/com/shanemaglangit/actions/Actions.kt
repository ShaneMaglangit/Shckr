package com.shanemaglangit.actions

import android.content.Context
import android.content.Intent

object Actions {
    fun openDetectorIntent(context: Context) = internalIntent(context, "com.shanemaglangit.detector.open")

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)
}