package com.example.guiderunner2;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpeedRunsFragment extends Fragment {

    private ListView list_view_library;
    private List<SpeedRuns> speedrunslist = new ArrayList<>();

    public String URL = "http://10.0.2.2:3000/records";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speed_runs, container, false);
        init(view);
        RequestTask task = new RequestTask(URL, "GET");
        task.execute();
        return view;
    }
    public void init(View view) {
        list_view_library = view.findViewById(R.id.list_view_library);
    }

    private class SpeedRunsAdapter extends ArrayAdapter<SpeedRuns> {

        public SpeedRunsAdapter() {
            super(getActivity(),  R.layout.records_adapter, speedrunslist);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.records_adapter, null, false);
            SpeedRuns actualSpeedruns = speedrunslist.get(position);
            TextView username = view.findViewById(R.id.username_records);
            TextView gamename = view.findViewById(R.id.gamename_records);
            TextView time = view.findViewById(R.id.time_records);
            TextView platform = view.findViewById(R.id.platform_records);
            TextView difficulty = view.findViewById(R.id.difficulty_records);
            TextView youtubelink = view.findViewById(R.id.youtubelink_records);


            username.setText(actualSpeedruns.getUsername());
            gamename.setText(actualSpeedruns.getGamename());
            time.setText(actualSpeedruns.getTime());
            platform.setText(actualSpeedruns.getPlatform());
            difficulty.setText(actualSpeedruns.getDifficulty());
            youtubelink.setText(actualSpeedruns.getYoutubelink());


            return view;
        }
    }
    private class RequestTask extends AsyncTask<Void, Void, Response> {
        String requestUrl;
        String requestType;
        String requestParams;

        public RequestTask(String requestUrl, String requestType, String requestParams) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
            this.requestParams = requestParams;
        }


        public RequestTask(String requestUrl, String requestType) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl,null);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams,null);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams,null);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl + "/" + requestParams,null);
                        break;
                }
            } catch (IOException e) {

            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            Gson converter = new Gson();
            if (response.getResponseCode() >= 400) {
                Toast.makeText(getActivity(),
                        "An error occurred while processing the request!", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                switch (requestType) {
                    case "GET":
                        SpeedRunsListHelper speedrunArray = converter.fromJson(
                                response.getContent(), SpeedRunsListHelper.class);
                        speedrunslist.clear();
                        speedrunslist.addAll(speedrunArray.getSpeedruns());
                        list_view_library.setAdapter(new SpeedRunsAdapter());


                        break;
                }
            }

        }
    }
}