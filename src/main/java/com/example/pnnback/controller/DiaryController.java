package com.example.pnnback.controller;

import com.example.pnnback.dto.DiaryRequestDto;
import com.example.pnnback.entity.Diary;
import com.example.pnnback.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping
    public ResponseEntity<Diary> createDiary(@RequestParam Long userId,
                                             @RequestBody DiaryRequestDto dto) {
        Diary saved = diaryService.saveDiary(userId, dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Diary>> getDiaries(@RequestParam Long userId) {
        List<Diary> list = diaryService.getUserDiaries(userId);
        return ResponseEntity.ok(list);
    }

    // 수정 API (PUT)
    @PutMapping("/{diaryId}")
    public ResponseEntity<Diary> updateDiary(@RequestParam Long userId,
                                             @PathVariable Long diaryId,
                                             @RequestBody DiaryRequestDto dto) {
        Diary updated = diaryService.updateDiary(userId, diaryId, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 삭제 API (DELETE)
    @DeleteMapping("/{diaryId}")
    public ResponseEntity<Void> deleteDiary(@RequestParam Long userId,
                                            @PathVariable Long diaryId) {
        boolean deleted = diaryService.deleteDiary(userId, diaryId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
