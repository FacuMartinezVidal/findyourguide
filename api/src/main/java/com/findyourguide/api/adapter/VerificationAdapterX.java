package com.findyourguide.api.adapter;

import org.springframework.stereotype.Service;

@Service
public class VerificationAdapterX implements IAdapter {
    @Override
    public boolean verification(String credential) {
        return true;
    }
}
