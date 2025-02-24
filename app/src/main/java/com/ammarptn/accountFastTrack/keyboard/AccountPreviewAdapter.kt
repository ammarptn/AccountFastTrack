package com.ammarptn.accountFastTrack.keyboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ammarptn.accountFastTrack.data.local.AccountEntity
import com.ammarptn.accountfasttrack.R

class AccountPreviewAdapter : RecyclerView.Adapter<AccountPreviewAdapter.CardViewHolder>() {

    private var accounts: List<AccountEntity> = emptyList()

    fun updateCards(newCards: List<AccountEntity>) {
        accounts = newCards
        notifyDataSetChanged()
    }

    fun getSelectedAccount(position: Int): AccountEntity? {
        return accounts.getOrNull(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_account_preview, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(accounts[position])
    }

    override fun getItemCount(): Int = accounts.size

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val accountView: CardView = view.findViewById(R.id.accountView)
        private val accountName: TextView = view.findViewById(R.id.accountName)
        private val environment: TextView = view.findViewById(R.id.environment)
        private val usernameLabel: TextView = view.findViewById(R.id.usernameLabel)
        private val environmentLabel: TextView = view.findViewById(R.id.environmentLabel)

        fun bind(card: AccountEntity) {
            accountView.setCardBackgroundColor(card.cardColor.toInt())
            
            // Set username with fallback
            accountName.text = if (card.username.isBlank()) "Not set" else card.username
            
            // Set environment with fallback
            environment.text = if (card.environment.isBlank()) "Not set" else card.environment
            
            // Set label alpha
            usernameLabel.alpha = 0.7f
            environmentLabel.alpha = 0.7f
        }
    }
}