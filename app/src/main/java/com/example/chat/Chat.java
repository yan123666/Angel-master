package com.example.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.angel.R;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Chat extends Activity {
    private final Context mContext= Chat.this;

    private boolean flag=false;
    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter adapter;
    private final List<Msg> msgList = new ArrayList<>();
    private List<Msg>dataList=new ArrayList<>();
    private Button sendImage;
    private Boolean imgflag=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置窗口没有标题栏
        setContentView(R.layout.activity_chat);
        adapter = new MsgAdapter(Chat.this, R.layout.item, msgList);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        sendImage=findViewById(R.id.send_image);
        dataList=SharedPreferenceUtil.getSelectBean(mContext,"msg");
        if(dataList!=null&&dataList.size()>0){
            msgList.addAll(dataList);
        }
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);



        sendImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhoto(2);
            }
        });
        send.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    if(!flag){
                        Msg msg = new Msg(content, Msg.SENT,"");
                        msgList.add(msg);
                        flag=true;
                    }else{
                        Msg msg = new Msg(content, Msg.RECEIVED,"");
                        msgList.add(msg);
                        flag=false;
                    }
                    if(msgList!=null&&msgList.size()>0){
                        SharedPreferenceUtil.putSelectBean(mContext,msgList,"msg");
                    }
                    adapter.notifyDataSetChanged();//有新消息时，刷新ListView中的显示
                    msgListView.setSelection(msgList.size());//将ListView定位到最后一行
                    inputText.setText("");//清空输入框的内容
                }
            }
        });
    }

    /**
     * 从相册中选择图片
     */
    private  void openPhoto(int openPhotoCode){
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, openPhotoCode);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 2) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                String imagePath=ImageUtils.getImagePath(uri, null, (Activity) mContext);
                if(!imgflag){
                    Msg msg = new Msg("", Msg.SENT,imagePath);
                    msgList.add(msg);
                    imgflag=true;
                }else{
                    Msg msg = new Msg("", Msg.RECEIVED,imagePath);
                    msgList.add(msg);
                    imgflag=false;
                }
                if(msgList!=null&&msgList.size()>0){
                    SharedPreferenceUtil.putSelectBean(mContext,msgList,"msg");
                }
                adapter.notifyDataSetChanged();//有新消息时，刷新ListView中的显示
                msgListView.setSelection(msgList.size());//将ListView定位到最后一行
               // inputText.setText("");//清空输入框的内容
            }
        }
    }



    public class MsgAdapter extends ArrayAdapter<Msg>{
        private final int resourceId;

        public MsgAdapter(Context context, int textViewresourceId, List<Msg> objects) {
            super(context, textViewresourceId, objects);
            resourceId = textViewresourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Msg msg = getItem(position);
            View view;
            ViewHolder viewHolder;

            if(convertView == null){
                view = LayoutInflater.from(getContext()).inflate(resourceId, null);
                viewHolder = new ViewHolder();
                viewHolder.leftLayout = (LinearLayout)view.findViewById(R.id.left_layout);
                viewHolder.rightLayout = (LinearLayout)view.findViewById(R.id.right_Layout);
                viewHolder.leftMsg = (TextView)view.findViewById(R.id.left_msg);
                viewHolder.rightMsg = (TextView)view.findViewById(R.id.right_msg);
                viewHolder.leftImage=view.findViewById(R.id.left_image);
                viewHolder.rightImage=view.findViewById(R.id.right_image);
                viewHolder.leftMsglayout=view.findViewById(R.id.left_msg_layout);
                viewHolder.rightMsglayout=view.findViewById(R.id.right_msg_layout);

                view.setTag(viewHolder);
            }else{
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }

            if(msg.getType()==Msg.RECEIVED){
                //如果是收到的消息，则显示左边消息布局，将右边消息布局隐藏
                viewHolder.leftLayout.setVisibility(View.VISIBLE);
                viewHolder.rightLayout.setVisibility(View.GONE);
                if(!TextUtils.isEmpty(msg.getImgUrl())){
                    viewHolder.leftMsglayout.setVisibility(View.GONE);
                    viewHolder.leftImage.setVisibility(View.VISIBLE);
                    File file = new File(msg.getImgUrl());
                    Glide.with(mContext).load(file).into(viewHolder.leftImage);
                }else{
                    viewHolder.leftImage.setVisibility(View.GONE);
                    viewHolder.leftMsglayout.setVisibility(View.VISIBLE);
                    viewHolder.leftMsg.setText(msg.getContent());
                }
            }else if(msg.getType()==Msg.SENT){
                //如果是发出去的消息，显示右边布局的消息布局，将左边的消息布局隐藏
                viewHolder.rightLayout.setVisibility(View.VISIBLE);
                viewHolder.leftLayout.setVisibility(View.GONE);
                viewHolder.rightMsg.setText(msg.getContent());

                if(!TextUtils.isEmpty(msg.getImgUrl())){
                    viewHolder.rightMsglayout.setVisibility(View.GONE);
                    viewHolder.rightImage.setVisibility(View.VISIBLE);
                    File file = new File(msg.getImgUrl());
                    Glide.with(mContext).load(file).into(viewHolder.rightImage);

                }else{
                    viewHolder.rightImage.setVisibility(View.GONE);
                    viewHolder.rightMsglayout.setVisibility(View.VISIBLE);
                    viewHolder.rightMsg.setText(msg.getContent());
                }
            }
            return view;
        }

        class ViewHolder{
            LinearLayout leftLayout;
            LinearLayout rightLayout;
            TextView leftMsg;
            TextView rightMsg;
            ImageView leftImage;
            ImageView rightImage;

            LinearLayout leftMsglayout;
            LinearLayout rightMsglayout;
        }
    }








}