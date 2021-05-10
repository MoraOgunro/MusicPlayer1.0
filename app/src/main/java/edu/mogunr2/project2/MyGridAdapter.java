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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.ViewHolder> {
    protected Context context;
    protected List<MusicData> songList;
    protected int PADDING = 40;
    private RVClickListener RVlistener;


    public MyGridAdapter(Context c, List<MusicData> songList, RVClickListener listener){
        context = c;
        this.songList = songList;
        this.RVlistener = listener;
    }

    @NonNull
    @Override
    public MyGridAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView, RVlistener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyGridAdapter.ViewHolder holder, int position) {
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
            title = (TextView) itemView.findViewById(R.id.gridTitle);
            artist = (TextView) itemView.findViewById(R.id.gridArtist);
            image = (ImageView) itemView.findViewById(R.id.gridImageView);
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
            Log.i("ON_CLICK", "Here1");
            int pos = getAdapterPosition();
            Log.i("ON_CLICK", "Here2");
            //listener.onClick(v, pos);
            Log.i("ON_CLICK", "Here3");
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
                switch (item.getItemId()){
                    case R.id.menuOptionClip:
                        webpage = Uri.parse(songList.get(getAdapterPosition()).getSongLink());
                        webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                        context.startActivity(webIntent);
                        return true;
                    case R.id.menuOptionSongWiki:
                        webpage = Uri.parse(songList.get(getAdapterPosition()).getSongWikipediaLink());
                        webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                        context.startActivity(webIntent);
                        return true;
                    case R.id.menuOptionArtistWiki:
                        webpage = Uri.parse(songList.get(getAdapterPosition()).getArtistWikipediaLink());
                        webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                        context.startActivity(webIntent);
                        return true;
                }
                Log.i("ON_CLICK", title.getText() + " item id: " + item.getOrder());
                return true;
            }
        };
    }
}