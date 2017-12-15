package com.abc.tjz.module.app.controller;

import com.abc.tjz.entity.Category;
import com.abc.tjz.entity.CouponTb;
import com.abc.tjz.module.app.dto.CouponTbCondiDto;
import com.abc.tjz.util.misc.AbstractController;
import com.abc.tjz.util.dto.ResultDto;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

import static com.abc.tjz.repository.RepoFactory.rf;

@RestController
@RequestMapping("/action/app/coupontb")
public class CouponTbController extends AbstractController {

    @GetMapping("/showIndex")
    public ModelAndView showIndex() {
        return new ModelAndView(this.getViewRoot() + "/index");
    }

    @GetMapping("/showDetail")
    public ModelAndView showDetail() {
        return new ModelAndView(this.getViewRoot() + "/detail");
    }

    @GetMapping("/showList")
    public ModelAndView showList() {
        return new ModelAndView(this.getViewRoot() + "/list");
    }

    @GetMapping("/getDetail")
    public ResultDto<CouponTb> getDetail(String couponId) {
        CouponTb coupon = rf.getCouponTbRepo().findOne(couponId);
        return ResultDto.succeed("", coupon);
    }

    @GetMapping("/getQuickEntries")
    public ResultDto<Set<Category>> getQuickEntries() {
        return ResultDto.succeed("", rf.getCategoryRepo().findOne("coupontb").getChildren());
    }

    @GetMapping("/getCoupons")
    public ResultDto<Page<CouponTb>> getCoupons(CouponTbCondiDto condi, @PageableDefault Pageable pageable) {
        Validate.isTrue(pageable.getPageSize() == 10);
        Page<CouponTb> couponTbPage = rf.getCouponTbRepo().findAll(condi.toSpecification(), pageable);
        return ResultDto.succeed("", couponTbPage);
    }
}
