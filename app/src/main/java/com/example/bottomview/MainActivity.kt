package com.example.bottomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.bottomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.fragment)

        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(
                setOf(
                    R.id.messageFragment,
                    R.id.contactFragment,
                    R.id.exploreFragment,
                    R.id.accountFragment
                )
            )
        )

        binding.linearLayout[0].setOnClickListener {
            navController.navigate(R.id.messageFragment)
        }
        binding.linearLayout[1].setOnClickListener {
            navController.navigate(R.id.contactFragment)
        }
        binding.linearLayout[2].setOnClickListener {
            navController.navigate(R.id.exploreFragment)
        }
        binding.linearLayout[3].setOnClickListener {
            navController.navigate(R.id.accountFragment)
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            controller.popBackStack()
            (binding.linearLayout[0] as MotionLayout).progress = 0f
            (binding.linearLayout[1] as MotionLayout).progress = 0f
            (binding.linearLayout[2] as MotionLayout).progress = 0f
            (binding.linearLayout[3] as MotionLayout).progress = 0f
            when (destination.id) {
                R.id.messageFragment -> (binding.linearLayout[0] as MotionLayout).transitionToEnd()
                R.id.contactFragment -> (binding.linearLayout[1] as MotionLayout).transitionToEnd()
                R.id.exploreFragment -> (binding.linearLayout[2] as MotionLayout).transitionToEnd()
                else -> (binding.linearLayout[3] as MotionLayout).transitionToEnd()

            }

        }


    }
}