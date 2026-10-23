package za.co.swiftdeliver.service;

import za.co.swiftdeliver.exception.DuplicateHubException;
import za.co.swiftdeliver.exception.HubNotFoundException;
import za.co.swiftdeliver.model.Hub;
import za.co.swiftdeliver.repository.HubRepository;

import java.util.List;
import java.util.Optional;

public class HubService {

    private final HubRepository repository;

    public HubService(HubRepository repository) {
        this.repository = repository;
    }

    public void createHub(Hub hub) {
        Optional<Hub> result = repository.getHub(hub.getHubId());
        if(result.isPresent()){
            throw new DuplicateHubException("Hub already exists");
        }
        repository.saveHub(hub);
    }

    public List<Hub> getAllHubs() {
        return repository.getAllHubs();
    }

    public Hub getHubById(String hubId) {
        Optional<Hub> hub = repository.getHub(hubId);

        if(hub.isEmpty()){
            throw new HubNotFoundException("Hub not found: " + hubId);
        }
        return hub.get();
    }


}
