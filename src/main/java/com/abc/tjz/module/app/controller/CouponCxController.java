package com.abc.tjz.module.app.controller;

import com.abc.tjz.entity.Category;
import com.abc.tjz.entity.CouponCx;
import com.abc.tjz.module.app.dto.CouponCxCondiDto;
import com.abc.tjz.util.dto.ResultDto;
import com.abc.tjz.util.misc.AbstractController;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

import static com.abc.tjz.repository.RepoFactory.rf;

@RestController
@RequestMapping("/action/app/couponcx")
public class CouponCxController extends AbstractController {

    @GetMapping("/showIndex")
    public ModelAndView showIndex() {
        return new ModelAndView(this.getViewRoot() + "/index");
    }

    @GetMapping("/showDetail")
    public ModelAndView showDetail() {
        return new ModelAndView(this.getViewRoot() + "/detail");
    }

    @GetMapping("/getQuickEntries")
    public ResultDto<Set<Category>> getQuickEntries(ModelMap map) {
        return ResultDto.succeed("", rf.getCategoryRepo().findOne("couponcx").getChildren());
    }

    @GetMapping("/getCoupons")
    public ResultDto<Page<CouponCx>> getCoupons(CouponCxCondiDto condi, @PageableDefault Pageable pageable) {
        Validate.isTrue(pageable.getPageSize() == 10);
        Page<CouponCx> page = rf.getCouponCxRepo().findAll(condi.toSpecification(), pageable);
        return ResultDto.succeed("", page);
    }

    @GetMapping("/getDetail")
    public ResultDto<ModelMap> getDetail(String couponId, ModelMap map) {
        CouponCx coupon = rf.getCouponCxRepo().findOne(couponId);
        map.addAttribute("coupon", coupon);
        map.addAttribute("category", coupon.getCategory());
        return ResultDto.succeed("", map);
    }
}
