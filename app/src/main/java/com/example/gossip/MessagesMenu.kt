package com.example.gossip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_messages_menu.*
import kotlinx.android.synthetic.main.user_list.view.*

/**
 * Main messages page
 * Has new message in sign out buttons
 */
class MessagesMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages_menu)

        supportActionBar?.title = "Chats"

        rv_messages_menu.layoutManager = LinearLayoutManager(this)
        getChats()
    }

    override fun onStart() {
        super.onStart()
        getUserData()
    }

    //Creates a menu inflater
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    //Shows the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.button_new_message -> {
                val intent = Intent(this, NewMessagesActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.button_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, MainMenu::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getUserData() {
        val userAuth = FirebaseAuth.getInstance().currentUser

        if (userAuth != null) {
            FirebaseInstanceId.getInstance().instanceId.
                    addOnCompleteListener(OnCompleteListener {
                        if(!it.isSuccessful) {
                            Log.w("getInstanceID failed", it.exception)
                            return@OnCompleteListener
                        }

                        val token = it.result?.token

                        Log.d("getInstanceID", token)
                    })
        } else {
            Log.d("Current_User", "Auth failed")
            val intent = Intent(this, MainMenu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun getChats(){
        val database = FirebaseDatabase.getInstance().getReference("user/${FirebaseAuth.getInstance().uid}/chats")

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val chatGroups = mutableListOf<String>()
                p0.children.forEach{
                    var userGroup = it.getValue(String::class.java)
                    if (userGroup != null) {
                        chatGroups.add(userGroup)
                    }
                }

                loadChats(chatGroups)
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    private fun loadChats(chatGroups: MutableList<String>){
        val database = FirebaseDatabase.getInstance().getReference("chats")

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val chatGroups = mutableListOf<Chats>()
                p0.children.forEach{
                    var userGroup = it.getValue(Chats::class.java)
                    if (userGroup != null) {
                        chatGroups.add(userGroup)
                    }
                }

                rv_messages_menu.adapter = MessageMenuAdaptor(chatGroups)
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}

/**
 * The recyclerview adaptor for messages menu
 * Reuses some things from the new messages recycler view
 */
class MessageMenuAdaptor(private val myDataset: MutableList<Chats>) :
    RecyclerView.Adapter<MessageMenuAdaptor.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ChatLog::class.java)
                val pos = it.textView_userlist_selectPos.text.toString().toInt()
                val idChat = myDataset[pos].id
                intent.putExtra("USERNAME", "temp")
                intent.putExtra("USERID", "id")
                intent.putExtra("CHATKEY", idChat)
                itemView.context.startActivity(intent)
            }
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MessageMenuAdaptor.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        var i = myDataset[position]
        holder.itemView.textView_username_new_messages?.text = i.title
        holder.itemView.textView_userlist_selectPos.text = position.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}
