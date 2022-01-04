package cf.domone.android.frag_repl_ex;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout layoutMain;
    private TextView tvIntro;
    private TextView tvMyGitHub;
    private TextView tvMyGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get views.
        this.layoutMain = (DrawerLayout)findViewById(R.id.layoutMain);
        this.tvIntro = (TextView)findViewById(R.id.tvIntro);
        this.tvMyGitHub = (TextView)findViewById(R.id.tvMyGitHub);
        this.tvMyGallery = (TextView)findViewById(R.id.tvMyGallery);

        // Set listeners.
        this.tvIntro.setOnClickListener(tvIntroClickListener);
        this.tvMyGitHub.setOnClickListener(tvMyGitHubClickListener);
        this.tvMyGallery.setOnClickListener(tvMyGalleryClickListener);

        showIntro();
    }

    private View.OnClickListener tvIntroClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            layoutMain.closeDrawers();
            showIntro();
        }
    };

    private View.OnClickListener tvMyGitHubClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            layoutMain.closeDrawers();
            showMyGitHub();
        }
    };

    private View.OnClickListener tvMyGalleryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            layoutMain.closeDrawers();
            showMyGallery();
        }
    };

    private void showIntro() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.layoutContent, IntroFragment.newInstance());
        ft.commit();
    }

    private void showMyGitHub() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.layoutContent, MyGitHubFragment.newInstance());
        ft.commit();
    }

    private void showMyGallery() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.layoutContent, MyGalleryFragment.newInstance());
        ft.commit();
    }
}
