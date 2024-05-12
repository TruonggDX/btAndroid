package com.example.dinhxuantruong_qlsv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinhxuantruong_qlsv.R;
import com.example.dinhxuantruong_qlsv.model.SinhVien;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.SinhVienViewHolder> {
    private Context context;
    private List<SinhVien> sinhVienList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public SinhVienAdapter(Context context, List<SinhVien> sinhVienList) {
        this.context = context;
        this.sinhVienList = sinhVienList;
    }

    @NonNull
    @Override
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item_view, parent, false);
        return new SinhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder holder, int position) {
        SinhVien sinhVien = sinhVienList.get(position);
        holder.bind(sinhVien);
    }

    @Override
    public int getItemCount() {
        return sinhVienList.size();
    }

    public class SinhVienViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMaSV, textViewHoTen, textViewNgaySinh, textViewDiaChi, textViewSDT, textViewKhoa, textViewLop, textViewMonHoc;
        private Button buttonEdit, buttonDelete;

        public SinhVienViewHolder(View itemView) {
            super(itemView);
            textViewMaSV = itemView.findViewById(R.id.textViewMaSV);
            textViewHoTen = itemView.findViewById(R.id.textViewHoTen);
            textViewNgaySinh = itemView.findViewById(R.id.textViewNgaySinh);
            textViewDiaChi = itemView.findViewById(R.id.textViewDiaChi);
            textViewSDT = itemView.findViewById(R.id.textViewSDT);
            textViewKhoa = itemView.findViewById(R.id.textViewKhoa);
            textViewLop = itemView.findViewById(R.id.textViewLop);
            textViewMonHoc = itemView.findViewById(R.id.textViewMonHoc);

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

        public void bind(SinhVien sinhVien) {
            textViewMaSV.setText(sinhVien.getMaSinhVien());
            textViewHoTen.setText(sinhVien.getHoTen());
            textViewNgaySinh.setText(sinhVien.getNgaySinh());
            textViewDiaChi.setText(sinhVien.getDiaChi());
            textViewSDT.setText(sinhVien.getSoDienThoai());
            textViewKhoa.setText(sinhVien.getKhoa());
            textViewLop.setText(sinhVien.getLop());
            textViewMonHoc.setText(sinhVien.getMonHoc());
        }
    }
}
