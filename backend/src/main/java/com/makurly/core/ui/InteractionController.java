package com.makurly.core.ui;

import com.makurly.core.application.InteractionService;
import com.makurly.core.ui.dto.InteractionRequest;
import com.makurly.core.ui.dto.InteractionResponse;
import com.makurly.core.ui.dto.UserInteractionResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interactions")
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @PostMapping
    public ResponseEntity<InteractionResponse> makeInteraction(@RequestBody InteractionRequest interactionRequest) {

        InteractionResponse body = interactionService.makeInteraction(interactionRequest);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UserInteractionResponse>> getUserInteractions(@PathVariable Long id){
        List<UserInteractionResponse> body = interactionService.getUserInteractions(id);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(body);
    }

}
