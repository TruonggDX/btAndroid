package com.example.baitapmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baitapmau.dao.MayTinhDAO;
import com.example.baitapmau.model.MayTinh;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMaMayTinh, editTextTenMayTinh, editTextLoaiMay, editTextHangSX, editTextSoLuong, editTextDonGia;
    private Button button, button2;
    private MayTinhDAO mayTinhDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items);
        mayTinhDAO = new MayTinhDAO(this);

        mayTinhDAO.open();

        editTextMaMayTinh = findViewById(R.id.maMayTinh);
        editTextTenMayTinh = findViewById(R.id.tenMayTinh);
        editTextLoaiMay = findViewById(R.id.loaiMayTinh);
        editTextHangSX = findViewById(R.id.hangSX);
        editTextSoLuong = findViewById(R.id.soLuong);
        editTextDonGia = findViewById(R.id.donGia);
        button = findViewById(R.id.btnThem);
        button2 = findViewById(R.id.btnView); // Đổi button2

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maMayTinh = editTextMaMayTinh.getText().toString();
                String tenMayTinh = editTextTenMayTinh.getText().toString();
                String loaiMay = editTextLoaiMay.getText().toString();
                String hangSX = editTextHangSX.getText().toString();
               if (!maMayTinh.equals("") && !tenMayTinh.equals("") && !loaiMay.equals("") && !hangSX.equals("") && !editTextSoLuong.getText().toString().equals("") && !editTextDonGia.getText().toString().equals("")){
                   try {
                       int soLuong = Integer.parseInt(editTextSoLuong.getText().toString());
                       double donGia = Double.parseDouble(editTextDonGia.getText().toString());
                       // Tạo đối tượng máy tính
                       MayTinh mayTinh = new MayTinh(maMayTinh, tenMayTinh, loaiMay, hangSX, soLuong, donGia);
                       // Thêm vào cơ sở dữ liệu
                       long result = mayTinhDAO.addMayTinh(mayTinh);
                       if (result != -1) {
                           Toast.makeText(MainActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                           // Xóa dữ liệu trong khi thêm thành công
                           editTextMaMayTinh.setText("");
                           editTextTenMayTinh.setText("");
                           editTextLoaiMay.setText("");
                           editTextHangSX.setText("");
                           editTextSoLuong.setText("");
                           editTextDonGia.setText("");
                       } else {
                           Toast.makeText(MainActivity.this, "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                       }
                   }catch (NumberFormatException ex){
                       Toast.makeText(MainActivity.this, "Vui lòng nhập số cho số lượng, đơn giá", Toast.LENGTH_SHORT).show();
                   }
               }else {
                   Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
               }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
