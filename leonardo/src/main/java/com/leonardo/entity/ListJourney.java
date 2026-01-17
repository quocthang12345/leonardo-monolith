package com.leonardo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "list_journey")
@Data
@EqualsAndHashCode(callSuper = true)
public class ListJourney extends Common {

  @Column
  private String title;

  @Column
  private String imgJourney;

  @ElementCollection
  @CollectionTable(name = "journey_descriptions", joinColumns = @JoinColumn(name = "journey_id"))
  @Column(name = "description")
  private List<String> descriptionJourney = new ArrayList<>();
}
