package in.presencesoft.csvtest.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.presencesoft.csvtest.pojos.FactListPojo;
import in.presencesoft.csvtest.presenter.FactListPresenter;
import in.presencesoft.csvtest.utils.CommonClass;
import in.presencesoft.csvtest.view.FactListView;

public class FactListModel implements FactListPresenter {
    private FactListView mFactListView;
    private Context con;
    private ArrayList<FactListPojo> pojos = new ArrayList<>();

    public FactListModel(FactListView view, Context con) {
        this.mFactListView = view;
        this.con = con;

    }

    private String getData() {
        try {
            CommonClass cc = new CommonClass(con);
            return cc.oKHttpGet("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void getFacts() {
        new getDataFromServer().execute();
    }


    @SuppressLint("StaticFieldLeak")
    private class getDataFromServer extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... strings) {
            return getData();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (result != null) {
                    parseResult(result);
                } else {
                    mFactListView.failedToGet("404");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void parseResult(String response) {
        try {
            JSONObject jObj = new JSONObject(response);
            pojos.clear();
            JSONArray jsonArray = jObj.getJSONArray("rows");

            String strTitle=jObj.getString("title");
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject obj = jsonArray.getJSONObject(j);

                FactListPojo pojoList = new FactListPojo();
                pojoList.setTitle(obj.getString("title"));
                pojoList.setDescription(obj.getString("description"));
                pojoList.setImagehref(obj.getString("imageHref"));

                // add data in pojo class
                pojos.add(pojoList);
            }

            //show facts list
            mFactListView.displayFacts(pojos,strTitle);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
