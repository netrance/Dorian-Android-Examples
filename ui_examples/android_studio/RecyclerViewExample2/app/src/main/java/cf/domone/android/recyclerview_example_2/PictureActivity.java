package cf.domone.android.recyclerview_example_2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * This activity is defined to show a picture file.
 */
public class PictureActivity extends AppCompatActivity {

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        ImageView ivPicture = (ImageView)findViewById(R.id.ivPicture);
        String imagePath = getIntent().getStringExtra("image_path");
        this.bitmap = BitmapFactory.decodeFile(imagePath);
        ivPicture.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        this.bitmap.recycle();

        super.onDestroy();
    }
}
