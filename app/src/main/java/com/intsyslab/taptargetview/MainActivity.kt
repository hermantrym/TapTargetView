package com.intsyslab.taptargetview

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import com.intsyslab.taptargetview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var prefEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("didShowPromt", MODE_PRIVATE)
        prefEditor = sharedPreferences.edit()

        showFabPromt()
    }

    private fun showFabPromt() {
        if (!sharedPreferences.getBoolean("didShowPromt", false))
            TapTargetView.showFor(
                this,
                TapTarget.forView(binding.btnFab, "Title for target", "description for target")
                    .tintTarget(false)
                    .outerCircleColor(R.color.purple_200)
                    .textColor(R.color.teal_700),
                object : TapTargetView.Listener() {
                    override fun onTargetClick(view: TapTargetView?) {
                        super.onTargetClick(view)
                        showImagePromt()
                    }
                }
            )
    }

    private fun showImagePromt() {
        TapTargetView.showFor(
            this,
            TapTarget.forView(binding.imgTarget, "Title for target imgTarget", "description for target")
                .tintTarget(false)
                .outerCircleColor(R.color.purple_200)
                .textColor(R.color.teal_700),
            object : TapTargetView.Listener() {
                override fun onTargetClick(view: TapTargetView?) {
                    super.onTargetClick(view)
                    prefEditor = sharedPreferences.edit()
                    prefEditor.putBoolean("didShowPromt", true)
                    prefEditor.apply()
                }
            }
        )
    }
}