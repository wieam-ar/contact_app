package com.example.contact_app


import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class ContactAdapter(private val contacts: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private val colors = listOf(
        "#FF6F61", "#6B5B95", "#88B04B", "#F7CAC9", "#92A8D1",
        "#955251", "#B565A7", "#009B77", "#DD4124", "#45B8AC"
    )

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconText: TextView = itemView.findViewById(R.id.iconText)
        val nameText: TextView = itemView.findViewById(R.id.contactName)
        val phoneText: TextView = itemView.findViewById(R.id.contactPhone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameText.text = contact.name
        holder.phoneText.text = contact.phone

        val letter = contact.name.first().uppercase()
        holder.iconText.text = letter

        val color = Color.parseColor(colors[position % colors.size])
        val background = holder.iconText.background.mutate() as GradientDrawable
        background.setColor(color)
    }

    override fun getItemCount(): Int = contacts.size
}
