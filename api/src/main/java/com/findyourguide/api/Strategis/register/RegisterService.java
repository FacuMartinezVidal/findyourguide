package com.findyourguide.api.Strategis.register;

import com.findyourguide.api.dto.user.RegisterDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {
    private final Map<String, IRegisterStrategy> strategies = new HashMap<>();
    private IRegisterStrategy currentStrategy;

    public RegisterService(Map<String, IRegisterStrategy> strategyMap) {
        this.strategies.putAll(strategyMap);
        if (!strategyMap.isEmpty()) {
            this.currentStrategy = strategyMap.values().iterator().next();
        }
    }

    public String register(RegisterDTO request) {
        if (currentStrategy == null) {
            throw new IllegalStateException("No strategy is currently set");
        }
        return currentStrategy.register(request);
    }

    public void changeStrategy(String strategyName) {
        IRegisterStrategy strategy = strategies.get(strategyName);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for: " + strategyName);
        }
        this.currentStrategy = strategy;
    }
}
