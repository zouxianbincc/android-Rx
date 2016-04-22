package cc.chono.yopper.rxandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cc.chono.yopper.rxandroid.PresenterLayer.IRxView;
import cc.chono.yopper.rxandroid.PresenterLayer.RxPresenter;

public class MainActivity extends AppCompatActivity implements IRxView{



    private RxPresenter mRxPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRxPresenter=new RxPresenter(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                mRxPresenter.Create();

                mRxPresenter.From();

                mRxPresenter.Interval();

                mRxPresenter.just();

                mRxPresenter.Range();

                mRxPresenter.Repeat();

                mRxPresenter.Timer();

            }
        });










    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Object object) {

    }

    @Override
    public void onNext(Object object) {

    }

    @Override
    public void range(Object object) {

    }

    @Override
    public void just1(Object object) {

    }

    @Override
    public void just2(Object object) {

    }

    @Override
    public void just3(Object object) {

    }
}
