package com.bwie.demo;

import android.content.Intent;


import android.os.Handler;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.text.format.Formatter;


import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.bwie.bean.Bean;


import com.google.gson.Gson;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Request;

import com.zhy.http.okhttp.OkHttpUtils;


import com.zhy.http.okhttp.callback.StringCallback;


import java.io.File;


import java.math.BigDecimal;
import java.util.ArrayList;


import java.util.List;


import static android.os.Environment.getExternalStorageDirectory;

public
class
MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;


    private Button dwbtn;


    private Button qcbtn;

    private String path = "http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1";

    private List<Bean.DataBean> dataBeanList = new ArrayList<>();

    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        dwbtn = (Button) findViewById(R.id.dw);
        qcbtn = (Button) findViewById(R.id.qhc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getdate();
        dwbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LocationDemo.class);
                startActivity(intent);
            }
        });
        qcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageLoader.getInstance().clearDiskCache();
                ImageLoader.getInstance().clearMemoryCache();
                qcbtn.setText("清除缓存0KB");

            }
        });


    }

    public void getdate() {

        OkHttpUtils.get().url(path).build().execute(new StringCallback() {

            public void onError(Request request, Exception e) {

            }

            public void onResponse(String response) {
                Bean bean = new Gson().fromJson(response, Bean.class);
                dataBeanList.addAll(bean.getData());
                Myadapter myadapter = new Myadapter(MainActivity.this, dataBeanList);
                recyclerView.setAdapter(myadapter);
                //获取缓存路径
                file = ImageLoader.getInstance().getDiskCache().getDirectory();
                String s = setFile(file);
                 qcbtn.setText("清除缓存" +s );
            }
        });

    }

    private long ff;

    //递归遍历文件夹计算图片大小
    private String setFile(File file) {
        //将文件夹中的所有都放到数据组里
        File[] files = file.listFiles();
        //遍历数组
        for (File f : files) {
            if (f.isDirectory()) {
                setFile(f);
            } else {
                long length = f.length();
                ff += length;
            }
        }
        return getFormatSize(ff);
    }

    /**
     * 格式化单位
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "B";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
}
