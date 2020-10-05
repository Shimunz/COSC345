package com.example.gossip

/**
 * Chat members that are in a group
 */
class ChatMembers() {

    var chatId : String? = null
    var members : List<String>? = null

    constructor(key: String?, memberArray: List<String>) : this() {
        this.chatId = chatId
        this.members = memberArray
    }
}