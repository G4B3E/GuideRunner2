package com.example.guiderunner2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpeedRunsFragment extends Fragment {

    private ListView list_view_library;
    private final List<Records> recordsList = new ArrayList<>();

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

    private class RecordsAdapter extends ArrayAdapter<Records> {

        public RecordsAdapter() {
            super(getActivity(), R.layout.records_adapter, recordsList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.records_adapter, null, false);
            Records actualRecords = recordsList.get(position);
            TextView username = view.findViewById(R.id.username_records);
            TextView gamename = view.findViewById(R.id.gamename_records);
            TextView time = view.findViewById(R.id.time_records);
            TextView platform = view.findViewById(R.id.platform_records);
            TextView difficulty = view.findViewById(R.id.difficulty_records);
            TextView youtubelink = view.findViewById(R.id.youtubelink_records);


            username.setText(actualRecords.getUsername());
            gamename.setText(actualRecords.getGamename());
            time.setText(actualRecords.getTime());
            platform.setText(actualRecords.getPlatform());
            difficulty.setText(actualRecords.getDifficulty());
            youtubelink.setText(actualRecords.getYoutubelink());


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
                        response = RequestHandler.get(requestUrl, null);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams, null);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams, null);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl + "/" + requestParams, null);
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
            } else {
                switch (requestType) {
                    case "GET":
                        RecordsListHelper recordsArray = converter.fromJson(
                                response.getContent(), RecordsListHelper.class);
                        recordsList.clear();
                        recordsList.addAll(recordsArray.getRecords());
                        Log.e("valami", "" + recordsList.size());
                        list_view_library.setAdapter(new RecordsAdapter());
                        break;
                }
            }

        }
    }
}