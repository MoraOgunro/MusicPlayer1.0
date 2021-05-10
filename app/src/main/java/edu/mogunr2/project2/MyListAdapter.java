package edu.mogunr2.project2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    protected Context context;
    protected List<MusicData> songList;
    protected int PADDING = 40;
    private RVClickListener RVlistener;


    public MyListAdapter(Context c, List<MusicData> songList, RVClickListener listener){
        context = c;
        this.songList = songList;
        this.RVlistener = listener;
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView, RVlistener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
        holder.title.setText(songList.get(position).getTitle());
        holder.artist.setText(songList.get(position).getArtist());
        holder.image.setImageResource(songList.get(position).getCoverID());

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

        public TextView title;
        public ImageView image;
        public TextView artist;
        private RVClickListener listener;
        private View itemView;


        public ViewHolder(@NonNull View itemView, RVClickListener passedListener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleView);
            artist = (TextView) itemView.findViewById(R.id.artistView);
            image = (ImageView) itemView.findViewById(R.id.coverView);
            this.itemView = itemView;
            itemView.setOnCreateContextMenuListener(this); //set context menu for each list item (long click)
            this.listener = passedListener;


            /*
                don't forget to set the listener defined here to the view (list item) that was
                passed in to the constructor.
             */
            itemView.setOnClickListener(this); //set short click listener
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onClick(v, pos);
            Uri webpage = Uri.parse(songList.get(pos).getSongLink());
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            context.startActivity(webIntent);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            //inflate menu from xml
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.context_menu, menu);
            menu.getItem(0).setOnMenuItemClickListener(onMenu);
            menu.getItem(1).setOnMenuItemClickListener(onMenu);
            menu.getItem(2).setOnMenuItemClickListener(onMenu);

          /*  //create menu in code

            menu.setHeaderTitle("My context menu");

            //add menu items and set the listener for each
            menu.add(0,v.getId(),0,"option 1").setOnMenuItemClickListener(onMenu);
            menu.add(0,v.getId(),0,"option 2").setOnMenuItemClickListener(onMenu);
            menu.add(0,v.getId(),0,"option 3").setOnMenuItemClickListener(onMenu);
            */

        }

        /*
            listener for menu items clicked
         */
        private final MenuItem.OnMenuItemClickListener onMenu = new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                Uri webpage;
                Intent webIntent;
                int id = item.getItemId();
                if(id == R.id.menuOptionClip){
                    webpage = Uri.parse(songList.get(getAdapterPosition()).getSongLink());
                    webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    context.startActivity(webIntent);
                }else if(id ==  R.id.menuOptionSongWiki){
                    webpage = Uri.parse(songList.get(getAdapterPosition()).getSongWikipediaLink());
                    webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    context.startActivity(webIntent);
                }else if(id == R.id.menuOptionArtistWiki){
                    webpage = Uri.parse(songList.get(getAdapterPosition()).getArtistWikipediaLink());
                    webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    context.startActivity(webIntent);
                }
                return true;
            }
        };
    }
}

