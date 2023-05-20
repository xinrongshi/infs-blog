package com.infs.blog.bean;

import java.util.List;

/**
 * page entity
 * @Author: Lexi
 * @Date:  2023/04/30
 */
public class Page<T> {

    private Integer number; //Current page
    private Integer size; //Return data size
    private Integer totalPages; //Total page
    private Integer totalCount; //Total page
    private List<T> content; //Data entity
    private Boolean first;
    private Boolean last;

    public Page(Integer number, Integer size, Integer totalCount, List<T> data) {
        this.number = number;
        this.size = size;
        this.totalCount = totalCount;
        this.content = data;
        this.totalPages = totalCount % size == 0 ? totalCount / size : (totalCount / size) + 1;
        this.first = number == 1;
        this.last = number.equals(this.totalPages) || number > this.totalPages;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }
}
