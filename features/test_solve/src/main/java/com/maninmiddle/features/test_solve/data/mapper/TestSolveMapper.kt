package com.maninmiddle.features.test_solve.data.mapper

import com.maninmiddle.core.common.network.dto.TaskItemDto
import com.maninmiddle.core.common.network.dto.VariantItemDto
import com.maninmiddle.features.test_solve.domain.model.TaskModel
import com.maninmiddle.features.test_solve.domain.model.VariantModel

fun List<VariantItemDto>.toVariantModel(): List<VariantModel> {
    return this.map { variantItemDto ->
        VariantModel(
            id = variantItemDto.id,
            taskId = variantItemDto.taskId,
            text = variantItemDto.text,
            isCorrect = variantItemDto.isCorrect
        )
    }
}

fun List<TaskItemDto>.toTasksModel(): List<TaskModel> {
    return this.map { taskItemDto ->
        TaskModel(
            id = taskItemDto.id,
            testId = taskItemDto.testId,
            text = taskItemDto.text,
            type = taskItemDto.type,
            correctAnswer = taskItemDto.correctAnswer,
            variants = taskItemDto.variants?.toVariantModel() ?: emptyList()
        )
    }
}