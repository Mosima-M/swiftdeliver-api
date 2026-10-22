package za.co.swiftdeliver.repository;

import za.co.swiftdeliver.model.Hub;

import java.util.*;

public class InMemoryHubRepository implements HubRepository{

    private final Map<String , Hub> hubs = new HashMap<>();

    @Override
    public List<Hub> getAllHubs() {
        return new ArrayList<>(hubs.values());
    }

    @Override
    public Optional<Hub> getHub(String hubId) {
        Hub hub = hubs.get(hubId);
        return Optional.ofNullable(hub);
    }

    @Override
    public void saveHub(Hub hub) {
        hubs.put(hub.getHubId(),hub);
    }
}
