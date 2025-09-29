package com.devdam.server.service.faker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devdam.server.enums.SubscriberType;
import com.devdam.server.model.Action;
import com.devdam.server.model.AppSetting;
import com.devdam.server.model.Bus;
import com.devdam.server.model.Driver;
import com.devdam.server.model.Employee;
import com.devdam.server.model.Location;
import com.devdam.server.model.Passenger;
import com.devdam.server.model.Role;
import com.devdam.server.model.Route;
import com.devdam.server.model.Subscriber;
import com.devdam.server.model.User;
import com.devdam.server.repository.ActionRepository;
import com.devdam.server.repository.AppSettingRepository;
import com.devdam.server.repository.BusRepository;
import com.devdam.server.repository.DriverRepository;
import com.devdam.server.repository.EmployeeRepository;
import com.devdam.server.repository.LocationRepository;
import com.devdam.server.repository.PassengerRepository;
import com.devdam.server.repository.RoleRepository;
import com.devdam.server.repository.RouteRepository;
import com.devdam.server.repository.SubscriberRepository;
import com.devdam.server.repository.UserRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("dev")
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FakerServiceImpl implements FakerService {
    private final Faker faker = new Faker();
    private final Slugify slugify = new Slugify();

    private final RoleRepository roleRepository;
    private final ActionRepository actionRepository;
    private final SubscriberRepository subscriberRepository;
    private final UserRepository userRepository;
    private final PassengerRepository passengerRepository;
    private final EmployeeRepository employeeRepository;
    private final DriverRepository driverRepository;
    private final BusRepository busRepository;
    private final LocationRepository locationRepository;
    private final RouteRepository routeRepository;
    private final AppSettingRepository appSettingRepository;

    private Set<Role> getRandomRoles() {
        Set<Role> roles = new HashSet<>();
        List<Role> allRoles = roleRepository.findAll();
        int numberOfRoles = faker.number().numberBetween(1, 2); // Assign between 1 and 2 roles

        if(!allRoles.isEmpty()) {
            for (int i = 0; i < numberOfRoles; i++) {
                Role randomRole = allRoles.get(faker.random().nextInt(allRoles.size()));
                roles.add(randomRole);
            }
        }

        return roles;
    }

    private Role getRoleByName(String name) {
        Role role = roleRepository.findByNameEqualsIgnoreCase(name);

        if (role == null) {
            role = roleRepository.save(Role.builder()
                .name(name)
                .slug(slugify.slugify(name))
                .read(true)
                .write((name.equalsIgnoreCase("passenger") || name.equalsIgnoreCase("user")) ? false : true)
                .execute((name.equalsIgnoreCase("administrator") || name.equalsIgnoreCase("admin") || name.equalsIgnoreCase("manager")) ? true : false)
                .build());
        }

        return role;
    }

    private Set<Bus> getRandomBuses() {
        Set<Bus> buses = new HashSet<>();
        List<Bus> allBuses = busRepository.findAll();
        int numberOfBuses = faker.number().numberBetween(1, 10); // Assign between 1 and 10 buses

        if(!allBuses.isEmpty()) {
            for (int i = 0; i < numberOfBuses; i++) {
                Bus randomBus = allBuses.get(faker.random().nextInt(allBuses.size()));
                buses.add(randomBus);
            }
        }

        return buses;
    }

    @Override
    public void generateRoles(int amount) {
        // Check if roles already exist
        if (roleRepository.count() > 0) {
            log.info("Roles already exist in the database. Skipping generation.");
            return;
        }

        log.info("Generating roles...");
        Set<String> generatedNames = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        Set<Role> roles = new HashSet<>();
        // RoleType[] hardcodedRoles = RoleType.values();
        
        // for (int i = 0; i < hardcodedRoles.length; i++) {
        for (int i = 0; i < amount; i++) {
            // Ensure unique role names
            sb.append(faker.job().position());
            // sb.append(hardcodedRoles[i]);

            // Skip duplicates
            if (generatedNames.contains(sb.toString().toLowerCase())) continue;

            roles.add(Role.builder()
                .name(sb.toString())
                .slug(slugify.slugify(sb.toString()))
                .read(faker.bool().bool())
                .write(faker.bool().bool())
                .execute(faker.bool().bool())
                .build()
            );

            generatedNames.add(sb.toString().toLowerCase());
            sb.setLength(0); // Clear the StringBuffer for the next iteration
        }

        roleRepository.saveAll(roles);
        log.info("Role generation completed!");
    }

    @Override
    public void generateActions(int amount) {
        // Check if actions already exist
        if (actionRepository.count() > 0) {
            log.info("Actions already exist in the database. Skipping generation.");
            return;
        }

        log.info("Generating actions...");
        Set<String> generatedNames = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        Set<Action> actions = new HashSet<>();

        for (int i = 0; i < amount; i++) {
            // Ensure unique action names
            sb.append(faker.hacker().verb());

            // Skip duplicates
            if (generatedNames.contains(sb.toString().toLowerCase())) continue;

            actions.add(Action.builder()
                .name(sb.toString())
                .slug(slugify.slugify(sb.toString()))
                .build()
            );

            generatedNames.add(sb.toString().toLowerCase());
            sb.setLength(0); // Clear the StringBuffer for the next iteration
        }

        actionRepository.saveAll(actions);
        log.info("Action generation completed!");
    }

    @Override
    public void generateSubscribers(int amount) {
        // Check if subscribers already exist
        if (subscriberRepository.count() > 0) {
            log.info("Subscribers already exist in the database. Skipping generation.");
            return;
        }

        log.info("Generating subscribers...");
        List<Subscriber> subscribers = new ArrayList<>();
        
        for (int i = 0; i < amount; i++) {
            subscribers.add(Subscriber.builder()
                .email(faker.internet().emailAddress())
                .unsubscribeCode(faker.internet().uuid())
                .type(faker.options().option(SubscriberType.class))
                .build()
            );
        }

        subscriberRepository.saveAll(subscribers);
        log.info("Subscriber generation completed!");
    }

    @Override
    public void generateUsers(int amount) {
        if (userRepository.count() > 0) {
            log.info("Users already exist. Skipping generation.");
            return;
        }

        log.info("Generating users...");
        List<User> users = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            User user = User.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password()) // encode in real app
                .isActive(faker.bool().bool())
                .isStaff(faker.bool().bool())
                .isSuperuser(faker.bool().bool())
                .roles(getRandomRoles())
                .build();

            users.add(user);
        }

        userRepository.saveAll(users);
        log.info("User generation completed!");
    }

    @Override
    public void generateAdministrators(int amount) {
        if (userRepository.count() > 0) {
            log.info("Users already exist. Skipping generation.");
            return;
        }

        log.info("Generating administrators...");
        List<User> admins = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            User admin = User.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password()) // encode in real app
                .isActive(true)
                .isStaff(true)
                .isSuperuser(true)
                .roles(Set.of(getRoleByName("Administrator")))
                .build();

            admins.add(admin);
        }

        userRepository.saveAll(admins);
        log.info("Administrator generation completed!");
     }

    @Override
    public void generateProfilesForUsers() {
        List<User> usersWithoutProfiles = userRepository.findAll().stream()
            .filter(user -> user.getProfile() == null)
            .toList();

        if (usersWithoutProfiles.isEmpty()) {
            log.info("All users already have profiles. Skipping profile generation.");
            return;
        }

        log.info("Generating profiles for users without profiles...");
        List<User> updatedUsers = new ArrayList<>();

        for (User user : usersWithoutProfiles) {
            // Create a new Profile
            com.devdam.server.model.Profile profile = com.devdam.server.model.Profile.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .address(faker.address().fullAddress())
                .photoURL(faker.internet().avatar())
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .filename(faker.file().fileName())
                .user(user) // Set the relationship
                .build();

            // Set the profile to the user
            user.setProfile(profile);
            updatedUsers.add(user);
        }

        // Save all updated users (which will also save the profiles due to cascade)
        userRepository.saveAll(updatedUsers);
        log.info("Profile generation completed!");
    }

    @Override
    public void generatePassengers(int amount) {
        // Check if passengers already exist
        if (passengerRepository.count() > 0) {
            log.info("Passengers already exist in the database. Skipping generation.");
            return;
        }

        log.info("Generating passengers...");
        List<Passenger> passengers = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            passengers.add(Passenger.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password()) // encode in real app
                .isActive(faker.bool().bool())
                .isStaff(false)
                .isSuperuser(false)
                .roles(Set.of(getRoleByName("Passenger")))
                .build()
            );
        }

        passengerRepository.saveAll(passengers);
        log.info("Passenger generation completed!");
    }

    @Override
    public void generateEmployees(int amount) {
        // Check if employees already exist
        if (employeeRepository.count() > 0) {
            log.info("Employees already exist in the database. Skipping generation.");
            return;
        }

        log.info("Generating employees...");
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            employees.add(Employee.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password()) // encode in real app
                .isActive(faker.bool().bool())
                .isStaff(true)
                .isSuperuser(false)
                .roles(Set.of(getRoleByName("Employee")))
                .isRegular(faker.bool().bool())
                .build()
            );
        }

        employeeRepository.saveAll(employees);
        log.info("Employee generation completed!");
    }

    @Override
    public void generateDrivers(int amount) {
        // Check if drivers already exist
        if (driverRepository.count() > 0) {
            log.info("Drivers already exist in the database. Skipping generation.");
            return;
        }

        log.info("Generating drivers...");
        List<Driver> drivers = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            drivers.add(Driver.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password()) // encode in real app
                .isActive(faker.bool().bool())
                .isStaff(faker.bool().bool())
                .isSuperuser(faker.bool().bool())
                .roles(Set.of(getRoleByName("Driver")))
                .build()
            );
        }

        driverRepository.saveAll(drivers);
        log.info("Driver generation completed!");
    }

    @Override
    public void generateBuses(int amount) {
        // Check if buses already exist
        if (busRepository.count() > 0) {
            log.info("Buses already exist in the database. Skipping generation.");
            return;
        }

        log.info("Generating buses...");
        List<Bus> buses = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Bus bus = Bus.builder()
                .licensePlate(faker.bothify("####??")) // e.g., 1234AB
                .busNumber(faker.number().numberBetween(1, 1000))
                
                .build();

            buses.add(bus);
        }

        busRepository.saveAll(buses);
        log.info("Bus generation completed!");
    }

    @Override
    public void assignDriversToBuses() {
        List<Bus> allFreeBuses = busRepository.findAllByDriverIsNullOrderByBusNumberAsc();
        List<Driver> allFreeDrivers = driverRepository.findAllByBusIsNull();

        if (allFreeBuses.isEmpty()) {
            log.info("No buses available to assign drivers.");
            return;
        }

        if (allFreeDrivers.isEmpty()) {
            log.info("No drivers available for assignment.");
            return;
        }

        log.info("Assigning drivers to buses...");
        List<Bus> updatedBuses = new ArrayList<>();
        List<Driver> updatedDrivers = new ArrayList<>();
        Set<Long> assignedDriverIds = new HashSet<>();

        for (Bus bus : allFreeBuses) {
            // Only assign a driver if the bus doesn't already have one
            if (bus.getDriver() == null) {
                Driver randomDriver = null;
                int attempts = 0;

                // Try to find an unassigned driver, limit attempts to avoid infinite loop
                while (attempts < 10) {
                    Driver potentialDriver = allFreeDrivers.get(faker.random().nextInt(allFreeDrivers.size()));

                    if (!assignedDriverIds.contains(potentialDriver.getId())) {
                        randomDriver = potentialDriver;
                        assignedDriverIds.add(potentialDriver.getId());
                        break;
                    }

                    attempts++;
                }

                if (randomDriver != null) {
                    randomDriver.setBus(bus);
                    updatedDrivers.add(randomDriver);

                    bus.setDriver(randomDriver);
                    updatedBuses.add(bus);
                }
            }
        }

        busRepository.saveAll(updatedBuses);
        driverRepository.saveAll(updatedDrivers);
        log.info("Driver assignment to buses completed!");
    }

    @Override
    public void generateBusLocations() {
        List<Bus> allBusesWithNoLocation = busRepository.findAllByLocationIsNull();

        if (allBusesWithNoLocation.isEmpty()) {
            log.info("No buses available for location generation.");
            return;
        }

        log.info("Generating bus locations...");
        for (Bus bus : allBusesWithNoLocation) {
            if (bus.getLocation() == null) {
                Location location = Location.builder()
                    .latitude(Double.valueOf(faker.address().latitude()))
                    .longitude(Double.valueOf(faker.address().longitude()))
                    .place(faker.address().cityName())
                    .bus(bus)
                    .build();

                bus.setLocation(locationRepository.save(location));
            }
        }

        busRepository.saveAll(allBusesWithNoLocation);
        log.info("Bus location generation completed!");
    }

    @Override
    public void generateRoutes(int amount) {
        // Check if routes already exist
        if (routeRepository.count() > 0) {
            log.info("Routes already exist in the database. Skipping generation.");
            return;
        }

        log.info("Generating routes...");

        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Route route = Route.builder()
                .name(faker.address().streetName())
                .startPoint(faker.address().cityName())
                .endPoint(faker.address().cityName())
                .fare(faker.number().randomDouble(2, 100, 8000)) // fare between 100.00 and 8000.00
                .build(); 

            routes.add(route);
        }

        routeRepository.saveAll(routes);
        log.info("Route generation completed!");
    }

    @Override
    public void assignBusesToRoutes() {
        List<Bus> allBusesWithoutRoutes = busRepository.findAllByRouteIsNull();
        List<Route> allRoutes = routeRepository.findAll();
        List<Route> updatedRoutes = new ArrayList<>();

        if (allBusesWithoutRoutes.isEmpty()) {
            log.info("No buses available to assign routes.");
            return;
        }

        if (allRoutes.isEmpty()) {
            log.info("No routes available to assign to buses.");
            return;
        }

        // Assign buses to routes
        for (Bus bus : allBusesWithoutRoutes) {
            Route randomRoute = allRoutes.get(faker.random().nextInt(allRoutes.size()));

            randomRoute.getBuses().add(bus);

            bus.setRoute(randomRoute);
            updatedRoutes.add(randomRoute);
        }

        routeRepository.saveAll(updatedRoutes);
        busRepository.saveAll(allBusesWithoutRoutes);
        log.info("Bus assignment to routes completed!");
    }

    @Override
    public void assignPassengersToFollowBuses() {
        List<Bus> allBuses = busRepository.findAll();
        List<Passenger> allPassengers = passengerRepository.findAll();

        if (allBuses.isEmpty() || allPassengers.isEmpty()) {
            log.info("No buses or passengers available for assignment.");
            return;
        }

        log.info("Assigning passengers to follow buses...");
        for (Passenger passenger : allPassengers) {
            Bus randomBus = allBuses.get(faker.random().nextInt(allBuses.size()));

            Set<Bus> followedBuses = passenger.getFollowedBuses();
            followedBuses.add(randomBus);
            followedBuses.addAll(getRandomBuses());

            // Ensure uniqueness
            passenger.setFollowedBuses(new HashSet<>(followedBuses));

            // randomBus.setPassenger(passenger);
            randomBus.getPassengers().add(passenger);
        }

        busRepository.saveAll(allBuses);
        passengerRepository.saveAll(allPassengers);
        log.info("Passenger assignment to buses completed!");
    }

    @Override
    public void generateAppSettings(int amount) {
        // Check if app settings already exist
        if (appSettingRepository.count() > 0) {
            log.info("App settings already exist in the database. Skipping generation.");
            return;
        }

        log.info("Generating application settings...");
        List<AppSetting> settings = new ArrayList<>();
        Set<String> generatedNames = new HashSet<>();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < amount; i++) {
            // Ensure unique setting names
            sb.append(faker.app().name());

            // Skip duplicates
            if (generatedNames.contains(sb.toString().toLowerCase())) continue;

            settings.add(AppSetting.builder()
                .name(sb.toString())
                .turnedOn(faker.bool().bool())
                .build()
            );

            generatedNames.add(sb.toString().toLowerCase());
            sb.setLength(0); // Clear the StringBuffer for the next iteration
        }

        appSettingRepository.saveAll(settings);
        log.info("Application settings generation completed.");
    }

    @Override
    public void clearAllData() {
        log.info("Clearing all data from the database...");

        // Delete in order to avoid foreign key constraint violations
        driverRepository.deleteAllInBatch();
        employeeRepository.deleteAllInBatch();
        passengerRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        busRepository.deleteAllInBatch();
        routeRepository.deleteAllInBatch();
        locationRepository.deleteAllInBatch();
        actionRepository.deleteAllInBatch();
        roleRepository.deleteAllInBatch();
        subscriberRepository.deleteAllInBatch();

        log.info("All data cleared from the database.");
    }
}
