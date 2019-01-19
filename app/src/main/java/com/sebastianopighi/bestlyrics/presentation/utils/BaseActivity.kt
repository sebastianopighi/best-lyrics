package com.sebastianopighi.bestlyrics.presentation.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import com.sebastianopighi.bestlyrics.R

abstract class BaseActivity : AppCompatActivity() {

    lateinit var progressBar: ProgressBar

    companion object {
        const val TRACK_ID = "track_id"
    }

    fun checkInternetConnection(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        activeNetwork?.let { networkInfo ->
            if(!networkInfo.isConnected) {
                showNoConnectionDialog()
            }
            return networkInfo.isConnected
        }
        showNoConnectionDialog()
        return false
    }

    fun showNoAPIKeyDialog() {
        progressBar.visibility = View.GONE
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.no_api_key_error_title))
        builder.setMessage(getString(R.string.no_api_key_error))
                .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
    }

    fun showNoConnectionDialog() {
        progressBar.visibility = View.GONE
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.no_connection_error_title))
        builder.setMessage(getString(R.string.no_connection_error))
                .setPositiveButton(getString(R.string.retry)) { dialog, _ ->
                    dialog.dismiss()
                    if (checkInternetConnection())
                        reloadData()
                }
                .setCancelable(false)
                .show()
    }

    fun showDialog(title: String, message:String, buttonTitle: String) {
        progressBar.visibility = View.GONE
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
                .setPositiveButton(buttonTitle) { dialog, _ ->
                    dialog.dismiss()
                    reloadData()
                }
                .setCancelable(false)
                .show()
    }

    abstract fun reloadData()

}