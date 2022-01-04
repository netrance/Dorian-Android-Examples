package cf.domone.android.recyclerview_example_2;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * View holder class to set the views of picture items
 */
public class PictureItemViewHolder extends RecyclerView.ViewHolder {

    private View      itemView;
    private ImageView ivPicture;

    public PictureItemViewHolder(View itemView) {
        super(itemView);

        this.itemView = itemView;
        this.ivPicture = (ImageView)itemView.findViewById(R.id.ivPicture);
    }

    public View getItemView() {
        return this.itemView;
    }

    public void setPicture(String imagePath) {
        int gridWidth = getGridWidth();

        this.ivPicture.getLayoutParams().width = gridWidth;
        this.ivPicture.getLayoutParams().height = gridWidth;

        // Picasso library is utilized for better performance of scrolling up and down.
        Picasso.with(this.itemView.getContext())
                .load(new File(imagePath))
                .resize(gridWidth, gridWidth)
                .into(this.ivPicture);
    }

    private int getGridWidth() {
        WindowManager wm = (WindowManager) this.itemView.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        int displayWidth = displaySize.x;

        return displayWidth / 3;
    }
}
