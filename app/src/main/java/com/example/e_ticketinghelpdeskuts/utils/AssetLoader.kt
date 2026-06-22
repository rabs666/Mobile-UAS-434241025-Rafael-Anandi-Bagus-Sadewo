package com.example.e_ticketinghelpdeskuts.utils

import android.content.Context
import org.json.JSONObject

/**
 * Modul 8 - Assets dan Media.
 *
 * Padanan native Android dari Flutter `rootBundle.loadString('assets/config.json')`.
 * Di Android, file statis (JSON, konfigurasi, dsb.) diletakkan pada folder
 * `app/src/main/assets/` lalu dibaca lewat [Context.getAssets].
 *
 * Contoh pemakaian:
 * ```
 * val config = AssetLoader.loadConfig(context)
 * val appName = config.getJSONObject("app").getString("name")
 * ```
 */
object AssetLoader {

    /** Membaca isi file pada folder assets sebagai String (mirip rootBundle.loadString). */
    fun loadString(context: Context, fileName: String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }

    /** Membaca file JSON pada assets dan mem-parse-nya menjadi [JSONObject]. */
    fun loadJson(context: Context, fileName: String): JSONObject =
        JSONObject(loadString(context, fileName))

    /** Helper khusus: memuat `config.json` aplikasi. */
    fun loadConfig(context: Context): JSONObject = loadJson(context, "config.json")
}
