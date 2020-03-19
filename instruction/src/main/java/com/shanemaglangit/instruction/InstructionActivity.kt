package com.shanemaglangit.instruction

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
        binding = ActivityInstructionBinding.inflate(layoutInflater)

        viewpager_steps.adapter = StepAdapter(this)

        button_skip.setOnClickListener {
            startActivity(Actions.openDetectorIntent(this))
        }

        TabLayoutMediator(tab_layout_steps, viewpager_steps) { _, _ ->}.attach()
    }

    private inner class StepAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3
        override fun createFragment(position: Int): Fragment = StepFragment("Draw a square")
    }
}
