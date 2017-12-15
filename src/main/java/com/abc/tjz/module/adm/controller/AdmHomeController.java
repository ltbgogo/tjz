package com.abc.tjz.module.adm.controller;

import com.abc.tjz.entity.Category;
import com.abc.tjz.entity.CouponCx;
import com.abc.tjz.util.dto.ResultDto;
import com.abc.tjz.util.misc.AbstractController;
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
@RequestMapping("/action/adm/home")
public class AdmHomeController extends AbstractController {

    @GetMapping("/showIndex")
    public ModelAndView showIndex() {
        return new ModelAndView(this.getViewRoot() + "/index");
    }
}
