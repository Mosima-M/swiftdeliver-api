package za.co.swiftdeliver.repository;

import za.co.swiftdeliver.model.Hub;


import java.util.List;
import java.util.Optional;

public interface HubRepository {

    List<Hub> getAllHubs();
    Optional<Hub> getHub(String hubId);
    void saveHub(Hub hub);
}
