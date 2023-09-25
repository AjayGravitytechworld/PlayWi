package com.abc.demo.model

class aaIncome {
    var iD = 0
    var name: String? = null
    var phoneNumber: String? = null

    constructor()
    constructor(id: Int, name: String?, _phone_number: String?) {
        iD = id
        this.name = name
        phoneNumber = _phone_number
    }

    constructor(name: String?, _phone_number: String?) {
        this.name = name
        phoneNumber = _phone_number
    }
}