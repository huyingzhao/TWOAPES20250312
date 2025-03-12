package templates.page;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

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
     *
     * @param pageDomain pageDomain
     */
    public static void startPage(PageDomain pageDomain) {
        if (pageDomain != null) {
            String orderBy;
            int pageNum;
            int pageSize;

            if (pageDomain.getPageNum() == null) {
                pageNum = 1;
            } else {
                pageNum = pageDomain.getPageNum();
            }

            if (pageDomain.getPageSize() == null) {
                pageSize = 20;
            } else {
                pageSize = pageDomain.getPageSize();
            }

            if (pageDomain.getOrderBy() == null) {
                orderBy = "";
            } else {
                orderBy = pageDomain.getOrderBy();
            }

            log.debug("startPage:{}", PageHelper.startPage(pageNum, pageSize, orderBy));
        }
    }

    /**
     * @param list list
     * @return list分页
     */
    public static <E> TableDataInfo getTableDataInfo(List<E> list) {
        TableDataInfo rspData = new TableDataInfo(list);
        if (list != null) {
            rspData.setCode(0);
            rspData.setRows(list);
            PageInfo<?> pageInfo = new PageInfo<>(list);
            rspData.setTotal(pageInfo.getTotal());
        }

        return rspData;
    }
}
