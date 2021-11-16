package org.sopt.clonegenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.sopt.clonegenie.databinding.ActivityMainBinding
import org.sopt.clonegenie.detail.ui.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val homeFragment = HomeFragment()
    val forYouFragment = ForYouFragment()
    val searchFragment = SearchFragment()
    val myMusicFragment = MyMusicFragment()
    val wholeMenuFragment = WholeMenuFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initFragment()
        setHomeBnv()
    }


    fun initFragment() {
        supportFragmentManager.beginTransaction().add(R.id.fcv_home_container, myMusicFragment)
            .commit()

        binding.bnvHome.menu.getItem(MY_MUSIC_POSITION).isChecked = true
    }

    fun setHomeBnv() {
        binding.bnvHome.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()


            when (it.itemId) {
                R.id.menu_home -> {
                    transaction.replace(R.id.fcv_home_container, homeFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_for_you -> {
                    transaction.replace(R.id.fcv_home_container, forYouFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_search -> {
                    transaction.replace(R.id.fcv_home_container, searchFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_my_music -> {
                    transaction.replace(R.id.fcv_home_container, myMusicFragment).commit()
                    return@setOnItemSelectedListener true
                }
                else -> {
                    transaction.replace(R.id.fcv_home_container, wholeMenuFragment).commit()
                    return@setOnItemSelectedListener true
                }
            }
        }
    }


    companion object {
        const val MY_MUSIC_POSITION = 3
    }
}