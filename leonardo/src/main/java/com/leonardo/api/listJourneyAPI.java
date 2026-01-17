package com.leonardo.api;

import com.leonardo.entity.ListJourney;
import com.leonardo.service.IListJourneyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class listJourneyAPI {
  private final IListJourneyService journeyService;

  @GetMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      path = {"/getListJourney"})
  public List<ListJourney> findValue() {
    return journeyService.findValue();
  }
}
