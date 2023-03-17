package ua.vitolex.qrcodegenerator_qrcodescan.presentation.screen


import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Paint.Align
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.common.InputImage
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.ScannerViewModel
import ua.vitolex.qrcodegenerator_qrcodescan.R
import ua.vitolex.qrcodegenerator_qrcodescan.ScannerApp
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.BarCodeAnalyser
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.navigation.Screens
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.*
import java.io.IOException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScanScreen(navController: NavHostController, viewModel: ScannerViewModel = hiltViewModel()) {
    val context = LocalContext.current
    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    val textResult = viewModel.textResult

    //для картинки
    val getImageRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            if (uri != null) {
                try {
                    val image = InputImage.fromFilePath(context, uri)
                    viewModel.scanner.process(image)
                        .addOnSuccessListener { barcodes ->
                            if (barcodes.isEmpty()) showToast("QR code or barcode is not found.")
                            viewModel.extractBarcodeQrCodeInfo(barcodes)
                        }
                        .addOnFailureListener { e ->
                            showToast("Failed scanning due to ${e.message}")
                        }
                } catch (e: IOException) {
                    e.printStackTrace()
                    showToast("Failed due to ${e.message}")
                }
            }
        }
    )
    var cameraOpen by remember { mutableStateOf(false) }
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val lifecycleOwner = LocalLifecycleOwner.current
    var preview by remember { mutableStateOf<Preview?>(null) }
    val barCodeVal = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                backgroundColor = Black333
            ) {
                Column(
                    Modifier
                        .padding(top = 15.dp, bottom = 0.dp, start = 5.dp)
                ) {
                    Text(text = "CODERATOR", color = WhiteGrey)
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .background(Silver)
                    )
                    Row(
                        Modifier
                            .height(30.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .height(30.dp)
                                .padding(bottom = 15.dp, top = 0.dp)
                                .background(Color.Transparent)
                        ) {
                            Canvas(modifier = Modifier
                                .width(50.dp)
                                .height(30.dp), onDraw = {
                                val trianglePath = Path().apply {
                                    moveTo(2.dp.toPx(), 15.dp.toPx())
                                    lineTo(30.dp.toPx(), 11.dp.toPx())
                                    lineTo(30.dp.toPx(), 19.dp.toPx())
                                }
                                drawPath(
                                    color = DirtyRed,
                                    path = trianglePath
                                )
                            })
                        }
                        Text(
                            text = "back",
                            fontSize = 10.sp,
                            fontFamily = presstart,
                            color = WhiteGrey,
                        )
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Black333)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Black333)
                .padding(vertical = 10.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (cameraOpen) {
                AndroidView(
                    factory = { AndroidViewContext ->
                        PreviewView(AndroidViewContext).apply {
                            this.scaleType = PreviewView.ScaleType.FILL_CENTER
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                            )
                            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(10.dp)
                        .clip(shape = RoundedCornerShape(30.dp)),
                    update = { previewView ->
                        val cameraSelector: CameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()
                        val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
                        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
                            ProcessCameraProvider.getInstance(context)

                        cameraProviderFuture.addListener({
                            preview = Preview.Builder().build().also {
                                it.setSurfaceProvider(previewView.surfaceProvider)
                            }
                            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                            val barcodeAnalyser = BarCodeAnalyser { barcodes ->
                                viewModel.extractBarcodeQrCodeInfo(barcodes)
                                    .also { cameraOpen = false }
                            }
                            val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build()
                                .also {
                                    it.setAnalyzer(cameraExecutor, barcodeAnalyser)
                                }

                            try {
                                cameraProvider.unbindAll()
                                cameraProvider.bindToLifecycle(
                                    lifecycleOwner,
                                    cameraSelector,
                                    preview,
                                    imageAnalysis
                                )
                            } catch (e: Exception) {
                                Log.d("TAG", "CameraPreview: ${e.localizedMessage}")
                            }
                        }, ContextCompat.getMainExecutor(context))
                    }
                )
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(10.dp)
                        .border(3.dp, Silver, shape = RoundedCornerShape(30.dp))
                        .padding(vertical = 25.dp, horizontal = 18.dp)
                        .border(2.dp, Silver, shape = RoundedCornerShape(25.dp))
                        .padding(8.dp)
                        .clip(shape = RoundedCornerShape(20.dp)),
                    backgroundColor = PrimaryGray,
                    contentColor = Dark,
                    elevation = 5.dp
                ) {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = textResult.value,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
                    Text(
                        text = "Start scanning from image or using camera",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(20.dp),
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Center,
                        color = WhiteGrey
                    )
            Row(
                modifier = Modifier
                    .width(262.dp)
                    .height(110.dp)
                    .background(Black333)
                    .border(3.dp, Silver, shape = RoundedCornerShape(12.dp))
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(PrimaryGray)
                        .width(120.dp)
                        .height(100.dp)
                        .clickable {
                            getImageRequest.launch(arrayOf("image/*"))
                            cameraOpen = false
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image_100),
                        contentDescription = "Camera",
                        modifier = Modifier
                            .scale(2.5f)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    modifier = Modifier
                        .background( if (cameraOpen) SecondaryGray else PrimaryGray)
                        .width(120.dp)
                        .height(100.dp)
                        .clickable {
                            cameraPermissionState.launchPermissionRequest()
                            cameraOpen = !cameraOpen
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.camera_100),
                        contentDescription = "Camera",
                        modifier = Modifier
                            .scale(2.5f)
                    )
                }
            }

        }
    }
}
