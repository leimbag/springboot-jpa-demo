package com.leimbag.jpa.demo.bean.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author leimbag
 */
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 7395587874318185320L;
    private int page;
    private int pageSize;

    private long total = 0;

    private Collection<T> pagedData;


    public int getCount() {
        if (pagedData == null) {
            return 0;
        }

        return pagedData.size();
    }

    public int getPageTotal() {
        if (total <= 0) {
            return 0;
        }

        return (int) Math.ceil( (double) total / pageSize);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Collection<T> getPagedData() {
        if (pagedData == null) {
            pagedData = new ArrayList<>(0);
        }
        return pagedData;
    }

    public void setPagedData(Collection<T> pagedData) {
        this.pagedData = pagedData;
    }
}
