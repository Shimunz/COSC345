package com.example.gossip

/**
 * This class is for storing user information
 *
 * @property uid unquie identifier generated by firebase for each user
 * @property username username user has choosen
 * @constructor creates a constructor with uid and username
 */
class Users {

    var uid : String? = null
    var username : String? =null
    var email : String? = null

    constructor(uid: String?, username: String?, email : String?) {
        this.uid = uid
        this.username = username
        this.email = email
    }

    constructor()

}