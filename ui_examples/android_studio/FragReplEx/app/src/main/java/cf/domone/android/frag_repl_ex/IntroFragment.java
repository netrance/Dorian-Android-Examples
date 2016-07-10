package cf.domone.android.frag_repl_ex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Fragment class to show Intro.
 */
public class IntroFragment extends Fragment {
    public IntroFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of IntroFragment fragment.
     * @return A new instance of fragment IntroFragment.
     */
    public static IntroFragment newInstance() {
        IntroFragment fragment = new IntroFragment();
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
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }

}
