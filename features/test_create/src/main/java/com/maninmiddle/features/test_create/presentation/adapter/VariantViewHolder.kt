package com.maninmiddle.features.test_create.presentation.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.RecyclerView
import com.maninmiddle.features.test_create.R
import com.maninmiddle.features.test_create.databinding.VariantCreateItemBinding
import com.maninmiddle.features.test_create.domain.model.VariantModel
import java.util.Locale

class VariantViewHolder(
    private val binding: VariantCreateItemBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(variantModel: VariantModel, currentPosition: Int) {
        binding.variantsCreateItemText.hint = String.format(
            Locale.getDefault(),
            "%s %d",
            context.getString(R.string.stringVariant),
            currentPosition + 1
        )
        binding.etVariant.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                variantModel.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {}

        })
        binding.variantsCreateItemCheckbox.setOnCheckedChangeListener { _, isChecked ->
            variantModel.isCorrect = isChecked
        }
    }
}