package com.koreait.first.ch10.searchmoviemodel;

public class MovieInfoResultVO {
    private MovieInfoVO movieInfo;
    private String source;

    public MovieInfoVO getMovieInfo() {
        return this.movieInfo;
    }

    public void setMovieInfo(MovieInfoVO movieInfo) {
        this.movieInfo = movieInfo;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
