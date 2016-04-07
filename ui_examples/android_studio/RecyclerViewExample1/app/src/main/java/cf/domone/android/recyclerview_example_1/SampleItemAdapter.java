package cf.domone.android.recyclerview_example_1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dorian on 16. 3. 22..
 */
public class SampleItemAdapter extends RecyclerView.Adapter<SampleItemViewHolder> {
    private List<SampleItem> sampleItemList;
    private Context context;

    public SampleItemAdapter(Context context, List<SampleItem> sampleItemList) {
        this.sampleItemList = sampleItemList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return this.sampleItemList.size();
    }

    @Override
    public SampleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View sampleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sample_item, parent, false);
        return new SampleItemViewHolder(sampleItemLayout);
    }

    @Override
    public void onBindViewHolder(SampleItemViewHolder viewHolder, final int position) {
        viewHolder.tvNumber.setText("" + sampleItemList.get(position).number);
        viewHolder.tvTitle.setText(sampleItemList.get(position).title);

        viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(sampleItemList.get(position).address));
                context.startActivity(intent);
            }
        });
    }
}
