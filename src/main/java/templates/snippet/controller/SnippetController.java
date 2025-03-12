package templates.snippet.controller;

import base.HYZJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import templates.page.Ajax;
import templates.page.PageDomain;
import templates.page.TableDataInfo;
import templates.snippet.doman.SnippetCode;
import templates.snippet.mapper.ISnippetCodeMapper;

import java.util.*;

/**
 * @author TWOAPES
 * 时间: 2021/11/28 21:15:15
 * 作用:表格笔记逻辑
 */
@Controller
@RequestMapping("/snippet")
public class SnippetController {
    @Autowired
    private ISnippetCodeMapper snippetCodeMapper;
    private final Ajax ajax;

    public SnippetController() {
        ajax=new Ajax();
    }

    @GetMapping("/snippet")
    public String snippet(ModelMap map) {
        List<String> list = snippetCodeMapper.selectSnippetTypes();
        List<SnippetCode> snippetCodeTypeList = new ArrayList<>();
        for (String type : list) {
            SnippetCode snippetCode = new SnippetCode();
            snippetCode.setType(type);
            snippetCodeTypeList.add(snippetCode);
        }

        map.put("snippetCodeTypeList", snippetCodeTypeList);
        return "snippet/snippet";
    }

    @GetMapping("/web")
    @ResponseBody
    public String web(@RequestParam Long id) {
        StringBuilder stringBuilder = new StringBuilder();
        if (id != null) {
            SnippetCode snippetCode = snippetCodeMapper.selectSnippetCodeById(id);
            stringBuilder.append("<!DOCTYPE html>\n" + "<html lang=\"zh\">\n" + "\n" + "<head>\n" + "    <meta charset=\"utf-8\"/>\n" + "    <title>").append(snippetCode.getTitle()).append("</title>\n").append("</head>")
                    .append(snippetCode.getData()).append("</html>");
        }

        return stringBuilder.toString();
    }

    @GetMapping("/detail")
    public String detail(@RequestParam Long id, ModelMap map) {
        if (id != null) {
            SnippetCode snippetCode = snippetCodeMapper.selectSnippetCodeById(id);
            map.put("title", snippetCode.getTitle());
            map.put("type", snippetCode.getType());

            if (snippetCode.getPs() != null && !snippetCode.getPs().isBlank()) {
                map.put("ps", "ps:" + snippetCode.getPs());
            }
            map.put("data", snippetCode.getData());
        }

        return "snippet/detail";
    }

    @RequestMapping("/view")
    @ResponseBody
    public TableDataInfo snippetJson(@RequestBody PageDomain pageDomain) {
        PageDomain.startPage(pageDomain);
        List<SnippetCode> snippetCodes;
        if (pageDomain.getSearchValue() == null || pageDomain.getSearchValue().isEmpty()) {
            snippetCodes = snippetCodeMapper.selectSnippet(new SnippetCode());
        } else {
            snippetCodes = dropValue(pageDomain);
        }

        return PageDomain.getTableDataInfo(snippetCodes);
    }

    @PostMapping("/save")
    @ResponseBody
    public Ajax save(@RequestBody SnippetCode snippetCode) {
        if(snippetCode.getTitle() == null || snippetCode.getTitle().isEmpty()) {
            return ajax.warning("标题不能为空");
        }

        Calendar calendar = Calendar.getInstance();
        if (snippetCode.getId() == null) {
            snippetCode.setIdentification(calendar.getTimeInMillis());
            snippetCode.setId(calendar.getTimeInMillis());
            snippetCode.setCreateDate(calendar.getTime());
            int count = snippetCodeMapper.insertSnippetCode(snippetCode);
            return count > 0 ? ajax.success("保存成功", snippetCode) : ajax.warning("保存失败");
        } else {
            snippetCode.setModifyDate(calendar.getTime());
            int count = snippetCodeMapper.updateSnippetCode(snippetCode);
            return count > 0 ? ajax.success("修改成功", snippetCode) : ajax.warning("修改失败");
        }
    }

    @PostMapping("/deleteList")
    @ResponseBody
    public Ajax delete(@RequestBody List<SnippetCode> snippetCodeList) {
        int count = 0;
        for (SnippetCode code : snippetCodeList) {
            if (code.getState().equals("0")) {
                count += snippetCodeMapper.deleteSnippetCode(code.getId());
            }
        }

        if (count > 0) {
            return ajax.success("删除成功");
        } else {
            return ajax.warning("删除失败");
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public Ajax delete(@RequestBody SnippetCode snippetCode) {
        int count = 0;
        if (snippetCode.getState().equals("0")) {
            count += snippetCodeMapper.deleteSnippetCode(snippetCode.getId());
        }

        if (count > 0) {
            return ajax.success("删除成功");
        } else {
            return ajax.warning("删除失败");
        }
    }

    @PostMapping("/clock")
    @ResponseBody
    public Ajax clock(@RequestBody List<SnippetCode> snippetCodeList) {
        int count = 0;
        for (SnippetCode code : snippetCodeList) {
            code.setState("1");
            count += snippetCodeMapper.updateSnippetCode(code);
        }

        if (count > 0) {
            return ajax.success("锁定成功");
        } else {
            return ajax.warning("锁定失败");
        }
    }

    @PostMapping("/unClock")
    @ResponseBody
    public Ajax unClock(@RequestBody List<SnippetCode> snippetCodeList) {
        int count = 0;
        for (SnippetCode code : snippetCodeList) {
            code.setState("0");
            count += snippetCodeMapper.updateSnippetCode(code);
        }

        if (count > 0) {
            return ajax.success("解锁成功");
        } else {
            return ajax.warning("解锁失败");
        }
    }


    @PostMapping("/types")
    @ResponseBody
    public Ajax types(ModelMap map) {
        List<String> list = snippetCodeMapper.selectSnippetTypes();
        if (!list.isEmpty()) {
            map.put("jsonType", toJson(list));
            return ajax.success("获取类型成功", map);
        } else {
            return ajax.success("获取类型失败");
        }
    }

    @PostMapping("/ps")
    @ResponseBody
    public Ajax ps(ModelMap map) {
        List<String> list = snippetCodeMapper.selectSnippetPs();
        if (!list.isEmpty()) {
            map.put("jsonPs", toJson(list));
            return ajax.success("获取类型成功", map);
        } else {
            return ajax.success("获取类型失败");
        }
    }

    private String toJson(List<String> list) {
        List<Map<String, String>> jsonList = new ArrayList<>();
        for (String s : list) {
            Map<String, String> json = new HashMap<>();
            json.put("id", s);
            json.put("name", s);
            jsonList.add(json);
        }

        return HYZJson.toJson(jsonList);
    }

    private List<SnippetCode> dropValue(PageDomain pageDomain) {
        List<SnippetCode> snippetCodes;
        String drop = pageDomain.getDrop();
        SnippetCode snippetCode = new SnippetCode();
        if (drop != null && !drop.isEmpty()) {
            if (drop.contains("title")) {
                snippetCode.setTitle(pageDomain.getSearchValue());
            }


            if (drop.contains("type")) {
                snippetCode.setType(pageDomain.getSearchValue());
            }

            if (drop.contains("ps")) {
                snippetCode.setPs(pageDomain.getSearchValue());
            }

            if (drop.contains("state")) {
                if (pageDomain.getSearchValue().equals("未锁定")) {
                    pageDomain.setSearchValue("0");
                } else if (pageDomain.getSearchValue().equals("已锁定")) {
                    pageDomain.setSearchValue("1");
                } else {
                    //2不存在,使其不能查询到数据
                    pageDomain.setSearchValue("2");
                }
                snippetCode.setState(pageDomain.getSearchValue());
            }
        }
        snippetCodes = snippetCodeMapper.selectSnippet(snippetCode);

        return snippetCodes;
    }

    @PostMapping("/getId")
    @ResponseBody
    public Ajax getId(@RequestBody SnippetCode snippetCode) {
        snippetCode = snippetCodeMapper.selectSnippetCodeById(snippetCode.getId());
        if (snippetCode != null) {
            Map<String, String> json = new HashMap<>();
            json.put("id", String.valueOf(snippetCode.getId()));
            json.put("type", snippetCode.getType());
            json.put("title", snippetCode.getTitle());
            json.put("ps", snippetCode.getPs());
            json.put("data", snippetCode.getData());
            json.put("identification", String.valueOf(snippetCode.getIdentification()));
            json.put("state", snippetCode.getState());
            json.put("mould", snippetCode.getMould());
            return ajax.success("获取类型成功", json);
        } else {
            return ajax.success("获取类型失败");
        }
    }
}
