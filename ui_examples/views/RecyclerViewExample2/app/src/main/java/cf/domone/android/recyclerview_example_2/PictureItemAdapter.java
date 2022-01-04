package cf.domone.android.recyclerview_example_2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Adapter class to hold and show picture items.
 */
public class PictureItemAdapter extends RecyclerView.Adapter<PictureItemViewHolder> {

    private List<PictureItem> pictureItemList;

    private Context context;

    public PictureItemAdapter(Context context, List<PictureItem> pictureItemList) {
        this.context = context;
        this.pictureItemList = pictureItemList;
    }

    @Override
    public PictureItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutPictureItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_picture, parent, false);
        return new PictureItemViewHolder(layoutPictureItem);
    }

    @Override
    public void onBindViewHolder(PictureItemViewHolder viewHolder, final int position) {
        viewHolder.setPicture(pictureItemList.get(position).getPath());

        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Moves to another activity that shows the selected image file.
                Intent intent = new Intent(context, PictureActivity.class);
                intent.putExtra("image_path", pictureItemList.get(position).getPath());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null == pictureItemList) ? 0 : pictureItemList.size();
    }
}
