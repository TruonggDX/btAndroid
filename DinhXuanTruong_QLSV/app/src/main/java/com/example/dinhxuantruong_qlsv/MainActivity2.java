package com.example.dinhxuantruong_qlsv;

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

import com.example.dinhxuantruong_qlsv.adapter.SinhVienAdapter;
import com.example.dinhxuantruong_qlsv.dao.SinhVienDAO;
import com.example.dinhxuantruong_qlsv.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SinhVienAdapter sinhVienAdapter;
    private List<SinhVien> sinhVienList;
    private SinhVienDAO sinhVienDAO;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sinhVienDAO = new SinhVienDAO(this);
        sinhVienList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewSinhVien);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter
        sinhVienAdapter = new SinhVienAdapter(this, sinhVienList);
        recyclerView.setAdapter(sinhVienAdapter);
        sinhVienDAO.open();

        // Load danh sách sinh viên
        loadSinhVienList();

        buttonAdd = findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        sinhVienAdapter.setOnItemClickListener(new SinhVienAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                SinhVien selectedSinhVien = sinhVienList.get(position);
                showEditSinhVienDialog(selectedSinhVien);
            }

            @Override
            public void onDeleteClick(int position) {
                confirmDeleteSinhVien(sinhVienList.get(position));
            }
        });
    }

    private void loadSinhVienList() {
        sinhVienList.clear();
        sinhVienList.addAll(sinhVienDAO.getAllSinhVien());
        sinhVienAdapter.notifyDataSetChanged();
    }

    private void showEditSinhVienDialog(final SinhVien sinhVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit_item, null);
        builder.setView(view);

        final EditText editTextMaSV = view.findViewById(R.id.maSV);
        final EditText editTextHoTen = view.findViewById(R.id.hoTen);
        final EditText editTextNgaySinh = view.findViewById(R.id.ngaySinh);
        final EditText editTextDiaChi = view.findViewById(R.id.diaChi);
        final EditText editTextSDT = view.findViewById(R.id.soDienThoai);
        final EditText editTextKhoa = view.findViewById(R.id.khoa);
        final EditText editTextLop = view.findViewById(R.id.lop);
        final EditText editTextMonHoc = view.findViewById(R.id.monHoc);

        editTextMaSV.setText(sinhVien.getMaSinhVien());
        editTextHoTen.setText(sinhVien.getHoTen());
        editTextNgaySinh.setText(sinhVien.getNgaySinh());
        editTextDiaChi.setText(sinhVien.getDiaChi());
        editTextSDT.setText(sinhVien.getSoDienThoai());
        editTextKhoa.setText(sinhVien.getKhoa());
        editTextLop.setText(sinhVien.getLop());
        editTextMonHoc.setText(sinhVien.getMonHoc());

        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Lấy dữ liệu từ các EditText
                String hoTen = editTextHoTen.getText().toString();
                String ngaySinh = editTextNgaySinh.getText().toString();
                String diaChi = editTextDiaChi.getText().toString();
//                String soDienThoai = editTextSDT.getText().toString();
                String khoa = editTextKhoa.getText().toString();
                String lop = editTextLop.getText().toString();
                String monHoc = editTextMonHoc.getText().toString();

                if (!hoTen.isEmpty() && !ngaySinh.isEmpty() && !diaChi.isEmpty() && !khoa.isEmpty() && !lop.isEmpty() && !monHoc.isEmpty()) {

                    String soDienThoai = editTextSDT.getText().toString();
                    if (soDienThoai.length() == 10){
                        SinhVien editedSinhVien = new SinhVien(sinhVien.getMaSinhVien(), hoTen, ngaySinh, diaChi, soDienThoai, khoa, lop, monHoc);
                        boolean updated = sinhVienDAO.updateSinhVien(editedSinhVien);
                        if (updated) {
                            loadSinhVienList();
                            Toast.makeText(MainActivity2.this, "Đã cập nhật sinh viên", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity2.this, "Không thể cập nhật sinh viên", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity2.this, "Số điện phải gồm 10 số !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity2.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", null);
        builder.create().show();
    }
    private void confirmDeleteSinhVien(final SinhVien sinhVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa sinh viên này?");

        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Xóa sinh viên từ cơ sở dữ liệu
                boolean deleted = sinhVienDAO.deleteSinhVien(sinhVien.getMaSinhVien());

                if (deleted) {
                    // Xóa sinh viên khỏi danh sách và cập nhật RecyclerView
                    sinhVienList.remove(sinhVien);
                    sinhVienAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity2.this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Không thể xóa sinh viên", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", null);

        builder.create().show();
    }
}
