package com.leonardo.repository;

import com.leonardo.entity.ListJourney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface listJourneyRepository extends JpaRepository<ListJourney, String> {}
