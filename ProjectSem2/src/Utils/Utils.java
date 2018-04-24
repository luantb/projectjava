/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.itrd.qlts.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author minhvufc
 */
public class Utils {

    /**
     * Hàm lấy về thời gian hiện tại
     *
     * @param type kiểu thời gian
     * @return Chuỗi thời gian trả về
     * @see DateFormat
     */
    public static String getTime(int type) {
        Calendar calendar = Calendar.getInstance(Constants.locale);
        DateFormat format = DateFormat.getDateTimeInstance(type, type, Constants.locale);
        return "[" + format.format(calendar.getTime()) + "] ";
    }
}
