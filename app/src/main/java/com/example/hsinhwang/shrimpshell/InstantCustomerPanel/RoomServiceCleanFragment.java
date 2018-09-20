package com.example.hsinhwang.shrimpshell.InstantCustomerPanel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.hsinhwang.shrimpshell.Classes.ChatMessage;
import com.example.hsinhwang.shrimpshell.Classes.Common;
import com.example.hsinhwang.shrimpshell.Classes.RoomServiceMsg;
import com.example.hsinhwang.shrimpshell.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class RoomServiceCleanFragment extends Fragment {
    RecyclerView rvRoomService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_service_fab,
                container, false);
        handlerView(view);
        return view;



    }

    private void handlerView(View view) {
        rvRoomService = view.findViewById(R.id.rvRoomService);
        rvRoomService.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<RoomServiceMsg> roomServiceMsgs = getRoomServiceMsgs();
        rvRoomService.setAdapter(new RoomServiceAdapter(getActivity(), roomServiceMsgs));
    }


    private class RoomServiceAdapter extends
            RecyclerView.Adapter<RoomServiceAdapter.MyViewHolder> {
        private Context context;
        private List<RoomServiceMsg> roomServiceMsgs;

        private RoomServiceAdapter(Context context, List<RoomServiceMsg> roomServiceMsgs) {
            this.context = context;
            this.roomServiceMsgs = roomServiceMsgs;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            LinearLayout layout_roomService;
            TextView tvRoomService1, tvRoomService2;
            EditText etRoomService1, etRoomService2;
            TimePicker tpRoomService;
            CheckBox cbRoomService1, cbRoomService2;
            Button btRoomService;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.ivRoomService);
                layout_roomService = itemView.findViewById(R.id.layout_roomService);
                tvRoomService1 = itemView.findViewById(R.id.tvRoomService1);
                tvRoomService2 = itemView.findViewById(R.id.tvRoomService2);
                etRoomService1 = itemView.findViewById(R.id.etRoomService1);
                etRoomService2 = itemView.findViewById(R.id.etRoomService2);
                cbRoomService1 = itemView.findViewById(R.id.cbRoomService1);
                cbRoomService2 = itemView.findViewById(R.id.cbRoomService2);
                btRoomService = itemView.findViewById(R.id.btRoomService);
                tpRoomService = itemView.findViewById(R.id.tpRoomService);


            }
        }

        @Override
        public int getItemCount() {
            return roomServiceMsgs.size();
        }

        @NonNull
        @Override
        public RoomServiceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(context).
                    inflate(R.layout.item_room_service_fab, viewGroup, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final RoomServiceAdapter.MyViewHolder myViewHolder, int position) {
            final RoomServiceMsg roomServiceMsg = roomServiceMsgs.get(position);

            myViewHolder.imageView.setImageResource(roomServiceMsg.getImage());
            myViewHolder.tvRoomService1.setText(roomServiceMsg.getTvRoomService1());
            myViewHolder.tvRoomService2.setVisibility(View.GONE);
            myViewHolder.tpRoomService.setVisibility(View.GONE);
            myViewHolder.etRoomService1.setVisibility(View.GONE);
            myViewHolder.etRoomService2.setVisibility(View.GONE);
            myViewHolder.cbRoomService1.setVisibility(View.GONE);
            myViewHolder.cbRoomService2.setVisibility(View.GONE);
            myViewHolder.btRoomService.setVisibility(View.GONE);

            myViewHolder.layout_roomService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (roomServiceMsg.getNumber()) {
                        case 0:
                            roomServiceMsgs.add(new RoomServiceMsg
                                    ("清潔房間", R.drawable.icon_room_service_clean, 1));
                            roomServiceMsgs.add(new RoomServiceMsg
                                    ("洗衣需求", R.drawable.icon_room_service_washing, 2));
                            roomServiceMsgs.add(new RoomServiceMsg
                                    ("備品需求", R.drawable.icon_room_service_gotoroom, 3));
                            roomServiceMsgs.add(new RoomServiceMsg
                                    ("聯絡櫃檯", R.drawable.icon_call_service_instant, 4));



                            notifyItemInserted(roomServiceMsgs.size());

                            break;

                        case 1:



                            if (
                                    myViewHolder.tvRoomService2.getVisibility() != View.VISIBLE ||
                                            myViewHolder.btRoomService.getVisibility() != View.VISIBLE ||
                                            myViewHolder.tpRoomService.getVisibility() != View.VISIBLE) {

                                myViewHolder.tvRoomService2.setVisibility(View.VISIBLE);
                                myViewHolder.btRoomService.setVisibility(View.VISIBLE);
                                myViewHolder.tpRoomService.setVisibility(View.VISIBLE);

                            } else if (
                                    myViewHolder.tvRoomService2.getVisibility() != View.GONE ||
                                            myViewHolder.btRoomService.getVisibility() != View.GONE ||
                                            myViewHolder.tpRoomService.getVisibility() != View.GONE) {

                                myViewHolder.tvRoomService2.setVisibility(View.GONE);
                                myViewHolder.btRoomService.setVisibility(View.GONE);
                                myViewHolder.tpRoomService.setVisibility(View.GONE);

                            }

                            break;

                        case 2:



                            if (
                                    myViewHolder.tvRoomService2.getVisibility() != View.VISIBLE ||
                                            myViewHolder.btRoomService.getVisibility() != View.VISIBLE ||
                                            myViewHolder.tpRoomService.getVisibility() != View.VISIBLE) {

                                myViewHolder.tvRoomService2.setVisibility(View.VISIBLE);
                                myViewHolder.btRoomService.setVisibility(View.VISIBLE);
                                myViewHolder.tpRoomService.setVisibility(View.VISIBLE);

                            } else if (
                                    myViewHolder.tvRoomService2.getVisibility() != View.GONE ||
                                            myViewHolder.btRoomService.getVisibility() != View.GONE ||
                                            myViewHolder.tpRoomService.getVisibility() != View.GONE) {

                                myViewHolder.tvRoomService2.setVisibility(View.GONE);
                                myViewHolder.btRoomService.setVisibility(View.GONE);
                                myViewHolder.tpRoomService.setVisibility(View.GONE);

                            }


                            break;


                        case 3:



                            if (
                                    myViewHolder.tvRoomService2.getVisibility() != View.VISIBLE ||
                                            myViewHolder.etRoomService1.getVisibility() != View.VISIBLE ||
                                            myViewHolder.etRoomService2.getVisibility() != View.VISIBLE ||
                                            myViewHolder.cbRoomService1.getVisibility() != View.VISIBLE ||
                                            myViewHolder.cbRoomService2.getVisibility() != View.VISIBLE ||
                                            myViewHolder.btRoomService.getVisibility() != View.VISIBLE) {

                                myViewHolder.tvRoomService2.setVisibility(View.VISIBLE);
                                myViewHolder.etRoomService1.setVisibility(View.VISIBLE);
                                myViewHolder.etRoomService2.setVisibility(View.VISIBLE);
                                myViewHolder.cbRoomService1.setVisibility(View.VISIBLE);
                                myViewHolder.cbRoomService2.setVisibility(View.VISIBLE);
                                myViewHolder.btRoomService.setVisibility(View.VISIBLE);


                            } else if (
                                    myViewHolder.tvRoomService2.getVisibility() != View.GONE ||
                                            myViewHolder.etRoomService1.getVisibility() != View.GONE ||
                                            myViewHolder.etRoomService2.getVisibility() != View.GONE ||
                                            myViewHolder.cbRoomService1.getVisibility() != View.GONE ||
                                            myViewHolder.cbRoomService2.getVisibility() != View.GONE ||
                                            myViewHolder.btRoomService.getVisibility() != View.GONE) {

                                myViewHolder.tvRoomService2.setVisibility(View.GONE);
                                myViewHolder.etRoomService1.setVisibility(View.GONE);
                                myViewHolder.etRoomService2.setVisibility(View.GONE);
                                myViewHolder.cbRoomService1.setVisibility(View.GONE);
                                myViewHolder.cbRoomService2.setVisibility(View.GONE);
                                myViewHolder.btRoomService.setVisibility(View.GONE);


                            }


                            break;
                        case 4:

                            Common.connectServer(context,"User","User");



                            if (
                                    myViewHolder.btRoomService.getVisibility() != View.VISIBLE) {

                                myViewHolder.btRoomService.setVisibility(View.VISIBLE);


                            } else if (

                                    myViewHolder.btRoomService.getVisibility() != View.GONE ) {

                                myViewHolder.btRoomService.setVisibility(View.GONE);


                            }

                            break;


                        default:

                            break;

                    }

                    rvRoomService.scrollToPosition(roomServiceMsgs.size() - 1);
                }


            });


            myViewHolder.btRoomService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String UserEnteritem1;
                    String UserEnteritem2;
                    UserEnteritem1 = myViewHolder.etRoomService1.getText().toString();
                    UserEnteritem2 = myViewHolder.etRoomService2.getText().toString();


                    switch (roomServiceMsg.getNumber()) {

                        case 1:


                            Toast.makeText(context,"我們將為您打掃房間",Toast.LENGTH_SHORT).show();








                            break;

                        case 2:



                            Toast.makeText(context,"我們將為您清洗換洗衣物",Toast.LENGTH_SHORT).show();




                            break;

                        case 3:

                            if (myViewHolder.cbRoomService1.isChecked() &&
                                    myViewHolder.cbRoomService2.isChecked()) {

                                if (UserEnteritem1.equals("") ||
                                        UserEnteritem2.equals("")) {

                                    Toast.makeText(context, "你沒有選擇你要的數量！", Toast.LENGTH_SHORT).show();


                                } else {


                                    Toast.makeText(context, "已收到您需要枕頭 " + UserEnteritem1
                                            + " 個及盥洗用具" + UserEnteritem2 + " 組", Toast.LENGTH_SHORT).show();







                                }

                            } else if (myViewHolder.cbRoomService1.isChecked()) {

                                if (UserEnteritem1.equals("")) {

                                    Toast.makeText(context, "你沒有選擇你要的數量！", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(context, "已收到您需要枕頭 " + UserEnteritem1
                                            + "個", Toast.LENGTH_SHORT).show();









                                }


                            } else if (myViewHolder.cbRoomService2.isChecked()) {

                                if (UserEnteritem2.equals("")) {

                                    Toast.makeText(context, "你沒有選擇你要的數量", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(context, "已收到您需要盥洗用具 " + UserEnteritem2
                                            + " 組", Toast.LENGTH_SHORT).show();






                                }

                            }

                            break;

                        case 4:

                            Toast.makeText(context, "已聯絡櫃檯為您服務，請稍候！", Toast.LENGTH_SHORT).show();




                            ChatMessage chatMessage = new ChatMessage("Call","User","Employee"
                                    ,"finish");
                            String chatMessageJson = new Gson().toJson(chatMessage);
                            Common.chatwebSocketClient.send(chatMessageJson);




                            break;

                        default:

                            break;
                    }


                }
            });

        }


    }


    public List<RoomServiceMsg> getRoomServiceMsgs() {
        List<RoomServiceMsg> roomServiceMsgs = new ArrayList<>();
        roomServiceMsgs.add(new RoomServiceMsg
                ("請問您需要什麼客房服務？",
                        R.drawable.icon_room_service, 0));

        return roomServiceMsgs;
    }


}
