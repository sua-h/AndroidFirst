package com.koreait.first.ch10.boxofficemodel;

public class BoxOfficeVO {
    private String rank;      // 순위
    private String movieNm;   // 영화명
    private String openDt;    // 개봉일
    private String audiCnt;


    public String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getMovieNm() {
        return this.movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getOpenDt() {
        return this.openDt;
    }

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    public String getAudiCnt() {
        return this.audiCnt;
    }

    public void setAudiCnt(String audiCnt) {
        this.audiCnt = audiCnt;
    }
}
