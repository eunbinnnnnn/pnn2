package com.example.pnnback.repository;

import com.example.pnnback.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserIdOrderByCreatedAtDesc(Long userId);
}

