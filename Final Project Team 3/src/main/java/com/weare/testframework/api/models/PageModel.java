package com.weare.testframework.api.models;

public class PageModel {
    private int index;
    private boolean next;
    private String searchParam1;
    private String searchParam2;
    private int size;

    public PageModel(int index, boolean next, String searchParam1, String searchParam2, int size) {
        this.index = index;
        this.next = next;
        this.searchParam1 = searchParam1;
        this.searchParam2 = searchParam2;
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public boolean getNext() {
        return next;
    }

    public String getSearchParam1() {
        return searchParam1;
    }

    public String getSearchParam2() {
        return searchParam2;
    }

    public int getSize() {
        return size;
    }
}
