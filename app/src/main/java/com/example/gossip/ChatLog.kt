package com.example.gossip

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.chat_log_from_row.view.*
import kotlinx.android.synthetic.main.chat_log_to_row.view.*
import kotlinx.android.synthetic.main.chat_log_to_row.view.textView_chat_log_to

/**
 * Chat log. This is where the user will send and recieve chat information
 */
class ChatLog : AppCompatActivity() {

    private var messageList = mutableListOf<Messages>()
    private var userMessage = ""
    private val currentuid = FirebaseAuth.getInstance().uid
    //private var chatName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        val name = intent.getStringExtra("NAME")
        val chatKey = intent.getStringExtra("CHATKEY")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        getChatName()

        rv_chat_log.layoutManager = LinearLayoutManager(this)

        getFirebaseDatabaseMessages(chatKey, false)
        button_send.setOnClickListener(clickListener)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_chat_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.button_rename_chat -> {
                val intent = Intent(this, NewMessagesActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getChatName() {
        val idChat = intent.getStringExtra("CHATKEY")
        val ref = FirebaseDatabase.getInstance().getReference("/chats/")

        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach{
                    if (it.key == idChat){
                        val chatItem = it.getValue(Chats::class.java)
                        if (chatItem != null) {
                            supportActionBar?.title = chatItem.title.toString()
                        }
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    //Actions for what the onClickListeners do. E.g: sets the send button actions
    //in the chat log activity.
    private val clickListener: View.OnClickListener = View.OnClickListener {
        when (it?.id) {
            R.id.button_send-> {
                userMessage = editText_message.text.toString()

                if (!userMessage.isNullOrEmpty()) {
                    sendToFirebaseDatabase(userMessage)
                }
            }
        }
    }

    private fun sendToFirebaseDatabase(userMessage: String) {
        val idChat = intent.getStringExtra("CHATKEY")
        val key: String? = FirebaseDatabase.getInstance().getReference("/messages/$idChat").push().key
        val user = FirebaseAuth.getInstance().currentUser
        val mID = FirebaseDatabase.getInstance().getReference("/messages/$idChat")

        val message = Messages(currentuid, userMessage)

        if (key != null) {
            mID.ref.push().setValue(message)
            getFirebaseDatabaseMessages(idChat, true)
            editText_message.setText("")

        }
    }

    private fun getFirebaseDatabaseMessages(idChat : String, t: Boolean){
        val database = FirebaseDatabase.getInstance().getReference("/messages/$idChat")

        database.addValueEventListener (object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val messagesList = mutableListOf<Messages>()
                p0.children.forEach{
                    val messageItem = it.getValue(Messages::class.java)
                    if (messageItem != null) {
                        messagesList.add(messageItem)
                    }
                }
                messageList = messagesList

                if (t){
                    rv_chat_log.adapter = ChatLogAdapter(messageList)
                }else{
                    rv_chat_log.adapter = ChatLogAdapter(messageList)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })

    }

}

/**
 * Recyclerview. Used to display a group of items efficiently
 */
class ChatLogAdapter(private val chat: MutableList<Messages>) :
    RecyclerView.Adapter<ChatLogAdapter.ChatLogViewHolder>() {

    private val VIEW_TYPE_ME = 1
    private val VIEW_TYPE_OTHER = 2

    inner class ChatLogViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        // TODO: Do something here
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int) : ChatLogViewHolder {
        if (viewType == VIEW_TYPE_ME){
            val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_log_to_row, parent, false)

            return ChatLogViewHolder(textView)
        }else{
            val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_log_from_row, parent, false)

            return ChatLogViewHolder(textView)
        }


    }


    override fun getItemCount(): Int {
        return chat.size
    }

    private fun checkPerson(personID: String): Boolean {
        val pID = FirebaseAuth.getInstance().uid
        return personID == pID
    }

    override fun onBindViewHolder(holder: ChatLogAdapter.ChatLogViewHolder, position: Int) {
        val temp = chat[position]
        if (checkPerson(temp.name.toString())){
            holder.itemView.textView_chat_log_to.text = chat[position].message
        }else{
            holder.itemView.textView_chat_log_from.text = chat[position].message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val temp = chat[position]
        return if (checkPerson(temp.name.toString())){
            VIEW_TYPE_ME
        }else{
            VIEW_TYPE_OTHER
        }
    }

}