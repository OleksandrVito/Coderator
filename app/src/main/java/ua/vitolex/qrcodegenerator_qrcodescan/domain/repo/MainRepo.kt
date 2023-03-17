package ua.vitolex.qrcodegenerator_qrcodescan.domain.repo

import kotlinx.coroutines.flow.Flow

interface MainRepo {
    fun startScanning(): Flow<String?>
}