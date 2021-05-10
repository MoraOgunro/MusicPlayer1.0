package edu.mogunr2.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity{
    RecyclerView albumView;
    MyListAdapter adapter;
    MyGridAdapter gridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        albumView = (RecyclerView) findViewById(R.id.recyclerView);

        ArrayList<MusicData> mList = new ArrayList<MusicData>(Arrays.asList(
                new MusicData("Stay Tonight","청하 (CHUNG HA)",R.drawable.stay_tonight,"https://www.youtube.com/watch?v=By9-Lqn5358","https://en.wikipedia.org/wiki/Stay_Tonight","https://en.wikipedia.org/wiki/Chungha"),
                new MusicData("Chanel","Frank Ocean",R.drawable.chanel,"https://www.youtube.com/watch?v=XnbsIl2BnWw","https://en.wikipedia.org/wiki/Chanel_(Frank_Ocean_song)","https://en.wikipedia.org/wiki/Frank_Ocean"),
                new MusicData("Dragonball Durag","Thundercat",R.drawable.dragonball,"https://www.youtube.com/watch?v=ormQQG2UhtQ","https://genius.com/Thundercat-dragonball-durag-lyrics","https://en.wikipedia.org/wiki/Thundercat_(musician)"),
                new MusicData("Teardrops","Lovestation",R.drawable.lovestation,"https://www.youtube.com/watch?v=FA3Pi0izrkk","https://en.wikipedia.org/wiki/Teardrops_(Womack_%26_Womack_song)#Lovestation_version","https://en.wikipedia.org/wiki/Lovestation"),
                new MusicData("피 땀 눈물 (Blood Sweat & Tears)","BTS",R.drawable.bst,"https://www.youtube.com/watch?v=hmE9f-TEutc","https://en.wikipedia.org/wiki/Blood_Sweat_%26_Tears_(song)","https://en.wikipedia.org/wiki/BTS"),
                new MusicData("On My Own","Jaden Smith ft. Kid Cudi",R.drawable.on_my_own,"https://www.youtube.com/watch?v=fNkPOLpQjlw","https://en.wikipedia.org/wiki/Erys","https://en.wikipedia.org/wiki/Jaden_Smith"),
                new MusicData("Georgia","Kevin Abstract",R.drawable.georgia,"https://www.youtube.com/watch?v=j1XRtu5WyVc","https://genius.com/Kevin-abstract-georgia-lyrics","https://en.wikipedia.org/wiki/Kevin_Abstract"),
                new MusicData("Heaven All Around Me","Saba",R.drawable.saba,"https://www.youtube.com/watch?v=8uyTY1m3zzA","https://en.wikipedia.org/wiki/Care_for_Me","https://en.wikipedia.org/wiki/Saba_(rapper)"),
                new MusicData("Nights","Frank Ocean",R.drawable.nights,"https://www.youtube.com/watch?v=r4l9bFqgMaQ","https://en.wikipedia.org/wiki/Blonde_(Frank_Ocean_album)","https://en.wikipedia.org/wiki/Frank_Ocean")
        ));


        RVClickListener listener = (view,position)->{
            //TextView name = (TextView)view.findViewById(R.id.titleView);
            //Toast.makeText(this,name.getText(),Toast.LENGTH_SHORT).show();
        };

        adapter = new MyListAdapter(this,mList,listener);
        gridAdapter = new MyGridAdapter(this,mList,listener);
        albumView.setHasFixedSize(true);
        albumView.setAdapter(adapter);
        albumView.setLayoutManager(new LinearLayoutManager(this));
        //albumView.setLayoutManager(new GridLayoutManager(this,2));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.optionsMenuList){
            albumView.setAdapter(adapter);
            albumView.setLayoutManager(new LinearLayoutManager(this));
        }else if(id == R.id.optionsMenuGrid){
            albumView.setAdapter(gridAdapter);
            albumView.setLayoutManager(new GridLayoutManager(this,2));
        }
        return true;
    }
}