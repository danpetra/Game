package game

import java.security.SecureRandom
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class Step (val args:Array<String>) {
    private val secureRandom = SecureRandom()
    private val random = SecureRandom.getInstanceStrong()
    private val key = ByteArray(32) // 256 bit
    private val hMacSHA256 = Mac.getInstance("HmacSHA256")
    private val secretKey = SecretKeySpec(key, "HmacSHA256")
    init{
        random.nextBytes(key)
        hMacSHA256.init(secretKey)
    }
    fun getKey():String{
        return byteArrayToString(key)
    }
    fun getHmac(message:String = ""):String {
        var messageWithKey = message + byteArrayToString(key)
        val hashWithKey = hMacSHA256.doFinal(messageWithKey.toByteArray())
        return byteArrayToString(hashWithKey)
    }
    fun makeStep():Int{
        return secureRandom.nextInt(args.size)
    }
    private fun byteArrayToString(bytes:ByteArray):String{
        val sb = StringBuilder()
        bytes.forEach { sb.append(String.format("%02X", it)) }
        return sb.toString()
    }
}