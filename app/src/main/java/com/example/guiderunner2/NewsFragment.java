package com.example.guiderunner2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    private ListView list_view_news;
    private final List<News> newslist = new ArrayList<>();

    public String URL = "http://10.0.2.2:3000/news";

    private Button Refresh;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        init(view);
        RequestTask task = new RequestTask(URL, "GET");
        task.execute();
        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RefreshScreen.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void init(View view) {
        list_view_news = view.findViewById(R.id.list_view_news);
        Refresh = view.findViewById(R.id.Refresh);
    }

    private class NewsAdapter extends ArrayAdapter<News> {

        public NewsAdapter() {
            super(getActivity(), R.layout.news_adapter, newslist);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.news_adapter, null, false);
            News actualNews = newslist.get(position);
            TextView gamename = view.findViewById(R.id.gamename);
            TextView title = view.findViewById(R.id.title);
            TextView content = view.findViewById(R.id.content);
            TextView source = view.findViewById(R.id.source);
            TextView date = view.findViewById(R.id.date);


            gamename.setText(actualNews.getGamename());
            title.setText(actualNews.getTitle());
            content.setText(actualNews.getContent());
            source.setText("Source : " + actualNews.getSource());
            date.setText("Date : " + actualNews.getDate());


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
                        NewsListHelper peopleArray = converter.fromJson(
                                response.getContent(), NewsListHelper.class);
                        newslist.clear();
                        newslist.addAll(peopleArray.getNews());
                        list_view_news.setAdapter(new NewsAdapter());
                        break;
                }
            }

        }
    }


}


