package com.khaled.rwayat3beer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WebViewHtml extends AppCompatActivity {
    WebView mWebview;
    int page;
    String StoryTitle;
    DB_Sqlite_Favorite db_fav =new DB_Sqlite_Favorite(this);
    ArrayList<List_itme_Index> List_Favorite = new ArrayList<List_itme_Index>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_html);

        Toolbar WebToolbar =(Toolbar)findViewById(R.id.ToolbarWebID);
        setSupportActionBar(WebToolbar);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.backicon);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



       mWebview =(WebView)findViewById(R.id.WebViewID);

        Intent data = getIntent();



         StoryTitle = data.getExtras().getString("mTitleKey");

         page =data.getExtras().getInt("pageNum");

        mWebview.loadUrl("file:///android_asset/html/"+ page+".htm");





        if (getSupportActionBar()!= null) {
            getSupportActionBar().setTitle(StoryTitle);
        }
        WebToolbar.setTitleTextColor(Color.WHITE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuweb, menu);

        return true;
       // return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
            finish();
        if (item.getItemId()==R.id.FavoritID) {


            int check= db_fav.get_check_List_Favorite(StoryTitle);
            if (check>0) {


                Toast.makeText(this, "عفوا، العنوان موجود بالمفضلة", Toast.LENGTH_SHORT).show();

            }else if (check==0){
                db_fav.Insert_to_favorite(StoryTitle,page);
                Toast.makeText(this, "تم الاضافة الي المفضلة بنجاح", Toast.LENGTH_SHORT).show();

            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putIntArray("IntKey", new int[]{mWebview.getScrollX(),mWebview.getScrollY()});
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int[] position = savedInstanceState.getIntArray("IntKey");
        if (position!=null)
            mWebview.scrollTo(position[0],position[1]);


           /* mWebview.post(new Runnable() {
                @Override
                public void run() {
                    mWebview.scrollTo(position[0],position[1]);
                }
            });*/
    }


}
