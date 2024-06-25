package com.findyourguide.api.Strategis.register;

import com.findyourguide.api.dto.user.RegisterDTO;
import org.springframework.stereotype.Service;

@Service("google")
public class Google implements IRegisterStrategy {
    @Override
    public String register(RegisterDTO user) {
        return "Successfully Registered with Google!";
    }
}
