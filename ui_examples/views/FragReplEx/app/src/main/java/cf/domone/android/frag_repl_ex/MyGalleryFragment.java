package cf.domone.android.frag_repl_ex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Fragment class to show My Gallery.
 */
public class MyGalleryFragment extends Fragment {

    public MyGalleryFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of MyGalleryFragment fragment.
     * @return A new instance of fragment MyGalleryFragment.
     */
    public static MyGalleryFragment newInstance() {
        MyGalleryFragment fragment = new MyGalleryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_gallery, container, false);
    }

}
