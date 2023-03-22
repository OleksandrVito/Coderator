package ua.vitolex.qrcodegenerator_qrcodescan.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
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

    private val _typeResult = mutableStateOf("")
    val typeResult: State<String> = _typeResult

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_ALL_FORMATS
        )
        .build()
    val scanner = BarcodeScanning.getClient(options)

    fun extractBarcodeQrCodeInfo(barcodes: List<Barcode>) {
        if (barcodes.isEmpty()) _textResult.value = ""
        for (barcode in barcodes) {
            // See API reference for complete list of supported types
          return  when (barcode.valueType) {
                Barcode.TYPE_WIFI -> {
                    val ssid = barcode.wifi!!.ssid
                    val password = barcode.wifi!!.password
                    val type = barcode.wifi!!.encryptionType
                    _textResult.value = "ssid : $ssid, password : $password, type : $type"
                    _typeResult.value = "WiFi"
                }
                Barcode.TYPE_URL -> {
                    _textResult.value = "${barcode.url!!.url}"
                    _typeResult.value = "WebSite"
                }
                Barcode.TYPE_PRODUCT -> {
                    _textResult.value = "${barcode.displayValue}"
                    _typeResult.value = "Product type"
                }
                Barcode.TYPE_EMAIL -> {
                    _textResult.value = "${barcode.displayValue}"
                    _typeResult.value = "Email"
                }
                Barcode.TYPE_CONTACT_INFO -> {
                    _textResult.value = "${barcode.displayValue}"
                    _typeResult.value = "Contact"
                }
                Barcode.TYPE_PHONE -> {
                    _textResult.value = "${barcode.displayValue}"
                    _typeResult.value = "Phone"
                }
                Barcode.TYPE_CALENDAR_EVENT -> {
                    _textResult.value = "${barcode.displayValue}"
                    _typeResult.value = "Event"
                }
                Barcode.TYPE_GEO -> {
                    _textResult.value = "${barcode.displayValue}"
                    _typeResult.value = "Location"
                }
                Barcode.TYPE_ISBN -> {
                    _textResult.value = "${barcode.displayValue}"
                    _typeResult.value = "ISBN"
                }
                Barcode.TYPE_DRIVER_LICENSE -> {
                    _textResult.value = "${barcode.displayValue}"
                    _typeResult.value = "Driving license"
                }
                Barcode.TYPE_SMS -> {
                    _textResult.value = "${barcode.displayValue}"
                    _typeResult.value = "SMS"
                }
                Barcode.TYPE_TEXT -> {
                    _textResult.value = "${barcode.displayValue}"
                    _typeResult.value = "Text"
                }
                Barcode.TYPE_UNKNOWN -> {
                    _textResult.value = "${barcode.displayValue}"
                    _typeResult.value = "Unknown"
                }
                else -> {
                    _typeResult.value =  "Couldn't determine"
                }
            }
        }
    }
}