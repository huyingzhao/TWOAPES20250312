package templates.snippet.doman;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author TWOAPES
 * 时间: 2021/11/28 13:34:34
 * 作用:
 */
@Data
@ToString
public class SnippetCode{
    private Long id;
    private String data;
    private String ps;
    private String mould;
    private String title;
    private String type;
    private String state;
    private long identification;
    private Date modifyDate;
    private Date createDate;
}
