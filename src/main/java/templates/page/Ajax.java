package templates.page;

import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.Locale;

/**
 * @author twoapes
 * 2022-02-10 9:46
 */
public class Ajax extends HashMap<String, Object> {
    private enum AjaxEnum {
        ERROR, WARNING, SUCCESS
    }

    /**
     * @param msg msg
     * @return 失败
     */
    public Ajax error(String msg) {
        this.clear();
        this.put("state", Ajax.AjaxEnum.ERROR.name().toLowerCase(Locale.ROOT));
        this.put("msg", msg);
        return this;
    }

    /**
     * @param msg msg
     * @return 失败
     */
    public Ajax error(String msg, ModelMap map) {
        Ajax ajax = error(msg);
        ajax.putAll(map);
        return this;
    }


    /**
     * @param msg msg
     * @return 警告
     */
    public Ajax warning(String msg) {
        this.clear();
        this.put("state", Ajax.AjaxEnum.WARNING.name().toLowerCase(Locale.ROOT));
        this.put("msg", msg);
        return this;
    }

    /**
     * @param msg msg
     * @return 警告
     */
    public Ajax warning(String msg, ModelMap map) {
        Ajax ajax = warning(msg);
        ajax.putAll(map);
        return this;
    }

    /**
     * @param msg msg
     * @return 成功
     */
    public Ajax success(String msg) {
        this.clear();
        this.put("state", Ajax.AjaxEnum.SUCCESS.name().toLowerCase(Locale.ROOT));
        this.put("msg", msg);
        return this;
    }

    /**
     * @param msg msg
     * @return 成功
     */
    public Ajax success(String msg, ModelMap map) {
        Ajax ajax = success(msg);
        ajax.putAll(map);
        return this;
    }

    /**
     * @param msg msg
     * @return 成功
     */
    public Ajax success(String msg, Object object) {
        Ajax ajax = success(msg);
        ajax.put("data",object);
        return this;
    }

    /**
     * @param key   key
     * @param value value
     * @return Ajax
     */
    @Override
    public Ajax put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
