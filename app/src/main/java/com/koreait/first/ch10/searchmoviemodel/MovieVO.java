package com.koreait.first.ch10.searchmoviemodel;

public class MovieVO {
    private String movieCd;
    private String movieNm;
    private String movieNmEn;
    private String repNationNm;   // 제작국가
    private String repGenreNm;    // 장르

    public String getMovieCd() {
        return this.movieCd;
    }

    public void setMovieCd(String movieCd) {
        this.movieCd = movieCd;
    }

    public String getMovieNm() {
        return this.movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getMovieNmEn() {
        return this.movieNmEn;
    }

    public void setMovieNmEn(String movieNmEn) {
        this.movieNmEn = movieNmEn;
    }

    public String getRepNationNm() {
        return this.repNationNm;
    }

    public void setRepNationNm(String repNationNm) {
        this.repNationNm = repNationNm;
    }

    public String getRepGenreNm() {
        return this.repGenreNm;
    }

    public void setRepGenreNm(String repGenreNm) {
        this.repGenreNm = repGenreNm;
    }
}
