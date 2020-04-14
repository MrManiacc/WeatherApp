package me.jraynor.api.requests;

import com.sun.jndi.toolkit.url.Uri;
import me.jraynor.helpers.Pair;

/**
 * This will send a request to the me.jraynor.api
 */
public class WeatherRequest {
    private static final String API_KEY = "l311jayQg4s4DwvwGUsRaQus6Ly6wZQ5";
    private static final String BASE_URL = "https://api.climacell.co/v3";
    private static final String CONTENT_TYPE = "application/JSON";

    private Uri requestUri;
    private String requestQuery;

    public WeatherRequest(Pair<String>... queries) {

        for (int i = 0; i < queries.length; i++) {
            Pair<String> query = queries[i];
            if (i == 0)
                requestQuery = "?";

        }
    }
}
