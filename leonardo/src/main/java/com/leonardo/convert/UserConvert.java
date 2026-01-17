package com.leonardo.convert;

import com.leonardo.entity.User;
import com.leonardo.entity.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConvert {
  private final ModelMapper modelMapper;

  public UserDTO toDTO(User user) {
      return modelMapper.map(user, UserDTO.class);
  }

  public User toDocs(UserDTO userDto) {
      return modelMapper.map(userDto, User.class);
  }
}
