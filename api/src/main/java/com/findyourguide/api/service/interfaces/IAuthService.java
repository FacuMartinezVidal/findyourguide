package com.findyourguide.api.service.interfaces;

import com.findyourguide.api.dto.UserLoginDTO;
import com.findyourguide.api.dto.user.LoginDTO;
import com.findyourguide.api.dto.user.RegisterDTO;
import com.findyourguide.api.dto.user.UserDTO;

public interface IAuthService {
    UserLoginDTO login(LoginDTO request);

    UserDTO register(RegisterDTO request);
}
