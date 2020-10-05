package com.example.gossip

class Chats {

    val chatID : String? = null
    var title : String? = null
    var lastMessage : String? = null

    constructor(chatID:String?, title: String?, lastMessage: String?) {
        this.title = title
        this.lastMessage = lastMessage
    }
}