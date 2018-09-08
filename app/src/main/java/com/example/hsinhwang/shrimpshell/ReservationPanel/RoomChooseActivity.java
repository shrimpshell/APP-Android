package com.example.hsinhwang.shrimpshell.ReservationPanel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hsinhwang.shrimpshell.Classes.RoomType;
import com.example.hsinhwang.shrimpshell.R;

import java.util.ArrayList;
import java.util.List;

public class RoomChooseActivity extends AppCompatActivity {
    private FloatingActionButton fabRoomChoose;
    private RecyclerView rvRoomChoose;
    private List<RoomType> roomTypeList;
    private Window window;
    private RoomType roomType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_choose);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window = getWindow();
            window.setStatusBarColor(Color.parseColor("#01728B"));
        }
        handleViews();
    }

    private void handleViews() {
        rvRoomChoose = findViewById(R.id.rvRoomChoose);
        rvRoomChoose.setLayoutManager(
                new StaggeredGridLayoutManager(1,
                        StaggeredGridLayoutManager.HORIZONTAL));
        roomTypeList = getRoomTypeList();
        rvRoomChoose.setAdapter(new RoomTypeAdapter(this, roomTypeList));

        /* 不處理捲動事件所以監聽器設為null */
        rvRoomChoose.setOnFlingListener(null);
        /* 如果希望一次滑動一頁資料，要加上PagerSnapHelper物件 */
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(rvRoomChoose);

        fabRoomChoose = findViewById(R.id.fabRoomChoose);
        fabRoomChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int roomTypeLastQuantity;
                roomTypeLastQuantity = roomType.getTvRoomTypeLastQuantity();
                if (roomTypeLastQuantity > 0) {
                    roomTypeLastQuantity = roomTypeLastQuantity - 1;

                }
            }
        });
    }


    private class RoomTypeAdapter extends
            RecyclerView.Adapter<RoomTypeAdapter.MyViewHolder> {
        private Context context;
        private List<RoomType> roomTypeList;

        RoomTypeAdapter(Context context, List<RoomType> roomTypeList) {
            this.context = context;
            this.roomTypeList = roomTypeList;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView ivRoomType;
            TextView tvRoomTypeName, tvRoomTypeSize, tvRoomTypeBed, tvRoomTypeAdult,
                    tvRoomTypeChild, tvRoomTypeLastQuantity, tvRoomTypePrice;
            Button btRoomCheck;

            MyViewHolder(@NonNull View itemView) {
                super(itemView);
                ivRoomType = findViewById(R.id.ivRoomType);
                tvRoomTypeName = findViewById(R.id.tvRoomTypeName);
                tvRoomTypeSize = findViewById(R.id.tvRoomTypeSize);
                tvRoomTypeBed = findViewById(R.id.tvRoomTypeBed);
                tvRoomTypeAdult = findViewById(R.id.tvRoomTypePeopleAdult);
                tvRoomTypeChild = findViewById(R.id.tvRoomTypePeopleChild);
                tvRoomTypeLastQuantity = findViewById(R.id.tvRoomTypeLastQuantity);
                tvRoomTypePrice = findViewById(R.id.tvRoomTypePrice);
            }
        }

        @Override
        public int getItemCount() {
            return roomTypeList.size();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(context).
                    inflate(R.layout.item_room_type, viewGroup, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
            final RoomType roomType = roomTypeList.get(i);
            myViewHolder.ivRoomType.setImageResource(roomType.getIvRoomType());
            myViewHolder.tvRoomTypeName.setText(roomType.getTvRoomTypeName());
            myViewHolder.tvRoomTypeSize.setText(roomType.getTvRoomTypeSize());
            myViewHolder.tvRoomTypeBed.setText(roomType.getTvRoomTypeBed());
            myViewHolder.tvRoomTypeAdult.setText(roomType.getTvRoomTypeAdult());
            myViewHolder.tvRoomTypeChild.setText(roomType.getTvRoomTypeChild());
            myViewHolder.tvRoomTypeLastQuantity.setText(roomType.getTvRoomTypeLastQuantity());
            myViewHolder.tvRoomTypePrice.setText(roomType.getTvRoomTypePrice());
            myViewHolder.btRoomCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RoomChooseActivity.this,
                            RoomCheckFragment.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("reservationDate", date);
//                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }

    public List<RoomType> getRoomTypeList() {
        List<RoomType> roomTypeList = new ArrayList<>();
        roomTypeList.add(new RoomType(R.drawable.pic_roomtype_2seaview,
                3,
                4100, "海景標準雙人房",
                "35平方公尺", "1張雙人床",
                "3位大人", "1位孩童"));
        roomTypeList.add(new RoomType(R.drawable.pic_roomtype_2seaview,
                2,
                3800, "山景標準雙人房",
                "35平方公尺", "1張雙人床",
                "3位大人", "1位孩童"));
        roomTypeList.add(new RoomType(R.drawable.pic_roomtype_2seaview,
                1,
                5300, "海景標準四人房",
                "45平方公尺", "2張雙人床",
                "4位大人", "1位孩童"));
        roomTypeList.add(new RoomType(R.drawable.pic_roomtype_2seaview,
                3,
                4900, "山景標準四人房",
                "45平方公尺", "1張雙人床",
                "4位大人", "1位孩童"));
        roomTypeList.add(new RoomType(R.drawable.pic_roomtype_2seaview,
                1,
                5800, "海景精緻雙人房",
                "42平方公尺", "1張雙人床",
                "3位大人", "1位孩童"));
        roomTypeList.add(new RoomType(R.drawable.pic_roomtype_2seaview,
                2,
                5400, "山景精緻雙人房",
                "42平方公尺", "1張雙人床",
                "3位大人", "1位孩童"));
        roomTypeList.add(new RoomType(R.drawable.pic_roomtype_2seaview,
                1,
                7000, "海景精緻四人房",
                "52平方公尺", "1張雙人床",
                "5位大人", "1位孩童"));
        roomTypeList.add(new RoomType(R.drawable.pic_roomtype_2seaview,
                1,
                6800, "山景精緻四人房",
                "52平方公尺", "1張雙人床",
                "5位大人", "1位孩童"));
        roomTypeList.add(new RoomType(R.drawable.pic_roomtype_2seaview,
                1,
                8000, "海景豪華雙人房",
                "60平方公尺", "1張雙人床",
                "3位大人", "1位孩童"));
        roomTypeList.add(new RoomType(R.drawable.pic_roomtype_2seaview,
                1,
                7600, "山景豪華雙人房",
                "60平方公尺", "1張雙人床",
                "3位大人", "1位孩童"));
//        roomTypeList.add(new RoomType(R.drawable.pic_roomtype_2seaview,
//                Integer.valueOf(R.string.roomTypeLastQuantity),
//                Integer.valueOf(R.string.roomTypePrice),String.valueOf(R.string.roomTypeName),
//                String.valueOf(R.string.roomTypeSize),String.valueOf(R.string.roomTypeBed),
//                String.valueOf(R.string.roomTypePeopleAdult),String.valueOf(R.string.roomTypePeopleChild)));
        return roomTypeList;
    }
}