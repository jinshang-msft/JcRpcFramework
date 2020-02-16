# JcRpcFramework
A customized RPC framework based on underlying HTTP communication that can enable the client to remotely invoke the methods provided by the server.

## Architecture
This framework contains 6 different modules. 

- **jc-rpc-common**: Provides the helper funtions that can easily utilize the reflection features in JDK, including creating a new instance, getting all public methods and invoking the designated method in the given class.
- **jc-rpc-codec**: Provides the serialization and deserialization functionalities to transfer the Java objects via network.
- **jc-rpc-proto**: Defines the protocol of the communication between the client and server.
- **jc-rpc-transport**: Includes the HTTP implementation that enables the communication between client and server.
- **jc-rpc-server**: Includes the implementation of the RPC server, the configuration class, and other classes that enable service registration, look-up and management.
- **jc-rpc-client**: Includes the implementation fo the RPC client, the configuration class, the service selector and remote invoker.
- **jc-rpc-example**: Add a straightforward example that uses the developed RPC framework to call the calculator remotely.

## How to run
Pull the code to the local repo. 

Build and run com.jcshang.jcrpc.example.Server, which will register the CalculatorServiceImpl to the ServiceManager. 

Build and run com.jcshang.jcrpc.example.Client. The result of the remote call will be printed on the console.

## JDK Version
Java 11

## Dependencies
- Commons-io: 2.5
- Jetty-servlet: 9.4.19.v20190610
- FastJson: 1.2.58
- JUnit: 4.12
- Lombok: 1.18.8
- Slf4j: 1.7.26
- LogBack: 1.2.3

## Credits
@smgeek
