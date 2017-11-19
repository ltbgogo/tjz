package com.abc.tjz.module.quan.controller;

import com.abc.tjz.entity.QuickEntry;
import com.abc.tjz.util.misc.AbstractController;
import com.abc.tjz.util.vo.ResultDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.abc.tjz.repository.RepoFactory.rf;

@RestController
@RequestMapping("/action/quantb")
public class TbController extends AbstractController {

    @GetMapping("/showIndex")
    public ModelAndView showIndex() {
        return new ModelAndView(this.getViewRoot() + "/index");
    }

    @GetMapping("/getQuickEntries")
    public ResultDto<List<QuickEntry>> getQuickEntries() {
        List<QuickEntry> entries = rf.getQuickEntryRepo().findByCategoryOrderBySeq("quantb");
        return ResultDto.succeed("", entries);
    }
}
