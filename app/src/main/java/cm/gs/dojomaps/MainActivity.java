package cm.gs.dojomaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos el fragmento
        fragment = new MapFragment();

        //Abrimos el fragmento
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

    }
}