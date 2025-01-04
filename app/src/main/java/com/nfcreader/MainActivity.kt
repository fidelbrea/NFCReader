package com.nfcreader

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null

    // Layout elements
    private lateinit var nfcStatusText: TextView
    private lateinit var tagIdText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind layout elements
        nfcStatusText = findViewById(R.id.nfcStatusText)
        tagIdText = findViewById(R.id.tagIdText)

        // Initialize the NFC adapter
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        // Set the initial status of NFC
        if (nfcAdapter == null) {
            nfcStatusText.text = getString(R.string.nfc_not_supported)
            Toast.makeText(this, getString(R.string.nfc_not_supported_toast), Toast.LENGTH_LONG).show()
            return
        } else if (!nfcAdapter!!.isEnabled) {
            nfcStatusText.text = getString(R.string.nfc_disabled)
            Toast.makeText(this, getString(R.string.nfc_disabled_toast), Toast.LENGTH_LONG).show()
            return
        } else {
            nfcStatusText.text = getString(R.string.nfc_enabled)
        }

        // Configure the pending intent for NFC foreground dispatch
        pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_MUTABLE
        )
    }

    override fun onResume() {
        super.onResume()
        // Enable NFC foreground dispatch
        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    override fun onPause() {
        super.onPause()
        // Disable NFC foreground dispatch
        nfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        // Detect NFC tag
        if (NfcAdapter.ACTION_TAG_DISCOVERED == intent?.action) {
            val tag: Tag? = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(NfcAdapter.EXTRA_TAG, Tag::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            }
            val tagId = tag?.id?.joinToString(separator = "") { String.format("%02X", it) }
            if (tagId != null) {
                tagIdText.text = getString(R.string.tag_id_detected, tagId)
                Toast.makeText(this, getString(R.string.tag_detected_toast, tagId), Toast.LENGTH_LONG).show()
            } else {
                tagIdText.text = getString(R.string.tag_not_detected)
                Toast.makeText(this, getString(R.string.tag_not_detected_toast), Toast.LENGTH_LONG).show()
            }
        }
    }
}
