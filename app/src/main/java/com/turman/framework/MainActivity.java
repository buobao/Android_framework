package com.turman.framework;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.turman.framework.components.DaggerMainComponent;
import com.turman.framework.components.MainComponent;
import com.turman.framework.entity.MapResult;
import com.turman.framework.modules.MainModule;
import com.turman.framework.utils.LocationUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dqf on 2016/4/13.
 */
public class MainActivity extends AppCompatActivity implements MainView {
    @Bind({R.id.place_1,R.id.place_2,R.id.place_3,R.id.place_4,R.id.place_5,R.id.place_6,R.id.place_7})
    List<TextView> places;

    private MainComponent component;

    @Inject
    protected MainPresenter mainPresenter;
    @Inject
    protected LocationUtils locationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        component = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
        component.inject(this);

        Location location = locationUtils.getLocation(this);

        Map<String,Object> sub_params = new HashMap<>();
        sub_params.put("latlng", location.getLatitude() + "," + location.getLongitude());
        sub_params.put("sensor", true);
        sub_params.put("language", "zh-CN");
        mainPresenter.loadEntity(sub_params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mainPresenter.cancleLoad();
    }

    @OnClick({R.id.place_1, R.id.place_2, R.id.place_3, R.id.place_4, R.id.place_5, R.id.place_6, R.id.place_7})
    public void onClick(View view) {
        String msg = "";
        switch (view.getId()) {
            case R.id.place_1:
                msg = places.get(0).getText().toString();
                break;
            case R.id.place_2:
                msg = places.get(1).getText().toString();
                break;
            case R.id.place_3:
                msg = places.get(2).getText().toString();
                break;
            case R.id.place_4:
                msg = places.get(3).getText().toString();
                break;
            case R.id.place_5:
                msg = places.get(4).getText().toString();
                break;
            case R.id.place_6:
                msg = places.get(5).getText().toString();
                break;
            case R.id.place_7:
                msg = places.get(6).getText().toString();
                break;
        }
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPlaces(MapResult result) {
        if (result != null) {
            int i = 0;
            for (TextView t:places){
                if (i == result.results.get(0).address_components.size()){
                    break;
                }
                t.setText(result.results.get(0).address_components.get(i).short_name);
                i++;
            }
        }
    }
}
