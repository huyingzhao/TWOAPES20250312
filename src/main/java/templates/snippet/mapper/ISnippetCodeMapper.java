package templates.snippet.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import templates.page.PageDomain;
import templates.snippet.doman.SnippetCode;

import java.util.List;

/**
 * @author TWOAPES
 * 时间: 2021/11/21 22:09:09
 * 作用:
 */
@Mapper
public interface ISnippetCodeMapper {
    List<SnippetCode> selectSnippetCode();
    SnippetCode selectSnippetCodeById(Long id);
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSnippetCode(SnippetCode snippetCode);
    int updateSnippetCode(SnippetCode snippetCode);
    Integer deleteSnippetCode(Long id);
    @Select("select distinct s.type from SOURCE_CODE s order by s.type")
    List<String> selectSnippetTypes();
    @Select("select ps from (select  s.ps as ps from SOURCE_CODE s) where ps is not null")
    List<String> selectSnippetPs();
    List<SnippetCode> selectSnippet(SnippetCode snippetCode);
    List<SnippetCode> selectSnippetWithPagination(PageDomain pageDomain);
    int countTotalSnippets();
}
