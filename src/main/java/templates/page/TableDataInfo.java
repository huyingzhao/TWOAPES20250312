package templates.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author TWOAPES
 * 时间: 2021/11/28 21:45:45
 * 作用:
 */
@Data
public class TableDataInfo implements Serializable {
    private long total;
    private List<?> rows;
    private int code;

    public TableDataInfo(List<?> list) {
        this.rows = list;
    }
}
