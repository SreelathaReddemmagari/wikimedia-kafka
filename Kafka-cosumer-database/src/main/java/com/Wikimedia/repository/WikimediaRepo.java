package com.Wikimedia.repository;

import com.Wikimedia.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikimediaRepo extends JpaRepository<WikimediaData,Long> {
}
