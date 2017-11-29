package ru.craftautoweb.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Администратор on 22.11.2016.
 */
public class CraftUtils {
    public static int GetLogonUserId(String user) {
        return 0;
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static String tdcell(Double val) {
        return tdcell(val, null, null);
    }

    public static String tdcell(Double val, String href) {
        return tdcell(val, href, null);
    }

    public static String tdcell(Double val, String href, String _class) {
        return tdcell(val == null ? "": String.format("%.2f", val), href, _class);
    }

    public static String tdcell(Timestamp date) {
        return tdcell(date, null, null);
    }

    public static String tdcell(Timestamp date, String href) {
        return tdcell(date, href, null);
    }

    public static String tdcell(Timestamp date, String href, String _class) {
        return tdcell(date == null ? "" : new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date), href, _class);
    }

    public static String tdcell(String text) {
        return tdcell(text, null, null);
    }

    public static String tdcell(String text, String href) {
        return tdcell(text, href, null);
    }

    public static String tdcell(String text, String href, String _class) {
        String td;
        // Проверяем параметры на null
        text = text == null ? "": text;
        href = href == null ? "": href;
        _class = _class == null ? "": _class;

        // Устанавливаем класс ячейки
        if (!_class.isEmpty()) {
            td = "<td class='" + _class;
        } else {
            td = "<td class='";
        }

        // Устанавливаем текст и ссылку ячейки
        if (!href.isEmpty()) {
            // Задан текст и ссылка
            td +=  " onclick' onclick=document.location=$('base').attr('href')+'" + href + "'>" + text + "</a></td>";
        } else {
            // Не задана ссылка
            td += "'>" + text + "</td>";
        }

        return td;
    }
}
