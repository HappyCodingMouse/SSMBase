package com.springmvc.utils;

import java.io.Serializable;
import java.util.List;

public class MyPager<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 每页条数
     */
    private int pageSize = 10;

    /**
     * 第几页
     */
    private int pageIndex = 1;

    /**
     * 总的记录数
     */
    private long totalRowCount;

    /**
     * 数据
     */
    private List<T> data;

    /**
     * 是否有上一页
     */
    private boolean hasPrevPage;


    /**
     * 是否有下一页
     */
    private boolean hasNextPage;

    /**
     * 是否有下一页
     */
    private long totalPageCount;


    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }


    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }


    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }


    /**
     * @return the totalRowCount
     */
    public long getTotalRowCount() {
        return totalRowCount;
    }


    /**
     * @param totalRowCount the totalRowCount to set
     */
    public void setTotalRowCount(long totalRowCount) {
        this.totalRowCount = totalRowCount;
    }


    /**
     * @return the data
     */
    public List<T> getData() {
        return data;
    }


    /**
     * @param data the data to set
     */
    public void setData(List<T> data) {
        this.data = data;
    }


    /**
     * @return the totalPageCount
     */
    public long getTotalPageCount() {
        if(this.totalRowCount % this.pageSize == 0){
            return this.totalRowCount / this.pageSize;
        }else{
            return this.totalRowCount / this.pageSize + 1;
        }
    }


    /**
     * @return the hasPrevPage
     */
    public boolean getHasPrevPage() {
        //什么情况下有上一页，如果当前页>1
        return this.pageIndex > 1;
    }


    /**
     * @return the hasNextPage
     */
    public boolean getHasNextPage() {
        //如果当前页和总的页数一样，则没有下一页，其它情况都有下一页
        return this.pageIndex == this.getTotalPageCount() ? false : true;
    }

    public MyPager(){

    }

    /**
     * 有参构造方法
     * @param pageSize 每页的条数
     * @param pageIndex 当前页码
     * @param totalRowCount 总的记录数
     * @param data 数据
     */
    public MyPager(int pageSize, int pageIndex, long totalRowCount, List<T> data) {
        super();
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalRowCount = totalRowCount;
        this.data = data;
        this.hasNextPage = this.getHasNextPage();
        this.hasPrevPage = this.getHasPrevPage();
        this.totalPageCount = this.getTotalPageCount();
    }
}

