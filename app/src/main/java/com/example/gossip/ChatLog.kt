package com.example.gossip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_log_from_row.view.*

class ChatLog : AppCompatActivity() {

    private var messageList = mutableListOf<String>()
    private var userMessage = ""

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        val name = intent.getStringExtra("USERNAME")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        supportActionBar?.title = name

        rv_chat_log.layoutManager = LinearLayoutManager(this)
        rv_chat_log.adapter = ChatLogAdapter(messageList)

        button_send.setOnClickListener(clickListener)

    }

    /**
     * Actions for what the onClickListeners do. E.g: sets the send button actions
     * in the chat log activity.
     */
    private val clickListener: View.OnClickListener = View.OnClickListener {
        when (it?.id) {
            R.id.button_send-> {
                userMessage = editText_message.text.toString()
                messageList.add(userMessage)
                (rv_chat_log.adapter as ChatLogAdapter).notifyItemInserted(messageList.size)
            }
        }
    }

}

/**
 * Recyclerview. Used to display a group of items efficiently
 */
class ChatLogAdapter(private val chat: MutableList<String>) :
    RecyclerView.Adapter<ChatLogAdapter.ChatLogViewHolder>() {

    class ChatLogViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        // TODO: Do something here
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int) : ChatLogAdapter.ChatLogViewHolder {

        val textView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.chat_log_to_row, parent, false)

        return ChatLogViewHolder(textView)

    }


    override fun getItemCount(): Int {
        return chat.size
    }

    override fun onBindViewHolder(holder: ChatLogAdapter.ChatLogViewHolder, position: Int) {
        holder.itemView.textView_chat_log.text = chat[position]
    }

}