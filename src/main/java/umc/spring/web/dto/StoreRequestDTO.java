package umc.spring.web.dto;

import lombok.Getter;

import java.util.List;

public class StoreRequestDTO {

    @Getter
    public static class JoinDto{
        String name;
        Float score;
        String address;
        Long regionId;
        List<Long> reviewList;
    }
}
