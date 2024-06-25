package com.findyourguide.api.adapter;

import org.springframework.stereotype.Service;

@Service
public class VerificationAdapterX implements IAdapter {
    @Override
    public void verificate(String credential) {
        System.out.println("Verifying credential: " + credential);
    }
}
