package in.presencesoft.csvtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;


import java.util.ArrayList;

import in.presencesoft.csvtest.adapter.FactListAdapter;
import in.presencesoft.csvtest.model.FactListModel;
import in.presencesoft.csvtest.pojos.FactListPojo;
import in.presencesoft.csvtest.presenter.FactListPresenter;
import in.presencesoft.csvtest.utils.CommonClass;
import in.presencesoft.csvtest.view.FactListView;

public class MainActivity extends AppCompatActivity implements FactListView, SwipeRefreshLayout.OnRefreshListener {

    FactListPresenter mPresenter;
    RecyclerView rvFacts;

    SwipeRefreshLayout swipLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mPresenter = new FactListModel(this, this);

        //initialize
        rvFacts = findViewById(R.id.rvFactsList);
        swipLayout = findViewById(R.id.swipe_layout);

        //call api
        if (new CommonClass(this).isOnline()) {
            mPresenter.getFacts();
        } else {
            new CommonClass(this).showAlertForInternet();
        }



        swipLayout.setOnRefreshListener(this);
    }

    @Override
    public void displayFacts(ArrayList<FactListPojo> pojos, String abTitle) {

        //stop refreshing
        if (swipLayout.isRefreshing()) {
            swipLayout.setRefreshing(false);
        }
        //set actionbar title
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(abTitle);
        //set adapter to recyclerview
        FactListAdapter adapter = new FactListAdapter(this, pojos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvFacts.setLayoutManager(llm);
        rvFacts.setAdapter(adapter);
    }

    @Override
    public void failedToGet(String errorCode) {
        new CommonClass(this).showAlertWithTitle("Alert", "Something went wrong, Please try later.");

    }

    @Override
    public void onRefresh() {
        mPresenter.getFacts();
    }
}