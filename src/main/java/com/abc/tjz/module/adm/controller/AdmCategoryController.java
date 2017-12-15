package com.abc.tjz.module.adm.controller;

import com.abc.tjz.entity.Category;
import com.abc.tjz.entity.CouponCx;
import com.abc.tjz.module.adm.dto.CategoryCondiDto;
import com.abc.tjz.module.adm.dto.CouponCxCondiDto;
import com.abc.tjz.module.adm.service.AdmCouponCxService;
import com.abc.tjz.util.dto.ResultDto;
import com.abc.tjz.util.misc.AbstractController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.abc.tjz.repository.RepoFactory.rf;

@RestController
@RequestMapping("/action/adm/category")
public class AdmCategoryController extends AbstractController {

    @GetMapping("/getCategories")
    public ResultDto<List<Category>> getCategories(CategoryCondiDto condi, @SortDefault(sort = "seq", direction = Sort.Direction.ASC) Sort sort) {
        List<Category> list = rf.getCategoryRepo().findAll(condi.toSpecification(), sort);
        return ResultDto.succeed("", list);
    }
}
