package cf.domone.android.frag_repl_ex;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Fragment class to show my GitHub site.
 */
public class MyGitHubFragment extends Fragment {

    public MyGitHubFragment() {
    }

    /**
     * Factory method to create a new instance of MyGitHubFragment fragment.
     * @return A new instance of fragment MyGitHubFragment.
     */
    public static MyGitHubFragment newInstance() {
        MyGitHubFragment fragment = new MyGitHubFragment();
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
        return inflater.inflate(R.layout.fragment_my_git_hub, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Loads my GitHub web page.
        WebView wvMyGitHub = (WebView)view.findViewById(R.id.wvMyGitHub);
        wvMyGitHub.getSettings().setJavaScriptEnabled(true);
        wvMyGitHub.setWebViewClient(new WebViewClient());
        wvMyGitHub.loadUrl("http://github.com/netrance");
    }
}
