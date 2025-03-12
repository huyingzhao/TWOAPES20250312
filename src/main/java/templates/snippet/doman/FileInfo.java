package templates.snippet.doman;

/**
 * @author add by huyingzhao 2025-02-23 21:03
 */
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class FileInfo {
    private Integer id;
    private String fileName;
    private String fileType;
    private String originalFileName;
    private Long fileSize;
    private String md5Checksum;
    private Date modificationTime;
    private Date uploadTime;
    private String operation;
    private Date createTime;
    private Date updateTime;
}
