package com.yuanqihudong.task.viewmodel

sealed class TaskState {
    object BeforeLoad : TaskState()
    object DoLoad : TaskState()
    object AfterLoad : TaskState()

    data class GetArticle(val content: String) : TaskState()
    data class ErrorData(val msg: String): TaskState()
}

sealed class AllIntent {

    object GetArticle : AllIntent()
}