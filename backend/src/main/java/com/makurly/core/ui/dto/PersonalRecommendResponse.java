package com.makurly.core.ui.dto;

import com.makurly.core.domain.Recommend;

public class PersonalRecommendResponse {

    private final ItemResponse item;
    private final Integer discountRate;

    private PersonalRecommendResponse(ItemResponse itemResponse, Integer discountRate) {
        this.item = itemResponse;
        this.discountRate = discountRate;
    }

    public static PersonalRecommendResponse of(Recommend recommend) {
        return new PersonalRecommendResponse(
            ItemResponse.of(recommend.getItem()),
            recommend.getDiscountRate()
        );
    }

    public ItemResponse getItem() {
        return item;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }
}
