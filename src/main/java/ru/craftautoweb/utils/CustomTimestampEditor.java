package ru.craftautoweb.utils;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Администратор on 02.12.2016.
 */
public class CustomTimestampEditor extends PropertyEditorSupport {
    private final DateFormat dateFormat;
    private final boolean isTime;

    public CustomTimestampEditor(boolean isTime) {
        if (isTime) {
            this.dateFormat = new SimpleDateFormat("HH:mm");
        } else {
            this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        }
        this.isTime = isTime;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasText(text)) {
            this.setValue((Object) null);
        } else {
            try {
                // Формируем дату
                Date parsedDate = dateFormat.parse(text);
                // Если время, ставим дату на 1899-12-30, время оставляем
                Timestamp timestamp;
                if(isTime) {
                    timestamp = new java.sql.Timestamp(0, 0, -1, parsedDate.getHours(), parsedDate.getMinutes(), 0, 0);
                } else {
                    timestamp = new java.sql.Timestamp(parsedDate.getTime());
                }
                // Устанавливаем значение
                this.setValue(timestamp);
            } catch (Exception e) {//this generic but you can control another types of exception
                throw new IllegalArgumentException("Could not parse date: " + text);
            }
        }
/*
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            this.setValue((Object) null);
        } else {
            if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
                throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
            }

            try {
                this.setValue(this.dateFormat.parse(text));
            } catch (ParseException var3) {
                throw new IllegalArgumentException("Could not parse date: " + var3.getMessage(), var3);
            }
        }
*/
    }

    public String getAsText() {
        Timestamp value = (Timestamp) this.getValue();
        String ret = "";
        if (value != null) {
            Date date = new Date();
            date.setTime(value.getTime());
            ret = this.dateFormat.format(date);
        }
        return ret;
    }
}
