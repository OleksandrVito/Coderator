package ua.vitolex.qrcodegenerator_qrcodescan

import android.app.Application
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ScannerApp:Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: ScannerApp? = null

        fun getUriPermission(uri: Uri){
            instance!!.applicationContext.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }
}