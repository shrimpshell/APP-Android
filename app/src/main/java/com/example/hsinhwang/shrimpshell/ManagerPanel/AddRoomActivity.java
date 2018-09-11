package com.example.hsinhwang.shrimpshell.ManagerPanel;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hsinhwang.shrimpshell.Classes.Rooms;
import com.google.gson.*;
import com.example.hsinhwang.shrimpshell.R;
import com.example.hsinhwang.shrimpshell.Classes.Common;
import com.example.hsinhwang.shrimpshell.Classes.CommonTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class AddRoomActivity extends AppCompatActivity {
    private final static String TAG = "AddRoomActivity";
    private EditText etAddRoomName, etAddRoomSize, etAddRoomBed, etAddRoomAdult, etAddRoomChild, etAddRoomQuantity, etAddRoomPrice;
    private Button btnAddRoom;
    private ImageView ivRoom;
    private byte[] image;
    private static final int REQUEST_PICK_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        initialization();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            Uri uri = intent.getData();
            int newSize = 512;
            if (uri != null) {
                String[] columns = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(uri, columns,
                        null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    String imagePath = cursor.getString(0);
                    cursor.close();
                    Bitmap srcImage = BitmapFactory.decodeFile(imagePath);
                    Bitmap downsizedImage = Common.downSize(srcImage, newSize);
                    ivRoom.setImageBitmap(downsizedImage);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    srcImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    image = out.toByteArray();
                }
            }
        }
    }

    private void initialization() {
        ivRoom = findViewById(R.id.ivRoom);
        etAddRoomName = findViewById(R.id.etAddRoomName);
        etAddRoomSize = findViewById(R.id.etAddRoomSize);
        etAddRoomBed = findViewById(R.id.etAddRoomBed);
        etAddRoomAdult = findViewById(R.id.etAddRoomAdult);
        etAddRoomChild = findViewById(R.id.etAddRoomChild);
        etAddRoomQuantity = findViewById(R.id.etAddRoomQuantity);
        etAddRoomPrice = findViewById(R.id.etAddRoomPrice);

        btnAddRoom = findViewById(R.id.btnAddRoom);
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 存資料庫
                insertAction();
                finish();
            }
        });
        ivRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_PICK_PICTURE);
            }
        });
    }

    private void insertAction() {
        String name = etAddRoomName.getText().toString().trim(),
                roomSize = etAddRoomSize.getText().toString().trim(),
                bed = etAddRoomBed.getText().toString().trim();
        int adult = Integer.parseInt(etAddRoomAdult.getText().toString().trim()),
                child = Integer.parseInt(etAddRoomChild.getText().toString().trim()),
                quantity = Integer.parseInt(etAddRoomQuantity.getText().toString().trim()),
                price = Integer.parseInt(etAddRoomPrice.getText().toString().trim());

        if (Common.networkConnected(this)) {
            String url = Common.URL + "/RoomServlet";
            Rooms room = new Rooms(0, name, roomSize, bed, adult, child, quantity, price);
            String imageBase64 = "";
            if (image != null) imageBase64 = Base64.encodeToString(image, Base64.DEFAULT);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action", "roomInsert");
            jsonObject.addProperty("room", new Gson().toJson(room));
            jsonObject.addProperty("imageBase64", imageBase64);
            int count = 0;
            try {
                String result = new CommonTask(url, jsonObject.toString()).execute().get();
                count = Integer.valueOf(result);
            } catch (Exception e) {
//                Log.e(TAG, e.toString());
            }
            if (count == 0) {
                Common.showToast(this, R.string.msg_InsertFail);
            } else {
                Common.showToast(this, R.string.msg_InsertSuccess);
            }
        } else {
            Common.showToast(this, R.string.msg_NoNetwork);
        }
    }
}
