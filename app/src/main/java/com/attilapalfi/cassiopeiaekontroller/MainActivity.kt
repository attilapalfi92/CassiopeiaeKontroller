package com.attilapalfi.cassiopeiaekontroller

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.attilapalfi.commons.Endpoint

import kotlinx.android.synthetic.main.activity_main.*
import java.net.InetAddress
import java.util.*

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { server_list }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ServerButtonAdapter(applicationContext)
        recyclerView.adapter = adapter

        Thread({
            while (true) {
                Thread.sleep(2000)
                adapter.addEndpoint(Endpoint(InetAddress.getLocalHost(), Random().nextInt()))
            }
        }).start()
    }
}

private class ServerButtonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val button: Button

    init {
        this.button = view.findViewById(R.id.server_button) as Button
    }
}

private class ServerButtonAdapter(private val context: Context) :
        RecyclerView.Adapter<ServerButtonViewHolder>() {

    private val handler = Handler(Looper.getMainLooper())
    private val servers = ArrayList<Endpoint>()

    fun addEndpoint(endpoint: Endpoint) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            handler.post {
                servers.add(endpoint)
                notifyItemInserted(servers.size - 1)
            }
        } else {
            servers.add(endpoint)
            notifyItemInserted(servers.size - 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.server_button_card, parent, false)

        return ServerButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServerButtonViewHolder, position: Int) {
        val button = holder.button

        button.text = servers[position].toString()

        button.setOnClickListener({
            val intent = Intent(it.context, ControllerActivity::class.java)
            intent.putExtra("server", servers[holder.adapterPosition])
            it.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return servers.size
    }
}