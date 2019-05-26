
package ru.vsu.aviaticketsback.ticketssearch.models.kiwi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KiwiResponse {

    @JsonProperty("search_params")
    private SearchParams searchParams;

    private List<Datum> data = null;

    @JsonProperty("search_id")
    private String searchId;

    public KiwiResponse() {
    }

    public KiwiResponse(SearchParams searchParams, List<Datum> data, String searchId) {
        super();
        this.searchParams = searchParams;
        this.data = data;
        this.searchId = searchId;
    }

    public SearchParams getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(SearchParams searchParams) {
        this.searchParams = searchParams;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }
}
