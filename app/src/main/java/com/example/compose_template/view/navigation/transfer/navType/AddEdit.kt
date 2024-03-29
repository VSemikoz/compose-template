package com.example.compose_template.view.navigation.transfer.navType

import JsonNavType
import com.example.compose_template.view.model.TodoItemMinimalUi
import com.google.gson.Gson

class TodoMinimalNavType : JsonNavType<TodoItemMinimalUi?>() {
    override fun fromJsonParse(value: String): TodoItemMinimalUi? {
        return if (value.isEmpty()) null else Gson().fromJson(value, TodoItemMinimalUi::class.java)
    }

    override fun getJsonParse(value: TodoItemMinimalUi?): String {
        return Gson().toJson(value)
    }
}

