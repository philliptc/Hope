package com.example.hope.jemaat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hope.R
import com.example.hope.jemaat.fragment.model.Ibadah

class RegistrasiIbadahAdapter() : RecyclerView.Adapter<RegistrasiIbadahAdapter.ViewHolder>(), View.OnClickListener{
    var dataibadah : ArrayList<Ibadah> = ArrayList()
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val btnJadwalIbadah : ImageButton = view.findViewById(R.id.btnNext_JadwalIbadah)
        val tvNamaIbadah = view.findViewById<TextView>(R.id.tvNamaIbadah_JadwalIbadah)
        val tvJamTanggalIbadah : TextView = view.findViewById(R.id.tvJamTanggal_JadwalIbadah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_jadwalibadah, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvNamaIbadah.text = dataibadah[position].nama_ibadah
        holder.tvJamTanggalIbadah.text = dataibadah[position].date
        holder.btnJadwalIbadah.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return dataibadah.size
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}