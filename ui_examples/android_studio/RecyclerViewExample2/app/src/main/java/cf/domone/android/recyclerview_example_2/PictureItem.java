package cf.domone.android.recyclerview_example_2;

/**
 * Item class to hold picture data.
 */
public class PictureItem {

    /** This field means the path of a picture file. */
    private String path;

    public PictureItem(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
