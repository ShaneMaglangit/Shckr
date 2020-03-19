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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)
        val steps = listOf(
            Step("Draw a filled black square on your wrist using a marker.", R.drawable.ic_draw),
            Step("Remove your phone case.", R.drawable.ic_case),
            Step("Put your phone's flash module on top of the square.", R.drawable.ic_flash),
            Step("Ask a question to your friend.", R.drawable.ic_question),
            Step("Tap to start the detector.", R.drawable.ic_start)
        )
        val detectorIntent = Actions.openDetectorIntent(this).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        binding = ActivityInstructionBinding.inflate(layoutInflater)

        viewpager_steps.adapter = StepAdapter(this, steps)
        viewpager_steps.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if(position == steps.size - 1) button_next.text = "Done"
                else button_next.text = "Next"
                super.onPageSelected(position)
            }
        })

        button_skip.setOnClickListener {
            startActivity(detectorIntent)
        }

        button_next.setOnClickListener {
            if(viewpager_steps.currentItem != steps.size - 1) viewpager_steps.currentItem += 1
            else startActivity(detectorIntent)
        }

        TabLayoutMediator(tab_layout_steps, viewpager_steps) { _, _ ->}.attach()
    }

    private inner class StepAdapter(fa: FragmentActivity, val steps: List<Step>) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = steps.size
        override fun createFragment(position: Int): Fragment = StepFragment(steps[position])
    }
}
