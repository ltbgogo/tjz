package com.abc.tjz;

import com.abc.tjz.util.SpringManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.abc.tjz.repository.RepoFactory.rf;

@SpringBootApplication
public class App {
	
    public static void main( String[] args ) {
		SpringApplication.run(App.class, args);
//    	SpringManager.startMailApplication(App.class, args);
        rf.getGoodsCategoryRepo().findAll();
    }
}
