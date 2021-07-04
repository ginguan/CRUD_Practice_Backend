package com.example.backend.repository;

import com.example.backend.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepo extends JpaRepository<Show, Long> {
  List<Show> findByStatus(boolean published);

  List<Show> findByTitleContaining(String title);
}
