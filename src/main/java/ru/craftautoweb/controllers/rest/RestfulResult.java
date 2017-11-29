package ru.craftautoweb.controllers.rest;

/**
 * Created by User on 19.11.2016.
 */
public class RestfulResult {
    private String pagination;
    private String rows;
    private int numPages;
    private int activePage;

    public RestfulResult(String pagination, String rows, int numPages, int activePage) {
        this.pagination = pagination;
        this.rows = rows;
        this.numPages = numPages;
        this.activePage = activePage;
    }

    public String getPagination() {
        return pagination;
    }

    public String getRows() {
        return rows;
    }

    public int getNumPages() {
        return numPages;
    }

    public int getActivePage() {
        return activePage;
    }
}
