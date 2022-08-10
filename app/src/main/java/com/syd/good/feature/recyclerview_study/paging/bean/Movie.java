package com.syd.good.feature.recyclerview_study.paging.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/24 15:22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class Movie {
    private String name;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static List<Movie> createMovies(int count){
        List<Movie> list = new ArrayList<>();
        for (int i = 0;i<count;i++){
            Movie movie = new Movie();
            movie.setName("movie"+i);
            list.add(movie);
        }
        return list;
    }
}
