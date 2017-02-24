package com.bwie.demo;

import android.content.Context;

import android.support.v7.widget.RecyclerView;


import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageView;


import android.widget.TextView;


import com.bwie.bean.Bean;


import com.nostra13.universalimageloader.core.DisplayImageOptions;


import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.List;

/**
 * 类描述:
 * 作者：陈文梦
 * 时间:2017/2/21 20:10
 * 邮箱:18310832074@163.com
 */

public
class
Myadapter extends RecyclerView.Adapter {

    private Context context;
    private List<Bean.DataBean> dataBeanList;
    private DisplayImageOptions options;

    public Myadapter(Context context, List<Bean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
        options=ImageLoaderUtils.initOptions();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder myViewHolder=new MyViewHolder(View.inflate(context,R.layout.item,null));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder= (MyViewHolder) holder;

        ImageLoader.getInstance().displayImage(dataBeanList.get(position).getImg(),myViewHolder.imageView,options);
        myViewHolder.textView1.setText(dataBeanList.get(position).getUserName());
        myViewHolder.textView2.setText(dataBeanList.get(position).getUserAge()+"");
        myViewHolder.textView3.setText(dataBeanList.get(position).getOccupation());
        myViewHolder.textView4.setText(dataBeanList.get(position).getIntroduction());
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1, textView2, textView3, textView4;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.item_image);
            textView1= (TextView) itemView.findViewById(R.id.item_name);
            textView2= (TextView) itemView.findViewById(R.id.item_age);
            textView3= (TextView) itemView.findViewById(R.id.item_zhiye);
            textView4= (TextView) itemView.findViewById(R.id.introduction);
        }
    }
}
