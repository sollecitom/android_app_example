package com.example.myfirstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Same as `findViewById<Button>(R.id.random_button)`
        view.random_button.setOnClickListener {
            val currentCount = view.getTextViewCurrentCount()
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount)
            findNavController().navigate(action)
        }

        view.toast_button.setOnClickListener {

            val toastTextPrefix = getText(R.string.toast_button_toast_text)
            val toast = Toast.makeText(context, "$toastTextPrefix: ${view.getTextViewCurrentCount()}", Toast.LENGTH_SHORT)
            toast.show()
        }

        view.count_button.setOnClickListener {
            view.updateTextViewCounter(Int::inc)
        }
    }

    private fun View.updateTextViewCounter(update: (Int) -> Int) {

        val current = getTextViewCurrentCount()
        val updated = update.invoke(current)
        textview_first.text = updated.toString()
    }

    private fun View.getTextViewCurrentCount(): Int = textview_first.text.toString().toInt()
}
