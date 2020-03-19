package com.shanemaglangit.instruction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.shanemaglangit.actions.Actions
import com.shanemaglangit.instruction.databinding.ActivityInstructionBinding
import kotlinx.android.synthetic.main.activity_instruction.*

class InstructionActivity : FragmentActivity() {
    private lateinit var binding: ActivityInstructionBinding

    companion object {
        const val STEPS_COUNT = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)
        val detectorIntent = Actions.openDetectorIntent(this).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        binding = ActivityInstructionBinding.inflate(layoutInflater)

        viewpager_steps.adapter = StepAdapter(this)
        viewpager_steps.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if(position == STEPS_COUNT - 1) button_next.text = "Done"
                else button_next.text = "Next"
                super.onPageSelected(position)
            }
        })

        button_skip.setOnClickListener {
            startActivity(detectorIntent)
        }

        button_next.setOnClickListener {
            if(viewpager_steps.currentItem != STEPS_COUNT - 1) viewpager_steps.currentItem += 1
            else startActivity(detectorIntent)
        }

        TabLayoutMediator(tab_layout_steps, viewpager_steps) { _, _ ->}.attach()
    }

    private inner class StepAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = STEPS_COUNT
        override fun createFragment(position: Int): Fragment = StepFragment("Draw a square")
    }
}
