package com.abc.tjz;

import com.abc.tjz.entity.CouponTakeout;
import com.abc.tjz.module.coupon.dto.CouponTakeoutCondiDto;
import com.abc.tjz.util.misc.SpringManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.io.IOException;
import java.util.Map;

import static com.abc.tjz.repository.RepoFactory.rf;

@SpringBootApplication
public class App {
	
    public static void main( String[] args ) throws IOException {
//		SpringApplication.run(App.class, args);
    	SpringManager.startMailApplication(App.class, args);
        CouponTakeoutCondiDto condi = new CouponTakeoutCondiDto();
//    	rf.getCouponTakeoutRepo().findAll(condi, new PageRequest(0, 1), "id");
        condi.getSupplement().add((root, query, cb) -> {
            //query.select((Selection) cb.construct(CouponTakeout.class, (Selection<?>) root.get("id")));
            query.select((Selection) cb.array(root.get("id"), root.get("id")));
            return cb.and();
        });
        Page<Object[]> page = rf.getCouponTakeoutRepo().findAll(condi, new PageRequest(0, 1));
        System.out.println(page.getContent().get(0)[0]);
    }
}
