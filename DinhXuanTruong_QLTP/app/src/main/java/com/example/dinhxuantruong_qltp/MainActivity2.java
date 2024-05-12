package com.example.dinhxuantruong_qltp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dinhxuantruong_qltp.adapter.TacPhamAdapter;
import com.example.dinhxuantruong_qltp.dao.TacPhamDAO;
import com.example.dinhxuantruong_qltp.model.TacPham;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ListView listViewTacPham;
    private TacPhamAdapter tacPhamAdapter;
    private TacPhamDAO tacPhamDAO;
    private List<TacPham> tacPhamList;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btnAdd);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // Khởi tạo đối tượng DAO
        tacPhamDAO = new TacPhamDAO(this);
        tacPhamDAO.open();

        // Ánh xạ ListView
        listViewTacPham = findViewById(R.id.listViewTacPham);

        // Lấy danh sách tác phẩm từ cơ sở dữ liệu
        tacPhamList = tacPhamDAO.getAllTacPham();

        // Khởi tạo và gắn adapter cho ListView
        tacPhamAdapter = new TacPhamAdapter(this, tacPhamList);
        listViewTacPham.setAdapter(tacPhamAdapter);

        // Đăng ký Context Menu cho ListView
        registerForContextMenu(listViewTacPham);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tacPhamDAO.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listViewTacPham) {
            menu.setHeaderTitle("Tùy chọn");
            menu.add(Menu.NONE, 1, Menu.NONE, "Sửa");
            menu.add(Menu.NONE, 2, Menu.NONE, "Xóa");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        TacPham selectedTacPham = tacPhamList.get(position);

        switch (item.getItemId()) {
            case 1:
                showEditDialog(selectedTacPham);
                return true;
            case 2:
                deleteTacPham(selectedTacPham);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showEditDialog(TacPham tacPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_item, null);
        builder.setView(dialogView);

        EditText editTextMaTacPham = dialogView.findViewById(R.id.editTextMaTacPham);
        EditText editTextTenTacPham = dialogView.findViewById(R.id.editTextTenTacPham);
        EditText editTextNhaXuatBan = dialogView.findViewById(R.id.editTextNhaXuatBan);
        EditText editTextSoXuatBan = dialogView.findViewById(R.id.editTextSoXuatBan);
        EditText editTextSoLuong = dialogView.findViewById(R.id.editTextSoLuong);
        EditText editTextDonGia = dialogView.findViewById(R.id.editTextDonGia);

        editTextMaTacPham.setText(tacPham.getMaTacPham());
        editTextTenTacPham.setText(tacPham.getTenTacPham());
        editTextNhaXuatBan.setText(tacPham.getNhaXuatBan());
        editTextSoXuatBan.setText(String.valueOf(tacPham.getSoXuatBan()));
        editTextSoLuong.setText(String.valueOf(tacPham.getSoLuong()));
        editTextDonGia.setText(String.valueOf(tacPham.getDonGia()));

        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenTacPham = editTextTenTacPham.getText().toString();
                String nhaXuatBan = editTextNhaXuatBan.getText().toString();
                if (!tenTacPham.equals("") && !nhaXuatBan.equals("")) {
                    try {
                        int soXuatBan = Integer.parseInt(editTextSoXuatBan.getText().toString());
                        int soLuong = Integer.parseInt(editTextSoLuong.getText().toString());
                        double donGia = Double.parseDouble(editTextDonGia.getText().toString());
                        tacPham.setTenTacPham(tenTacPham);
                        tacPham.setNhaXuatBan(nhaXuatBan);
                        tacPham.setSoXuatBan(soXuatBan);
                        tacPham.setSoLuong(soLuong);
                        tacPham.setDonGia(donGia);

                        if (tacPhamDAO.updateTacPham(tacPham)) {
                            Toast.makeText(MainActivity2.this, "Cập nhật tác phẩm thành công", Toast.LENGTH_SHORT).show();
                            refreshListView();
                        } else {
                            Toast.makeText(MainActivity2.this, "Cập nhật tác phẩm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }catch (NumberFormatException ex){
                        Toast.makeText(MainActivity2.this, "Vui lòng nhập số cho số xuất bản,số lượng, đơn giá", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity2.this, "Vui lòng nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }    private void refreshListView() {
        // Cập nhật lại danh sách tác phẩm từ cơ sở dữ liệu
        tacPhamList.clear();
        tacPhamList.addAll(tacPhamDAO.getAllTacPham());
        tacPhamAdapter.notifyDataSetChanged();
    }

    private void deleteTacPham(TacPham tacPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa tác phẩm");
        builder.setMessage("Bạn có chắc chắn muốn xóa tác phẩm này?");

        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (tacPhamDAO.deleteTacPham(tacPham.getMaTacPham())) {
                    Toast.makeText(MainActivity2.this, "Xóa tác phẩm thành công", Toast.LENGTH_SHORT).show();
                    refreshListView();
                } else {
                    Toast.makeText(MainActivity2.this, "Xóa tác phẩm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}



