package com.example.pnnback.service;

import com.example.pnnback.dto.DiaryRequestDto;
import com.example.pnnback.entity.Diary;
import com.example.pnnback.repository.DiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public Diary saveDiary(Long userId, DiaryRequestDto dto) {
        Diary diary = new Diary();
        diary.setUserId(userId);
        diary.setTitle(dto.getTitle());
        diary.setContent(dto.getContent());
        return diaryRepository.save(diary);
    }

    public List<Diary> getUserDiaries(Long userId) {
        return diaryRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    // 수정 기능
    public Diary updateDiary(Long userId, Long diaryId, DiaryRequestDto dto) {
        Optional<Diary> optionalDiary = diaryRepository.findById(diaryId);
        if (optionalDiary.isPresent()) {
            Diary diary = optionalDiary.get();
            if (!diary.getUserId().equals(userId)) {
                // 권한 체크: userId가 다르면 null 반환하거나 예외 처리 가능
                return null;
            }
            diary.setTitle(dto.getTitle());
            diary.setContent(dto.getContent());
            return diaryRepository.save(diary);
        }
        return null;
    }

    // 삭제 기능
    public boolean deleteDiary(Long userId, Long diaryId) {
        Optional<Diary> optionalDiary = diaryRepository.findById(diaryId);
        if (optionalDiary.isPresent()) {
            Diary diary = optionalDiary.get();
            if (!diary.getUserId().equals(userId)) {
                // 권한 체크 실패
                return false;
            }
            diaryRepository.deleteById(diaryId);
            return true;
        }
        return false;
    }
}
