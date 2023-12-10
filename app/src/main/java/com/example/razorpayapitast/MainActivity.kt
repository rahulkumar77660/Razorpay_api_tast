package com.example.razorpayapitast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment(HomeFragment())
        val bottomNavigationVoid  : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationVoid.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home->{
                    changeFragment(HomeFragment())
                }
                R.id.item->{
                    changeFragment(ItemFragment())
                }
                R.id.customer->{changeFragment(CustomerFragment())
                }
            }
            true
        }

    }

    fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit()
    }
}