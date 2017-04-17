package cn.shop.model;

import java.util.List;

public class Page
{
    List<?> list = null;
    int total = 0;
    int pageSize = 0;
    int currentPage = 0;

    public Page()
    {
    }

    public Page(List<?> list, int pageSize, int currentPage, int total)
    {
        this.list = list;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.total = total;
    }

    public List<?> getList()
    {
        return list;
    }

    public int getTotal()
    {
        return total;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setList(List<?> list)
    {
        this.list = list;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }
}
