package ua.vitolex.qrcodegenerator_qrcodescan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.hilt.android.AndroidEntryPoint
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.navigation.SetupNavHost
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.QRCodeGeneratorQRCodeScanTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scanner = GmsBarcodeScanning.getClient(this)
            QRCodeGeneratorQRCodeScanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetupNavHost(navController = navController)
                }
            }
        }
    }
}



