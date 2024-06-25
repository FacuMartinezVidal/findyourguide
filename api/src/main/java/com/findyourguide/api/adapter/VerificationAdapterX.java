package com.findyourguide.api.adapter;

public class VerificationAdapterX implements IAdapter {
    @Override
    public void verificate(String credential) {
        System.out.println("Verifying credential: " + credential);
    }
}
