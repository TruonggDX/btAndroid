package com.example.baitapmau;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baitapmau.adapter.MayTinhAdapter;
import com.example.baitapmau.dao.MayTinhDAO;
import com.example.baitapmau.model.MayTinh;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MayTinhAdapter mayTinhAdapter;
    private List<MayTinh> mayTinhList;
    private MayTinhDAO mayTinhDAO;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mayTinhDAO = new MayTinhDAO(this);
        mayTinhList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewMayTinh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo adapter
        mayTinhAdapter = new MayTinhAdapter(this, mayTinhList);
        recyclerView.setAdapter(mayTinhAdapter);
        mayTinhDAO.open();
        // Load danh sách máy tính
        loadMayTinhList();
        button = findViewById(R.id.btnAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mayTinhAdapter.setOnItemClickListener(new MayTinhAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                MayTinh selectedMayTinh = mayTinhList.get(position);
                showEditMayTinhDialog(selectedMayTinh);
            }

            @Override
            public void onDeleteClick(int position) {
                confirmDeleteMayTinh(mayTinhList.get(position));
            }
        });
    }

    private void loadMayTinhList() {
        mayTinhList.clear();
        mayTinhList.addAll(mayTinhDAO.getAllMayTinh());
        mayTinhAdapter.notifyDataSetChanged();
    }
    private void showEditMayTinhDialog(final MayTinh mayTinh) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit_item, null);
        builder.setView(view);


        final EditText editTextMaMayTinh = view.findViewById(R.id.maMayTinh);
        final EditText editTextTenMayTinh = view.findViewById(R.id.tenMayTinh);
        final EditText editTextLoaiMay = view.findViewById(R.id.loaiMayTinh);
        final EditText editTextHangSX = view.findViewById(R.id.hangSX);
        final EditText editTextSoLuong = view.findViewById(R.id.soLuong);
        final EditText editTextDonGia = view.findViewById(R.id.donGia);


        editTextMaMayTinh.setText(mayTinh.getMaMayTinh());
        editTextTenMayTinh.setText(mayTinh.getTenMayTinh());
        editTextLoaiMay.setText(mayTinh.getLoaiMay());
        editTextHangSX.setText(mayTinh.getHangSX());
        editTextSoLuong.setText(String.valueOf(mayTinh.getSoLuong()));
        editTextDonGia.setText(String.valueOf(mayTinh.getDonGia()));


        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Lấy dữ liệu từ các EditText
                String tenMayTinh = editTextTenMayTinh.getText().toString();
                String loaiMay = editTextLoaiMay.getText().toString();
                String hangSX = editTextHangSX.getText().toString();
                if (!tenMayTinh.equals("") && !loaiMay.equals("") && !hangSX.equals("") && !editTextSoLuong.getText().toString().equals("") && !editTextDonGia.getText().toString().equals("")){
                  try {
                      int soLuong = Integer.parseInt(editTextSoLuong.getText().toString());
                      double donGia = Double.parseDouble(editTextDonGia.getText().toString());

                      // Tạo đối tượng máy tính mới
                      MayTinh editedMayTinh = new MayTinh(mayTinh.getMaMayTinh(), tenMayTinh, loaiMay, hangSX, soLuong, donGia);

                      // Cập nhật máy tính trong cơ sở dữ liệu
                      boolean updated = mayTinhDAO.updateMayTinh(editedMayTinh);

                      if (updated) {
                          // Cập nhật lại danh sách máy tính và RecyclerView
                          loadMayTinhList();
                          Toast.makeText(MainActivity2.this, "Đã cập nhật máy tính", Toast.LENGTH_SHORT).show();
                      } else {
                          Toast.makeText(MainActivity2.this, "Không thể cập nhật máy tính", Toast.LENGTH_SHORT).show();
                      }
                  }catch (NumberFormatException ex){
                      Toast.makeText(MainActivity2.this, "Vui lòng nhập số cho số lượng, đơn giá", Toast.LENGTH_SHORT).show();
                  }
                }else {
                    Toast.makeText(MainActivity2.this, "Vui lòng nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", null);

        builder.create().show();
    }

    private void confirmDeleteMayTinh(final MayTinh mayTinh) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa máy tính này?");


        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Xóa máy tính từ cơ sở dữ liệu
                boolean deleted = mayTinhDAO.deleteMayTinh(mayTinh.getMaMayTinh());

                if (deleted) {

                    mayTinhList.remove(mayTinh);
                    mayTinhAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity2.this, "Đã xóa máy tính", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Không thể xóa máy tính", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", null);

        builder.create().show();
    }
}
