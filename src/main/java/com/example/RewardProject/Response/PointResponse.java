package com.example.RewardProject.Response;

public class PointResponse {

    private int point;

    private Long id;

    public PointResponse(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
