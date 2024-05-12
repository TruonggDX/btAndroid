package com.example.baitapmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitapmau.R;
import com.example.baitapmau.model.MayTinh;

import java.util.List;

public class MayTinhAdapter extends RecyclerView.Adapter<MayTinhAdapter.MayTinhViewHolder> {
    private Context context;
    private List<MayTinh> mayTinhList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MayTinhAdapter(Context context, List<MayTinh> mayTinhList) {
        this.context = context;
        this.mayTinhList = mayTinhList;
    }

    @NonNull
    @Override
    public MayTinhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item_view, parent, false);
        return new MayTinhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MayTinhViewHolder holder, int position) {
        MayTinh mayTinh = mayTinhList.get(position);
        holder.bind(mayTinh);
    }

    @Override
    public int getItemCount() {
        return mayTinhList.size();
    }

    public class MayTinhViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMaMayTinh, textViewTenMayTinh, textViewLoaiMay, textViewHangSX, textViewSoLuong, textViewDonGia;
        private Button buttonEdit, buttonDelete;

        public MayTinhViewHolder(View itemView) {
            super(itemView);
            textViewMaMayTinh = itemView.findViewById(R.id.textViewMaMayTinh);
            textViewTenMayTinh = itemView.findViewById(R.id.textViewTenMayTinh);
            textViewLoaiMay = itemView.findViewById(R.id.textViewLoaiMay);
            textViewHangSX = itemView.findViewById(R.id.textViewHangSX);
            textViewSoLuong = itemView.findViewById(R.id.textViewSoLuong);
            textViewDonGia = itemView.findViewById(R.id.textViewDonGia);

            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditClick(position);
                        }
                    }
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }

        public void bind(MayTinh mayTinh) {
            textViewMaMayTinh.setText(mayTinh.getMaMayTinh());
            textViewTenMayTinh.setText(mayTinh.getTenMayTinh());
            textViewLoaiMay.setText(mayTinh.getLoaiMay());
            textViewHangSX.setText(mayTinh.getHangSX());
            textViewSoLuong.setText(String.valueOf(mayTinh.getSoLuong()));
            textViewDonGia.setText(String.valueOf(mayTinh.getDonGia()));
        }
    }
}
