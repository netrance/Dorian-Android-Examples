package cf.domone.android.recyclerview_example_1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dorian on 16. 3. 22..
 */
public class SampleItemViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNumber;
    public TextView tvTitle;

    public SampleItemViewHolder(View view) {
        super(view);
        this.tvNumber = (TextView)view.findViewById(R.id.tvNumber);
        this.tvTitle = (TextView)view.findViewById(R.id.tvTitle);
    }
}
