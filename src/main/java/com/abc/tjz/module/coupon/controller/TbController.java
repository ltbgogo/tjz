package com.abc.tjz.module.coupon.controller;

import com.abc.tjz.entity.CouponTb;
import com.abc.tjz.entity.QuickEntry;
import com.abc.tjz.util.misc.AbstractController;
import com.abc.tjz.util.vo.ResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.abc.tjz.repository.RepoFactory.rf;

@RestController
@RequestMapping("/action/coupontb")
public class TbController extends AbstractController {

    @GetMapping("/showIndex")
    public ModelAndView showIndex() {
        return new ModelAndView(this.getViewRoot() + "/index");
    }

    @GetMapping("/getQuickEntries")
    public ResultDto<List<QuickEntry>> getQuickEntries() {
        List<QuickEntry> entries = rf.getQuickEntryRepo().findByCategoryOrderBySeq("coupontb");
        return ResultDto.succeed("", entries);
    }

    @GetMapping("/getCoupons")
    public ResultDto<Page<CouponTb>> getCoupons() {
        Page<CouponTb> page = rf.getCouponTbRepo().findAll(new PageRequest(0, 10));
        return ResultDto.succeed("", page);
    }
}
