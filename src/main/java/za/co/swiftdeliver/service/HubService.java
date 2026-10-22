package za.co.swiftdeliver.service;

import za.co.swiftdeliver.model.Hub;
import za.co.swiftdeliver.repository.HubRepository;

import java.util.Optional;

public class HubService {

    private final HubRepository repository;

    public HubService(HubRepository repository) {
        this.repository = repository;
    }

    public void createHub(Hub hub) {
        Optional<Hub> result = repository.getHub(hub.getHubId());
        if(result.isPresent()){
            throw new IllegalArgumentException("Hub already exists");
        }
        repository.saveHub(hub);
    }
}
