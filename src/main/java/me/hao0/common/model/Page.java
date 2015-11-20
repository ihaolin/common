package me.hao0.common.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页对象
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 7544274721272147458L;

    private Long total;

    private List<T> data;

    public Page(Long total, List<T> data){
        this.total = total;
        this.data = data;
    }

    public static <T> Page<T> empty() {
        return new Page<T>(0L, Collections.<T>emptyList());
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}