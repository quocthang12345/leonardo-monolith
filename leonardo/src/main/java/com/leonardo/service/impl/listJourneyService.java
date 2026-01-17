package com.leonardo.service.impl;

import com.leonardo.entity.ListJourney;
import com.leonardo.repository.listJourneyRepository;
import com.leonardo.service.IListJourneyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class listJourneyService implements IListJourneyService {
  private final listJourneyRepository listJourneyRepo;

  @Override
  public List<ListJourney> findValue() {
    return listJourneyRepo.findAll();
  }
}
