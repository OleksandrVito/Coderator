package ua.vitolex.qrcodegenerator_qrcodescan.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor() : ViewModel() {

    private val _textResult = mutableStateOf("")
    val textResult: State<String> = _textResult

    val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_ALL_FORMATS
        )
        .build()
    val scanner = BarcodeScanning.getClient(options)

    fun extractBarcodeQrCodeInfo(barcodes: List<Barcode>) {
        if (barcodes.isEmpty())  _textResult.value = ""
        for (barcode in barcodes) {
            val bounds = barcode.boundingBox
            val corners = barcode.cornerPoints

            val rawValue = barcode.rawValue
            val valueType = barcode.valueType
            // See API reference for complete list of supported types
            when (valueType) {
                Barcode.TYPE_WIFI -> {
                    val ssid = barcode.wifi!!.ssid
                    val password = barcode.wifi!!.password
                    val type = barcode.wifi!!.encryptionType
                }
                Barcode.TYPE_URL -> {
                    val title = barcode.url!!.title
                    val url = barcode.url!!.url
                }
            }
            _textResult.value = rawValue ?: ""
        }
    }
}