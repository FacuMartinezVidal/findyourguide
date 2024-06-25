package com.findyourguide.api.Strategis.register;

import com.findyourguide.api.dto.user.RegisterDTO;

public interface IRegisterStrategy {

    String register(RegisterDTO user);

}
