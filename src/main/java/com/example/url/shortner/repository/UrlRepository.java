package com.example.url.shortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.url.shortner.entity.UrlEntity;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
}
