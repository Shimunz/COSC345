package com.example.gossip

/**
 * Messgaes
 * How messages are going to be stored
 */
class Messages {

    var name : String? = null
    var message : String? = null

    constructor(name: String?, message: String?){
        this.name = name
        this.message = message
    }
}