package com.example.gossip

/**
 * Chat
 * How chats are going to be stored
 */
class Chats {

    var title : String? = null
    var lastMessage : String? = null
    var id : String? = null

    constructor(title: String?, lastMessage: String?, id: String?) {
        this.title = title
        this.lastMessage = lastMessage
        this.id = id
    }
    constructor()
}