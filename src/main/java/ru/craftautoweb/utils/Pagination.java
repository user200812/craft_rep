package ru.craftautoweb.utils;

/**
 * Created by User on 19.11.2016.
 */
public class Pagination {
    public int pageSize;
    public int activePage;
    public int countRecs;
    public int numPages;
    public int skip() {
        return (activePage - 1) * pageSize;
    }
    public Pagination()
    {
        pageSize = 10;
        activePage = 1;
        countRecs = 0;
        numPages = 1;
    }

    public Pagination(int pageSize, int activePage, int countRecs)
    {
        this.pageSize = pageSize;
        this.countRecs = countRecs;
        if (countRecs == 0)
            numPages = 1;
        else
            numPages = countRecs / pageSize + (countRecs % pageSize == 0 ? 0 : 1);
        this.activePage = activePage <= numPages ? activePage : numPages;
    }

    public void SetActivePage(int activePage)
    {
        this.activePage = activePage;
    }

    public String GetPagination()
    {
        boolean isPrevSet = false;
        String items = "<li title=\"Предыдущая страница\""
                + (activePage == 1 ? " class=disabled><a" : "><a href=\"#\" onclick=\"javascript:prevPage();return false;\"")
                + ">&laquo;</a></li>";
        for (int i = 1; i <= numPages; i++)
        {
            if (i == 1 || i == numPages || Math.abs(activePage - i) < 2 ||
                    (i < activePage && activePage <= 3) ||
                    (activePage > numPages - 3 && i > activePage))
            {
                items = items + "<li"
                        + (i == activePage ? " class=\"active\"" : "")
                        + "><a href=\"#\" onclick=\"javascript:setActivePage(" + i + ");return false;\">"
                        + i
                        + "</a></li>";
                isPrevSet = true;
            }
            else if (isPrevSet)
            {
                isPrevSet = false;
                items = items + "<li style=\"cursor: default;\"><a>...</a></li>";
            }
        }
        items = items + "<li title=\"Следующая страница\""
                + (activePage == numPages ? " class=disabled><a" : "><a href=\"#\" onclick=\"javascript:nextPage();return false;\"")
                + ">&raquo;</a></li>";
        return items;
    }
}
