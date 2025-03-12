package templates.snippet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import templates.page.PageDomain;
import templates.snippet.doman.SnippetCode;
import templates.snippet.mapper.ISnippetCodeMapper;

import java.util.List;

/**
 * @author add by huyingzhao 2025-02-19 23:00
 */
@Controller
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private ISnippetCodeMapper snippetCodeMapper;

    @GetMapping("/notes")
    public String notes(ModelMap map,@RequestParam(required = false) String title, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize) {
        SnippetCode pageDomain = new SnippetCode();
        pageDomain.setTitle(title);
        int countTotalSnippets = snippetCodeMapper.countTotalSnippets(pageDomain);
        int totalPages = (int) Math.ceil((double) countTotalSnippets / pageSize);

        pageNum = Math.max(1, Math.min(pageNum, totalPages)); // Ensure pageNum is within bounds
        pageDomain.setPageNum((pageNum-1)*pageSize);
        pageDomain.setPageSize(pageSize);

        List<SnippetCode> notes = snippetCodeMapper.selectSnippetWithPagination(pageDomain);
        map.put("notes", notes);
        map.put("title", title);
        map.put("currentPage", pageNum);
        map.put("countTotalSnippets", countTotalSnippets);
        map.put("totalPages", totalPages);

        return "notes/notes";
    }
}
