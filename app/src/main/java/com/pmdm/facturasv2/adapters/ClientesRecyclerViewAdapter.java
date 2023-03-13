package com.pmdm.facturasv2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.pmdm.facturasv2.*;

import androidx.annotation.NonNull;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.pmdm.facturasv2.R;
import com.pmdm.facturasv2.modelo.ModelCliente;

import java.util.ArrayList;

public class ClientesRecyclerViewAdapter extends RecyclerView.Adapter<ClientesRecyclerViewAdapter.MyViewHolder> {

    Context _context;
    private ArrayList<ModelCliente> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.textViewVersion = itemView.findViewById(R.id.textViewVersion);
            this.imageViewIcon = itemView.findViewById(R.id.imageViewClientCard);
        }
    }

    public ClientesRecyclerViewAdapter(Context context, ArrayList<ModelCliente> data) {

        _context = context;
        dataSet = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_detalle_client, parent, false);

        view.setOnClickListener(MaestroClientesFragment.myOnClickListener);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getNombre());
        textViewVersion.setText(""+dataSet.get(listPosition).getCodigo());
        imageView.setImageResource(R.drawable.client);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
