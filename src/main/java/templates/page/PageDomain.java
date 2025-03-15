package templates.page;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author TWOAPES
 * 时间: 2021/11/28 21:51:51
 * 作用:分页
 */
@Slf4j
@Data
public class PageDomain {
    private Integer pageNum;
    private Integer pageSize;
    private String orderByColumn;
    private String isAsc;
    private String searchValue;
    private String drop;

    public String getOrderBy() {
        if (!StringUtils.hasLength(orderByColumn)) {
            return "";
        }

        return orderByColumn + " " + isAsc;
    }


    /**
     * 初始化分页
     */
    public void startPage() {
        log.debug("startPage:{}", PageHelper.startPage(pageNum, pageSize, getOrderBy()));
    }

    /**
     * @param list list
     * @return list分页
     */
    public <E> HashMap<String, Object> getTableDataInfo(List<E> list) {
        HashMap<String, Object> rspData = new HashMap<>();
        if (list != null) {
            rspData.put("code", 0);
            rspData.put("rows", list);
            PageInfo<?> pageInfo = new PageInfo<>(list);
            rspData.put("total", pageInfo.getTotal());
        }

        return rspData;
    }
}
