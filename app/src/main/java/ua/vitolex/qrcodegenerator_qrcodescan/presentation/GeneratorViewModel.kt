package ua.vitolex.qrcodegenerator_qrcodescan.presentation

import android.content.ContentValues
import android.graphics.Bitmap
import android.util.Log
import androidmads.library.qrgenearator.QRGEncoder
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.google.zxing.WriterException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratorViewModel @Inject constructor() : ViewModel() {

    private val _mapBitmap = mutableStateOf<Bitmap?>(null)
    val mapBitmap: State<Bitmap?> = _mapBitmap

    fun startGeneration(inputValue: String, type: String) {
//        bundleOf(Pair("name","Sanja"), Pair( "postal", "7585"), Pair("data","urlecscscs"))
        val qrGenerator = QRGEncoder(inputValue, null, type, 900)
        try {
            // Getting QR-Code as Bitmap

            val bitmap = qrGenerator.getBitmap(1)
            _mapBitmap.value = bitmap
        } catch (e: WriterException) {
            Log.v(ContentValues.TAG, e.toString())
        }
    }
}