package com.example.contact_app

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private var contacts: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(), Filterable {

    private var contactsFiltered = contacts

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
        val contact = contactsFiltered[position]
        holder.nameText.text = contact.name
        holder.phoneText.text = contact.phone
        holder.iconText.text = contact.name.first().uppercase()

        val color = Color.parseColor(colors[position % colors.size])
        val background = holder.iconText.background.mutate() as GradientDrawable
        background.setColor(color)

        // 🟢 عند الضغط على contact
        holder.itemView.setOnClickListener {
            val context = it.context
            val phoneNumber = contact.phone

            // يفتح تطبيق المكالمات ومعاه الرقم
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = contactsFiltered.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchText = constraint.toString().lowercase()
                val filteredList = if (searchText.isEmpty()) {
                    contacts
                } else {
                    contacts.filter { it.name.lowercase().contains(searchText) }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                contactsFiltered = results?.values as List<Contact>
                notifyDataSetChanged()
            }
        }
    }
}
