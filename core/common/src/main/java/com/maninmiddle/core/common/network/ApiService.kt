package com.maninmiddle.core.common.network

import com.maninmiddle.core.common.network.dto.StatsItemDto
import com.maninmiddle.core.common.network.dto.TaskItemDto
import com.maninmiddle.core.common.network.dto.TestItemDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("tests")
    suspend fun getTests(): List<TestItemDto>

    @GET("tests/{testId}/tasks")
    suspend fun getTasksByTestId(@Path("testId") testId: Int): List<TaskItemDto>

    @GET("generate")
    suspend fun generateTasks(@Query("text") text: String): List<TaskItemDto>

    @POST("tests")
    suspend fun createTest(@Body testItemDto: TestItemDto): TestItemDto

    @POST("tasks")
    suspend fun createTasks(@Body tasks: List<TaskItemDto>)


    @POST("stats")
    suspend fun createStat(@Body stat: StatsItemDto)


    @GET("stats")
    suspend fun getStats(
        @Query("testId") testId: Int? = null
    ): List<StatsItemDto>
}