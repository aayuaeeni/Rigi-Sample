package com.raju.rigi_sample.chat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.raju.rigi_sample.application.RigiApp
import com.raju.rigi_sample.databinding.ActivityMainBinding
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory
import android.widget.Toast

import android.R

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.raju.rigi_sample.login.LoginActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var client: ChatClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Step 0 - inflate binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val app: RigiApp = application as RigiApp
        client = app.getChatClient()

        // Step 2 - Authenticate and connect the user
        val user = User(id = "tutorial-droid").apply {
            name = "Tutorial Droid"
            image = "https://bit.ly/2TIt8NR"
        }
        client?.connectUser(
            user = user,
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoidHV0b3JpYWwtZHJvaWQifQ.NhEr0hP9W9nwqV7ZkdShxvi02C5PR7SJE7Cs4y7kyqg"
//            token = "fty69nvhbwe3unh3tzccrxuey32nuebuez68q6w27zcf45dxruhb2eekefdmsqer"
        )?.enqueue()

        // Step 3 - Set the channel list filter and order
        // This can be read as requiring only channels whose "type" is "messaging" AND
        // whose "members" include our "user.id"
        val filter = Filters.and(
            Filters.eq("type", "messaging"),
            Filters.`in`("members", listOf(user.id))
        )
        val viewModelFactory = ChannelListViewModelFactory(filter, ChannelListViewModel.DEFAULT_SORT)
        val viewModel: ChannelListViewModel by viewModels { viewModelFactory }

        // Step 4 - Connect the ChannelListViewModel to the ChannelListView, loose
        //          coupling makes it easy to customize
        viewModel.bindView(binding.channelListView, this)
        binding.channelListView.setChannelItemClickListener { channel ->
            startActivity(ChannelActivity4.newIntent(this, channel))
        }

        initActions()
    }

    private fun initActions() {
       binding.ivLogout.setOnClickListener {
           showAlert()
       }
    }

    private fun showAlert() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("Do you really want to logout?")
        builder.setTitle("Warning!")
        builder.setCancelable(false)
        builder.setNegativeButton(resources.getString(R.string.cancel),
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.setPositiveButton("Logout",
            DialogInterface.OnClickListener { dialog, which ->
                val app: RigiApp = application as RigiApp
                val pref = app.getPref()
                pref?.clearPreferences()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
                finishAffinity()
            })

        val alert: AlertDialog = builder.create()
        alert.show()
    }
}
