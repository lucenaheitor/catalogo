package com.example.demo.repository;

import com.example.demo.dto.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository  extends JpaRepository<Livro, UUID> {
}
