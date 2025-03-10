package com.maninmiddle.features.test_create.data.mapper

import com.maninmiddle.core.common.network.dto.TaskItemDto
import com.maninmiddle.core.common.network.dto.TestItemDto
import com.maninmiddle.core.common.network.dto.VariantItemDto
import com.maninmiddle.features.test_create.domain.model.TaskModel
import com.maninmiddle.features.test_create.domain.model.TestItem
import com.maninmiddle.features.test_create.domain.model.VariantModel

fun TestItem.toTestItemDto(): TestItemDto {
    return TestItemDto(
        id = id,
        name = name,
        subject = subject,
        completeTime = completeTime,
        password = password
    )
}


fun TestItemDto.toTestItem(): TestItem {
    return TestItem(
        id = id,
        name = name,
        subject = subject,
        completeTime = completeTime,
        password = password
    )
}


fun List<TaskItemDto>.toListTaskItem(): List<TaskModel> {
    return this.map { taskDto ->
        TaskModel(
            id = taskDto.id,
            testId = taskDto.testId,
            type = taskDto.type,
            text = taskDto.text,
            variants = taskDto.variants!!.toListVariantModel(),
            correctAnswer = taskDto.correctAnswer
        )
    }
}

fun List<TaskModel>.toListTaskItemDto(): List<TaskItemDto> {
    return this.map { taskDto ->
        TaskItemDto(
            id = taskDto.id,
            testId = taskDto.testId,
            type = taskDto.type,
            text = taskDto.text,
            variants = taskDto.variants.toListVariantModelDto(),
            correctAnswer = taskDto.correctAnswer!!
        )
    }
}


fun List<VariantItemDto>.toListVariantModel(): MutableList<VariantModel> {
    return this.map { variantDto ->
        VariantModel(
            id = variantDto.id,
            taskId = variantDto.taskId,
            text = variantDto.text,
            isCorrect = variantDto.isCorrect
        )
    }.toMutableList()
}

fun List<VariantModel>.toListVariantModelDto(): MutableList<VariantItemDto> {
    return this.map { variantDto ->
        VariantItemDto(
            id = variantDto.id,
            taskId = variantDto.taskId,
            text = variantDto.text,
            isCorrect = variantDto.isCorrect
        )
    }.toMutableList()
}

