package com.example.dinhxuantruong_qltp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dinhxuantruong_qltp.dao.TacPhamDAO;
import com.example.dinhxuantruong_qltp.model.TacPham;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMaTacPham, editTextTenTacPham, editTextNhaXuatBan, editTextSoXuatBan, editTextSoLuong, editTextDonGia;
    private Button buttonThem, buttonXem;

    private TacPhamDAO tacPhamDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items);

        // Khởi tạo đối tượng DAO
        tacPhamDAO = new TacPhamDAO(this);
        tacPhamDAO.open();

        // Ánh xạ các View từ layout
        editTextMaTacPham = findViewById(R.id.maTacPham);
        editTextTenTacPham = findViewById(R.id.tenTacPham);
        editTextNhaXuatBan = findViewById(R.id.nhaXuatBan);
        editTextSoXuatBan = findViewById(R.id.soXuatBan);
        editTextSoLuong = findViewById(R.id.soLuong);
        editTextDonGia = findViewById(R.id.donGia);
        buttonThem = findViewById(R.id.btnThem);
        buttonXem = findViewById(R.id.btnView);
        // Xử lý sự kiện khi nhấn nút "Thêm"
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maTacPham = editTextMaTacPham.getText().toString();
                String tenTacPham = editTextTenTacPham.getText().toString();
                String nhaXuatBan = editTextNhaXuatBan.getText().toString();
                if (!maTacPham.equals("") && !tenTacPham.equals("") && !nhaXuatBan.equals("")) {
                    try {
                            int soXuatBan = Integer.parseInt(editTextSoXuatBan.getText().toString());
                            int soLuong = Integer.parseInt(editTextSoLuong.getText().toString());
                            double donGia = Double.parseDouble(editTextDonGia.getText().toString());
                        // Tạo đối tượng Tác phẩm
                        TacPham tacPham = new TacPham(maTacPham, tenTacPham, nhaXuatBan, soXuatBan, soLuong, donGia);

                        // Thêm Tác phẩm vào CSDL
                        long result = tacPhamDAO.addTacPham(tacPham);

                        if (result != -1) {
                            Toast.makeText(MainActivity.this, "Thêm tác phẩm thành công", Toast.LENGTH_SHORT).show();
                            // Xóa dữ liệu sau khi thêm thành công
                            editTextMaTacPham.setText("");
                            editTextTenTacPham.setText("");
                            editTextNhaXuatBan.setText("");
                            editTextSoXuatBan.setText("");
                            editTextSoLuong.setText("");
                            editTextDonGia.setText("");
                        } else {
                            Toast.makeText(MainActivity.this, "Thêm tác phẩm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }catch (NumberFormatException ex){
                        Toast.makeText(MainActivity.this, "Vui lòng nhập số cho số xuất bản,số lượng, đơn giá", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
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
