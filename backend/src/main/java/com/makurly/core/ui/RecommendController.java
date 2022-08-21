package com.makurly.core.ui;

import com.makurly.core.application.RecommendService;
import com.makurly.core.application.WebClientService;
import com.makurly.core.application.dto.RecommendResponse;
import com.makurly.core.ui.dto.PersonalRecommendResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

    private final WebClientService webClientService;

    private final RecommendService recommendService;

    public RecommendController(WebClientService webClientService, RecommendService recommendService) {
        this.webClientService = webClientService;
        this.recommendService = recommendService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PersonalRecommendResponse>> recommend(@PathVariable Long id){
        RecommendResponse recommendResponse = webClientService.getItemsFromInteraction(id);
        List<PersonalRecommendResponse> body = recommendService.findItemsByIds(recommendResponse);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(body);
    }
}
