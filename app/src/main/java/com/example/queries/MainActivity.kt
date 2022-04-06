package com.example.queries

import android.app.Dialog
import android.content.ContentValues.TAG
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.Insets.add
import androidx.core.view.OneShotPreDrawListener.add
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.queries.databinding.ActivityMainBinding
import com.example.queries.databinding.DialogBinding
import com.google.android.gms.common.util.WorkSourceUtil.add
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: Adapter
    lateinit var linearLayoutManager: LinearLayoutManager
    var arrayList: ArrayList<query> = ArrayList()
    val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = Adapter(arrayList)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rv.layoutManager = linearLayoutManager
        binding.rv.adapter = adapter


        binding.btndialog.setOnClickListener {
            dialog()
        }
    }
    private fun dialog() {
            var dialog = Dialog(this)
            var dialogBinding = DialogBinding.inflate(layoutInflater)

            dialog.setContentView(dialogBinding.root)
            dialog.setCancelable(true)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
            dialogBinding.btnsave.setOnClickListener {
                if (TextUtils.isEmpty(dialogBinding.tvname.text.toString())) {
                    Toast.makeText(this, "Enter Name", Toast.LENGTH_LONG).show()
                } else if (TextUtils.isEmpty(dialogBinding.tvclass.text.toString())) {
                    Toast.makeText(this, "Enter class name", Toast.LENGTH_SHORT).show()
                } else {
                    val user = hashMapOf(
                        "name" to dialogBinding.tvname.text.toString(),
                        "class" to dialogBinding.tvclass.text.toString()

                    )
                    db.collection("documents").document("details of student")
                        .set(user)
                        .addOnSuccessListener {
                            Log.d(
                                TAG,
                                "DocumentSnapshot successfully written!"
                            )
                        }
                        .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

                    arrayList.add(query(dialogBinding.tvname.text.toString(),dialogBinding.tvclass.text.toString()))
                    adapter.notifyDataSetChanged()

            }
        }
    }
}

//    db.collection("documents").document("details of student")
//            .update(
//                mapOf(
//                    "first" to "supreet",
//                    "last" to "kaur"
//                )
//            )
//    }
//db.collection("documents").document("details of student").delete().addOnSuccessListener{
//            Log.d(TAG, "DocumentSnapshot successfully deleted!") }
//            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
//        // Deleting collections from an Android client is not recommended.
//
//    }
