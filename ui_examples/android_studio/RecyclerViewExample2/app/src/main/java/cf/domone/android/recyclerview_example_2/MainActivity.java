package cf.domone.android.recyclerview_example_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define the recycler view as a grid layout where the number of columns are 3.
        RecyclerView rvPictures = (RecyclerView)findViewById(R.id.rvPictures);
        rvPictures.setLayoutManager(new GridLayoutManager(this, 3));

        PictureItemAdapter pictureItemAdapter = new PictureItemAdapter(
                this,
                getPictureFileNames(getPicturePath())
        );
        rvPictures.setAdapter(pictureItemAdapter);
    }

    /**
     * Get a directory path that has picture files.
     * @return The directory path. For example, it is "/storage/extSdCard/DCIM/Camera" in Galaxy A5.
     */
    private String getPicturePath() {
        return System.getenv("SECONDARY_STORAGE") + "/DCIM/Camera/";
    }

    /**
     * Get a list of picture item objects
     * @param directory The directory path that has picture files
     * @return List of the picture item objects
     */
    private List<PictureItem> getPictureFileNames(String directory) {
        File fileDirectory = new File(directory);
        String[] pictureFileNameArray = fileDirectory.list(pictureFileNameFilter);
        List<PictureItem> pictureItemList = new LinkedList<>();

        for (int i = 0; i < pictureFileNameArray.length; i++) {
            PictureItem item = new PictureItem(
                    fileDirectory.getAbsolutePath()
                    + File.separator
                    + pictureFileNameArray[i]
            );
            pictureItemList.add(item);
        }

        return pictureItemList;
    }

    private FilenameFilter pictureFileNameFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String filename) {
            if (filename.endsWith(".png")) {
                return true;
            } else if (filename.endsWith(".jpg")) {
                return true;
            }

            return false;
        }
    };
}
