

## Bean Creation

The application demonstrates three different ways of defining Spring beans:

* Beans discovered by component scanning
    * Classes like: StateChangeLogger, LoggingAspect are annotated with @Component or @Service, allowing Spring to detect them automatically.

* Beans defined in configuration classes
    * In SmartHomeConfig, multiple smart-home devices are created using @Bean methods.
      Each method returns a fully configured device instance (light, thermostat, alarm).

* There is also a programmatic registration mechanism that adds a device at runtime.

## Dependency Injection

All services and controllers use constructor injection.
This makes dependencies explicit and improves testability and clarity.


## Abstractions and Interfaces

All smart-home devices implement a common interface, SmartDevice.
Each type of device provides its own behavior but is accessed through the shared contract.

## Bean Scopes and Lifecycle

Two different scopes are used:

* Singleton (default)

    * Most beans—such as devices, controller, registry, loggers—are created once and reused.

* Prototype

    * UserSessionContext is declared as prototype.
    * Each request for this bean results in a new instance, demonstrated using a dedicated controller endpoint.


## Aspect-Oriented Programming

A single aspect, LoggingAspect, intercepts method calls on all smart-home devices.
When a device is turned on, turned off, or has its level changed, an entry is recorded via StateChangeLogger.

## Programmatic Bean Registration

The application dynamically registers a new device (a smart plug) at startup using the application context.

## REST API

A REST controller exposes endpoints for:

* listing all devices
* reading an individual device by ID
* turning devices on/off
* setting device levels
* retrieving the event log produced by the aspect

All operations are identified through device IDs, allowing multiple devices of the same type to coexist and be controlled independently.