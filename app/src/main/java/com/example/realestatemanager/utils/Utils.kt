package com.example.realestatemanager.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class Utils {


    object Companion {

        /**
         * Conversion d'un prix d'un bien immobilier (Dollars vers Euros)
         * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
         * @param dollars
         * @return
         */
        fun convertDollarToEuro(dollars: Int): Int {
            return Math.round(dollars * 0.812).toInt()
        }


        /**
         * Conversion de la date d'aujourd'hui en un format plus approprié
         * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
         * @return
         */
        @SuppressLint("SimpleDateFormat")
        fun getTodayDate(): String? {
            val dateFormat: DateFormat = SimpleDateFormat("dd MMM, yyyy")
            return dateFormat.format(Date())
        }


        /**
         * Vérification de la connexion réseau
         * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
         * @param context
         * @return
         */
        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager =
                context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected

        }

        fun isWifiAvailable(context: Context): Boolean {
            val wifi =
                context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            return wifi.isWifiEnabled

        }

        fun validateEmail(email: String?): Boolean {
            val mail: CharSequence = email.toString()
            val matcher = pattern.matcher(mail)
            return matcher.matches()
        }

        private val EMAIL_PATTERN by lazy { "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$" }
        private val pattern = Pattern.compile(EMAIL_PATTERN)

    }
}