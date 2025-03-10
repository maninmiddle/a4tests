package com.maninmiddle.features.test_create.presentation.adapter

import com.maninmiddle.features.test_create.domain.model.VariantModel

interface OnTaskCreateListener {
    fun onTaskTextChanged(taskIdx: Int, newText: String) // Обновление текста вопроса
    fun onVariantChanged(taskIdx: Int, variants: MutableList<VariantModel>) // Обновление вариантов
    fun onVariantTextChanged(taskIdx: Int, variantIdx: Int, newText: String) // Обновление текста варианта
    fun onVariantCorrectnessChanged(taskIdx: Int, variantIdx: Int, isCorrect: Boolean) // Обновление правильности варианта
    fun onQuestionAdded() // Добавление нового вопроса
}