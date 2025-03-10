package com.maninmiddle.features.tests_list.data.mapper

import com.maninmiddle.core.common.network.dto.TestItemDto
import com.maninmiddle.features.tests_list.domain.model.TestItem

fun List<TestItemDto>.toListTestItem(): List<TestItem> {
    return this.map { testItemDto ->
        TestItem(
            id = testItemDto.id,
            name = testItemDto.name,
            subject = testItemDto.subject,
            completeTime = testItemDto.completeTime,
            password = testItemDto.password
        )
    }
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

fun TestItem.toTestItemDto(): TestItemDto {
    return TestItemDto(
        id = id,
        name = name,
        subject = subject,
        completeTime = completeTime,
        password = password
    )
}