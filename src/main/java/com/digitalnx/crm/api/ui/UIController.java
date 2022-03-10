package com.digitalnx.crm.api.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UIController {
    @Autowired
    UIRepository uiRepository;

    @GetMapping("/ui")
    ResponseEntity<UI> getUI() {
        List<UI> ui = uiRepository.findAll();
        if (ui.size() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ui.get(0));
        }
    }

    @PutMapping("/ui")
    ResponseEntity<UI> updateUI(@RequestBody UI uiRequest) {
        List<UI> ui = uiRepository.findAll();
        if(ui.size() == 0) {
            return ResponseEntity.internalServerError().build();
        } else {
            var existingId = ui.get(0).getId();
            uiRequest.setId(existingId);
            uiRepository.save(uiRequest);
            return ResponseEntity.ok(uiRequest);
        }
    }
}
