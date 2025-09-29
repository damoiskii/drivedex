package com.devdam.server.service;

public interface FakerService {
    void generateRoles(int amount);
    void generateActions(int amount);
    void generateSubscribers(int amount);
    void generateUsers(int amount);
    void generateAdministrators(int amount);
    void generatePassengers(int amount);
    void generateEmployees(int amount);
    void generateDrivers(int amount);
    void generateProfilesForUsers();

    void generateBuses(int amount);
    void assignDriversToBuses();
    void generateBusLocations();
    void generateRoutes(int amount);
    void assignBusesToRoutes();

    void assignPassengersToFollowBuses();

    void clearAllData();
}
