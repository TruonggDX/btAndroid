package com.example.dinhxuantruong_qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dinhxuantruong_qlsv.dao.SinhVienDAO;
import com.example.dinhxuantruong_qlsv.model.SinhVien;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMaSV, editTextHoTen, editTextNgaySinh, editTextDiaChi, editTextSDT, editTextKhoa, editTextLop, editTextMonHoc;
    private Button buttonThem, buttonXem;
    private SinhVienDAO sinhVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items);
        sinhVienDAO = new SinhVienDAO(this);

        sinhVienDAO.open();

        editTextMaSV = findViewById(R.id.maSV);
        editTextHoTen = findViewById(R.id.hoTen);
        editTextNgaySinh = findViewById(R.id.ngaySinh);
        editTextDiaChi = findViewById(R.id.diaChi);
        editTextSDT = findViewById(R.id.soDienThoai);
        editTextKhoa = findViewById(R.id.khoa);
        editTextLop = findViewById(R.id.lop);
        editTextMonHoc = findViewById(R.id.monHoc);
        buttonThem = findViewById(R.id.btnThem);
        buttonXem = findViewById(R.id.btnView);

        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maSV = editTextMaSV.getText().toString();
                String hoTen = editTextHoTen.getText().toString();
                String ngaySinh = editTextNgaySinh.getText().toString();
                String diaChi = editTextDiaChi.getText().toString();
//                String soDienThoai = editTextSDT.getText().toString();
                String khoa = editTextKhoa.getText().toString();
                String lop = editTextLop.getText().toString();
                String monHoc = editTextMonHoc.getText().toString();

                if (!maSV.equals("") && !hoTen.equals("") && !ngaySinh.equals("") && !diaChi.equals("") && !khoa.equals("") && !lop.equals("") && !monHoc.equals("")) {
                    // Create a new student object

                        String soDienThoai = editTextSDT.getText().toString();
                        if (soDienThoai.length() == 10){
                            SinhVien sinhVien = new SinhVien(maSV, hoTen, ngaySinh, diaChi, soDienThoai, khoa, lop, monHoc);
                            // Add the student to the database
                            long result = sinhVienDAO.addSinhVien(sinhVien);

                            if (result != -1) {
                                Toast.makeText(MainActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                                // Clear input fields after successful addition
                                editTextMaSV.setText("");
                                editTextHoTen.setText("");
                                editTextNgaySinh.setText("");
                                editTextDiaChi.setText("");
                                editTextSDT.setText("");
                                editTextKhoa.setText("");
                                editTextLop.setText("");
                                editTextMonHoc.setText("");
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "Số điện phải gồm 10 số !", Toast.LENGTH_SHORT).show();
                        }
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
