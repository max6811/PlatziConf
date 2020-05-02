package com.maxidevastronaut.conf.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.maxidevastronaut.conf.R
import kotlinx.android.synthetic.main.activity_splashscreen.*
import java.util.*

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val animacion = AnimationUtils.loadAnimation(this, R.anim.animacion)//cargamos la anim
        ivLogoPlatziConf.startAnimation(animacion)

        val intent = Intent(this, MainActivity::class.java)


        animacion.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(intent)
                finish()//la animacion se destruye
                //configurar el androidManifest
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })

    }
}
