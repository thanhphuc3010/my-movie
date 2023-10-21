package com.phucpt.mymovie.presentation.ui

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.phucpt.mymovie.codebase.BaseFragment
import com.phucpt.mymovie.databinding.FragmentMovieDetailBinding
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Encoders
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.util.Date
import javax.crypto.Cipher

/**
 * Created by Phucpt on 16/07/2023 at 15:40
 */

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding, ViewModel>(FragmentMovieDetailBinding::inflate) {
    private lateinit var keyStore: KeyStore
    private lateinit var keyPair: KeyPair

    override val viewModel: ViewModel by viewModels()

    override fun initializeComponents() {
        initKeyStore()
    }

    override fun initializeEvents() {
        binding.btnGenKey.setOnClickListener {
            val alias = binding.edtAlias.text.toString()
            createKey(alias)
            binding.tvPublicKey.text = getKeyInfo(alias)
        }

        binding.btnEncrypt.setOnClickListener {
            val alias = binding.edtAlias.text.toString()
            encryptString(binding.edtInput.text.toString(), alias)
        }

        binding.btnDecrypt.setOnClickListener {
            val alias = binding.edtAlias.text.toString()
            decryptString(binding.tvEncrypt.text.toString(), alias)
        }

        binding.btnGetAlias.setOnClickListener {
            generateJwt()
        }
    }

    override fun initializeObservables() {

    }

    override fun initializeData() {

    }

    private fun initKeyStore() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore.load(null)
        } catch (e: Exception) {
            Log.d(javaClass.simpleName, "initKeyStore: ${e.message}")
        }
    }

    private fun createKey(alias: String) {
        try {
            if (!keyStore.containsAlias(alias)) {
                val keyPairGenerator = KeyPairGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_RSA,
                    "AndroidKeyStore"
                )
                val parameterSpec = KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                ).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                    .setDigests(KeyProperties.DIGEST_SHA1)
                    .build()
                keyPairGenerator.initialize(parameterSpec)
                keyPair = keyPairGenerator.genKeyPair()
            } else {
                Toast.makeText(requireContext(), "Alias exist!!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.d(javaClass.simpleName, "createKey: ${e.message}")
        }
    }

    private fun getKeyInfo(alias: String): String {
        val cert = keyStore.getCertificate(alias)
        val publicKey = cert.publicKey

        val publicKeyBytes: ByteArray = Base64.encode(publicKey.encoded, Base64.DEFAULT)
        val pubKeyString = String(publicKeyBytes)
        Log.d(javaClass.simpleName, "------------>${pubKeyString} --- $")
        return pubKeyString
    }

    private fun encryptString(clearText: String, alias: String) {
        val publicKey = keyStore.getCertificate(alias).publicKey
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val cipherText = cipher.doFinal(clearText.toByteArray(Charsets.UTF_8))
        binding.tvEncrypt.text = Base64.encodeToString(cipherText, Base64.DEFAULT)
    }

    private fun decryptString(cipherText: String, alias: String) {
        val privateKeyEntry = keyStore.getEntry(alias, null) as KeyStore.PrivateKeyEntry
        val privateKey = privateKeyEntry.privateKey
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val decryptText = cipher.doFinal(Base64.decode(cipherText, Base64.DEFAULT))
        binding.tvDecrypt.text = String(decryptText)
    }

    private fun generateJwt() {
        val alg = Jwts.SIG.RS512
        val keyPair = alg.keyPair().build()
        val jwt = Jwts.builder()
            .issuer("omni")
            .setAudience("aud")
            .issuedAt(Date())
            .signWith(keyPair.private, alg)
            .compact()

        Jwts.header()

        val publicKey = Encoders.BASE64.encode(keyPair.public.encoded)
        val privateKey = Encoders.BASE64.encode(keyPair.private.encoded)
        Log.d(javaClass.simpleName, "Public key: $publicKey")
        Log.d(javaClass.simpleName, "Private key: $privateKey")
        Log.d(javaClass.simpleName, "jwt: $jwt")
    }
}