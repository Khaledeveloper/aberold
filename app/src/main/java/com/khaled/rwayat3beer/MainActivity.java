package com.khaled.rwayat3beer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
//import android.widget.ArrayAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  MyAdapter.ItemClickListener {
    RecyclerView mRecyclerView;
    ArrayList<String> listIndex = new ArrayList<>();

    ArrayList<List_itme_Index> List_Favorite = new ArrayList<List_itme_Index>();
    // ArrayAdapter<String> arrayAdapter;
    DB_Sqlite_Favorite db_fav = new DB_Sqlite_Favorite(this);
    String List_Type;

    MyAdapter adapter;
    TextView TitleOfStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView rowTextView = (TextView) findViewById(R.id.TextViewRowID);

        // Typeface typeface = Typeface.createFromAsset(getAssets(), "f.ttf");


        // rowTextView.setTypeface(typeface);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.ToolbarmainID);
        setSupportActionBar(mToolbar);

       /* if (getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("?????? ????");
        }*/
        mToolbar.setTitleTextColor(Color.WHITE);


        list_index();

    }

    public void list_index() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("rwayat3ber");
        }

        listIndex.clear();
        List_Type = "Index";
        try {
            InputStream inputStream = getAssets().open("index.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                listIndex.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // arrayAdapter = new ArrayAdapter<String>(this, R.layout.row,R.id.TextViewRowID,listIndex);
        //   mRecyclerView.setAdapter(arrayAdapter);


      /*  // 2. set layoutManger
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 3. create an adapter
       adapter = new MyAdapter(this,listIndex);


        // 4. set adapter
        mRecyclerView.setAdapter(adapter);
        // 5. set item animator to DefaultAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setmClickListener(this); */
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerViewMainID);

        // 2. set layoutManger
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 3. create an adapter
        adapter = new MyAdapter(this, listIndex);


        // 4. set adapter
        mRecyclerView.setAdapter(adapter);
        // 5. set item animator to DefaultAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setmClickListener(this);


    }


    @Override
    public void onItemClick(View view, int position, long id) {
        // TitleOfStory =(TextView)findViewById(R.id.TextViewRowID);
        int pos = (position + 1);
        String TitleOfStory = adapter.getItem(position);
        Toast.makeText(this, "????? ??? " + pos + " " + TitleOfStory, Toast.LENGTH_SHORT).show();

        int PageNum = 0;
        if (List_Type.equals("Index")) {
            PageNum = position;
        } else if (List_Type.equals("Favorite")) {
            PageNum = List_Favorite.get(position).getPage_id();
        }

        Intent intent = new Intent(MainActivity.this, WebViewHtml.class);
        intent.putExtra("mTitleKey", TitleOfStory);
        intent.putExtra("pageNum", PageNum);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu1, menu);
        /*MenuItem searchItem = menu.findItem(R.id.SearchID);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);*/


        return true;


    }

    /*@Override
    public boolean onQueryTextSubmit(String query) {
        search(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        search(newText);

        return false;
    }*/
    /*public void search(String word) {
        if (word.length() > 0) {
            listIndex.clear();
            for (int i = 0; i < listIndex.size(); i++) {
                String name = listIndex.get(i);
                if (name.contains(word)) {
                    listIndex.add(name);
                }
            }


            adapter.notifyDataSetChanged();
        }
    }*/

   /* public void List_Favorite() {
        List_Type = "Favorite";
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Favorites");
            List_Favorite = db_fav.get_All_Favorite();
            listIndex.clear();
            for (int i = 0; i < List_Favorite.size(); i++) {
                listIndex.add(List_Favorite.get(i).getMain_Title());

            }
        }
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.FavoritListID) {
            //Toast.makeText(this,"Favorite List", Toast.LENGTH_SHORT).show();
            List_Type = "Favorite";
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Favorites");
            }
            List_Favorite = db_fav.get_All_Favorite()

            if (List_Favorite.size() == 0) {
                Toast.makeText(this, "no favorite ", Toast.LENGTH_SHORT).show();
                list_index();
            } else {
                listIndex.clear();
                for (int i = 0; i < List_Favorite.size(); i++) {
                    listIndex.add(List_Favorite.get(i).getMain_Title());


                }

                // mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }


        if (id == R.id.ShareID) {

            list_index();
            // Toast.makeText(this, "share ", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.ExitID) {
            finish();
            //  adapter.notifyDataSetChanged();
        }

        //if (id==R.id.SearchID){
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onBackPressed() {
        if (List_Type.equals("Favorite")) {
            list_index();
        } else {
            finish();
        }
    }

    public void delclick(View view) {
        if (List_Type.equals("Favorite")) {
            for (int i = 0; i < List_Favorite.size(); i++) {
                int idUniqe = List_Favorite.get(i).getId();
                String IDFinal = String.valueOf(idUniqe);

                db_fav.Delete_row(IDFinal);
                List_Type = "Favorite";
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Favorites");
                    List_Favorite = db_fav.get_All_Favorite();
                    listIndex.clear();
                    for (int c = 0; c < List_Favorite.size(); c++) {
                        listIndex.add(List_Favorite.get(c).getMain_Title());


                    }

                }
            }
        }
    }
}

   /* @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();

        ArrayList<String> newList = new ArrayList<>();
        for (listIndex){
            String name = adapter.getItem().toLowerCase();
            if (name.contains(newText))
                newList.add(adapter);

        }
        adapter.setFilter(newList);
        return true;
    }
}*/

