package base;


import com.google.gson.GsonBuilder;

/**
 * 创建人:twoapes
 * 创建日期:2018/10/3
 * 创建时间:17:02
 * 创建作用:gson的实例
 */
public class HYZJson {
    /**
     * 注意:gson不支持Blob,Clob等数据库类型数据
     *
     * @param t t
     * @return 任意对象转换成json, 但不保存
     */

    public static String toJson(Object t) {
        if (t == null) return "";
        return new GsonBuilder().disableHtmlEscaping().create().toJson(t);
    }
}
