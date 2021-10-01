package com.quiz.web.utils;
/**
 *Pageable class contains params for pagination
 **/
public class Pageable {
    private int page;
    private int size;
    private String sort;
    private boolean order;

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSortWithOrder() {
        if (sort.length() > 0)
            return " order by " + sort;
        return sort;
    }

    public String getSort() {
        return sort;
    }

    public int getOffset() {
        return (page - 1) * size;
    }

    public boolean isOrder() {
        return order;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public static class Builder {
        private Pageable pageable =new Pageable();

        public Builder page(int page) {
            pageable.setPage(page);
            return this;
        }

        public Builder size(int size) {
            pageable.setSize(size);
            return this;
        }

        public Builder sort(String sort) {
            pageable.setSort(sort);
            return this;
        }

        public Builder ASC() {
            pageable.setOrder(true);
            return this;
        }

        public Builder DCS() {
            pageable.setOrder(false);
            return this;
        }

        public Pageable build() {
            return pageable;
        }
    }
}