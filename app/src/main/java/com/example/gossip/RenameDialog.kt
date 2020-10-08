package com.example.gossip

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class RenameDialog : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                builder.setMessage(R.string.Rename_chat_group)
                    .setPositiveButton(R.string.rename, DialogInterface.OnClickListener { dialog, id ->
                        //Positive
                    })
                    .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, id ->
                        //Negative
                    })
            builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }
}