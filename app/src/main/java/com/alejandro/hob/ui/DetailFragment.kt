package com.alejandro.hob.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.alejandro.hob.R
import com.alejandro.hob.data.remote.Brastlewark
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 *
 */
class DetailFragment : DialogFragment() {

    private lateinit var brastlewark: Brastlewark

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load(brastlewark.thumbnail)
            .into(image)

        name.text = brastlewark.name
        age.text = getString(R.string.age_format, brastlewark.age)
        weight.text = getString(R.string.weight_format, String.format("%.2f", brastlewark.weight))
        height.text = getString(R.string.height_format, String.format("%.2f", brastlewark.height))
        hair.text = brastlewark.hairColor
        professions.text = printArray(brastlewark.professions)
        friends.text = printArray(brastlewark.friends)
    }

    private fun printArray(professions: List<String>): String {
        var string = ""
        var counter = 0

        for (i in professions) {
            if (counter > 1){
                string += "$i\n"
                counter = 0
            } else {
                string += "$i, "
                counter++
            }
        }
        return string
    }

    fun setData(brastlewark: Brastlewark) {
        this.brastlewark = brastlewark
    }

}
