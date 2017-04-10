package com.islavstan

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import java.util.*


class Note : Model {
    @Column(name = "title")
    public var title: String? = null
    @Column(name = "text")
    public var text: String? = null
    @Column(name = "create_date")
    public var createDate: Date? = null
    @Column(name = "change_date")
    public var changeDate: Date? = null

    constructor()

    constructor(title: String, createDate: Date, changeDate: Date) {
        this.title = title
        this.createDate = createDate
        this.changeDate = changeDate
    }

    constructor(title: String, createDate: Date) {
        this.title = title
        this.createDate = createDate

    }

    fun getInfo(): String = "Название: $title" +
            "Время создания: $createDate" +
            "Время изменения: $changeDate"
}
