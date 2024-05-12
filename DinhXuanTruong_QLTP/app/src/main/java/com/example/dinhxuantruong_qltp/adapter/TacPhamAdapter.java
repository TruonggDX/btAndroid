package com.example.dinhxuantruong_qltp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dinhxuantruong_qltp.model.TacPham;

import java.util.List;

public class TacPhamAdapter extends ArrayAdapter<TacPham> {
    private Context context;
    private List<TacPham> tacPhamList;

    public TacPhamAdapter(Context context, List<TacPham> tacPhamList) {
        super(context, 0, tacPhamList);
        this.context = context;
        this.tacPhamList = tacPhamList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TacPham currentTacPham = tacPhamList.get(position);

        TextView tacPhamNameTextView = convertView.findViewById(android.R.id.text1);
        String getName = "Tên tác phẩm: " + currentTacPham.getTenTacPham();
        tacPhamNameTextView.setText(getName);

        TextView tacPhamDetailsTextView = convertView.findViewById(android.R.id.text2);
        String details ="Số lượng: " + currentTacPham.getSoLuong();
        tacPhamDetailsTextView.setText(details);

        return convertView;
    }
}
