package com.yyren.happyread.adapter;

import com.yyren.happyread.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 雷楚桥 on 2017/4/23.
 */

public class InfoAdapter {
    private List<Map<String,Object>> myList,noticeList;
    public InfoAdapter(){
        init();
    }


    private void init(){
        myList = new ArrayList<Map<String, Object>>();
        noticeList = new ArrayList<Map<String, Object>>();
    }

    //得到数据源
    public List<Map<String, Object>> getMyData(){
        int icon[] = new int[]{R.mipmap.my_unlock,R.mipmap.my_lock,R.mipmap.my_like,R.mipmap.my_collection,R.mipmap.my_special,R.mipmap.my_library,R.mipmap.my_library,};
        String name[] = new String[]{"书籍分享","二手交易","我的收藏","收藏的文章","我的专题","我的文集","学习分享"};
        int count[] = new int[]{2,2,3,2,0,1,-1};
        for(int i=0;i<icon.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("icon",icon[i]);
            map.put("name",name[i]);
            if(count[i] == -1)
                map.put("count",null);
            else
                map.put("count",count[i]);
            myList.add(map);
        }
        return myList;
    }

    //得到数据源
    public List<Map<String, Object>> getNoticeData(){
        int icon[] = new int[]{R.mipmap.notice_comment,R.mipmap.notice_wang,R.mipmap.notice_mail,R.mipmap.notice_like,R.mipmap.notice_more};
        String name[] = new String[]{"评论","书友交流","投稿请求","喜欢和赞","其他提醒"};
        int count[] = new int[]{2,2,3,2,0};
        for(int i=0;i<icon.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("icon",icon[i]);
            map.put("name",name[i]);
            if(count[i] == -1)
                map.put("count",null);
            else
                map.put("count",count[i]);
            noticeList.add(map);
        }
        return noticeList;
    }

}
