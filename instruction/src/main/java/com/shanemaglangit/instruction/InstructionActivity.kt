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
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.shanemaglangit.instruction.databinding.ActivityInstructionBinding
import kotlinx.android.synthetic.main.activity_instruction.*

class InstructionActivity : FragmentActivity() {
    private lateinit var binding: ActivityInstructionBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)
        binding = ActivityInstructionBinding.inflate(layoutInflater)
        viewPager = findViewById(R.id.viewpager_steps)
        viewPager.adapter = StepAdapter(this)
    }

    private inner class StepAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3
        override fun createFragment(position: Int): Fragment = StepFragment("Draw a square")
    }
}
