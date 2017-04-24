package com.yyren.happyread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.yyren.happyread.adapter.InfoAdapter;

public class MainActivity extends Activity {
    private String nav_type;
    private ViewStub vs_home, vs_attention, vs_notice, vs_my;
    private ImageView home, attention, notice, my, my_info_edit;
    private LinearLayout my_info_layout;
    private Intent intent;
    private ListView my_info_list,notice_info_list;
    private PopupWindow popWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化所有控件
        defineEvent();//开启所有事件
        onLoadListView();//记载ListView控件
    }

    //初始化控件
    private void initView() {
        //预定属性
        nav_type = "home";

        //导航组件
        home = (ImageView) findViewById(R.id.nav_home);
        attention = (ImageView) findViewById(R.id.nav_attention);
        notice = (ImageView) findViewById(R.id.nav_notice);
        my = (ImageView) findViewById(R.id.nav_my);

        //页面组件
        vs_home = (ViewStub) findViewById(R.id.vs_show_home);
        vs_attention = (ViewStub) findViewById(R.id.vs_show_attention);
        vs_notice = (ViewStub) findViewById(R.id.vs_show_notice);
        vs_my = (ViewStub) findViewById(R.id.vs_show_my);
        vs_home.inflate();
        vs_attention.inflate();
        vs_notice.inflate();
        vs_my.inflate();
        vs_attention.setVisibility(View.GONE);
        vs_notice.setVisibility(View.GONE);
        vs_my.setVisibility(View.GONE);

        //我的
        my_info_layout = (LinearLayout) findViewById(R.id.my_info_layout);
        my_info_edit = (ImageView) findViewById(R.id.my_info_edit);
        my_info_list = (ListView) findViewById(R.id.my_info_list);

        //消息
        notice_info_list = (ListView) findViewById(R.id.notice_info_list);
    }



    //改变导航栏显示
    public void changeShow(View view) {
        String tag = view.getTag().toString();
        if (tag.equals("add")){
            tag = nav_type;
            showAdd();
        }else
            nav_type = tag;
        navSwitch(tag);
    }

    //显示add界面
    private void showAdd(){
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(this, R.layout.activity_add, null);
        popWindow = new PopupWindow(popView, RelativeLayout.MarginLayoutParams.MATCH_PARENT,RelativeLayout.MarginLayoutParams.MATCH_PARENT);
        popWindow.setAnimationStyle(R.style.PopupAnimation);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.showAtLocation(parent,Gravity.CENTER , 0, 0);
    }

    //pop界面事件
    public void addShowPop(View view){
        switch(view.getTag().toString()){
            case "type1":
                Toast.makeText(MainActivity.this,"第一个",Toast.LENGTH_SHORT).show();
                break;
            case "type2":
                Toast.makeText(MainActivity.this,"第二个",Toast.LENGTH_SHORT).show();
                break;
            case "type3":
                Toast.makeText(MainActivity.this,"第三个",Toast.LENGTH_SHORT).show();
                break;
            case "cancel":
                Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
                break;
        }
        popWindow.dismiss();
    }

    //导航栏切换
    private void navSwitch(String tag) {
        home.setImageResource(R.mipmap.home);
        attention.setImageResource(R.mipmap.attention_favor);
        notice.setImageResource(R.mipmap.notice);
        my.setImageResource(R.mipmap.my);
        pageSwitch(tag);

        switch (tag) {
            case "home":
                home.setImageResource(R.mipmap.home_fill);
                break;
            case "attention":
                attention.setImageResource(R.mipmap.attention_favor_fill);
                break;
            case "notice":
                notice.setImageResource(R.mipmap.notice_fill);
                break;
            case "my":
                my.setImageResource(R.mipmap.my_fill);
                break;
        }
    }

    //页面切换
    private void pageSwitch(String tag) {
        vs_home.setVisibility(View.GONE);
        vs_attention.setVisibility(View.GONE);
        vs_notice.setVisibility(View.GONE);
        vs_my.setVisibility(View.GONE);
        switch (tag) {
            case "home":
                vs_home.setVisibility(View.VISIBLE);
                break;
            case "attention":
                vs_attention.setVisibility(View.VISIBLE);
                break;
            case "notice":
                vs_notice.setVisibility(View.VISIBLE);
                break;
            case "my":
                vs_my.setVisibility(View.VISIBLE);
                break;
        }
    }

    //加载ListView
    private void onLoadListView(){
        InfoAdapter infoAdapter = new InfoAdapter();
        SimpleAdapter myAdapter = new SimpleAdapter(MainActivity.this,infoAdapter.getMyData(),R.layout.my_info_list,new String[]{"icon","name","count"},new int[]{R.id.my_info_list_icon,R.id.my_info_list_name,R.id.my_info_list_count});
        my_info_list.setAdapter(myAdapter);
        SimpleAdapter noticeAdapter = new SimpleAdapter(MainActivity.this,infoAdapter.getNoticeData(),R.layout.notice_info_list,new String[]{"icon","name","count"},new int[]{R.id.notice_info_list_icon,R.id.notice_info_list_name,R.id.notice_info_list_count});
        notice_info_list.setAdapter(noticeAdapter);
    }

    //事件
    private void defineEvent() {
        //个人主页
        my_info_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, MyInfoActivity.class);
                startActivity(intent);
            }
        });
        //信息编辑
        my_info_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, MyInfoEditActivity.class);
                startActivity(intent);
            }
        });
        //功能列表
        my_info_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name[] = new String[]{"公开文章","私密文章","喜欢的文章","收藏的文章","我的专题","我的文集"};
                Toast.makeText(MainActivity.this,name[i],Toast.LENGTH_SHORT).show();
            }
        });
        //消息类别
        notice_info_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name[] = new String[]{"评论","借书请求","投稿请求","喜欢和赞","其他提醒"};
                Toast.makeText(MainActivity.this,name[i],Toast.LENGTH_SHORT).show();
            }
        });
    }

    //根据Tag来跳转不同的界面
    public void redirectByTag(View view){
        String tag = view.getTag().toString();
        switch (tag){
            case "settings":
                Toast.makeText(MainActivity.this,"设置",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
