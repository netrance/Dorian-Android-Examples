package cf.domone.android.recyclerview_example_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rvSample = (RecyclerView)findViewById(R.id.rvSample);
        this.rvSample.setLayoutManager(new LinearLayoutManager(this));

        SampleItemAdapter adapter = new SampleItemAdapter(this, SampleItem.getSampleItemList());
        this.rvSample.setAdapter(adapter);
    }
}
