# PiAPI Java Library
##### [Bolillo Kremer](https://youtube.com/BolilloKremer?https://www.youtube.com/BolilloKremer?sub_confirmation=1)

## Overview
This user friendly library allows you to easily interface with multiple raspberry pi's at once using [PiAPI](https://github.com/Bolillo-Kremer/PiAPI). The simplicity of this library makes it easy for anybody to use. Most functionality is based off of [onoff](https://www.npmjs.com/package/onoff), which is running on [PiAPI](https://github.com/Bolillo-Kremer/PiAPI).

For updates on this project and other other entertainging coding projects, please subscribe to my YouTube channel, [Bolillo Kremer](https://youtube.com/BolilloKremer?https://www.youtube.com/BolilloKremer?sub_confirmation=1). 

## How to use

### Requirements
This library requires that [PiAPI](https://github.com/Bolillo-Kremer/PiAPI) is running on your raspberry pi. You can install it on your pi with just one command! To see instructions, [click here](https://github.com/Bolillo-Kremer/PiAPI/blob/master/README.md).

### Initializing
You can download the latest .jar library by clicking [here](#)
After downloading and adding a reference to this library in your project, you will need to setup you PiAPI connection like this.

```java
import PiAPI.*;
```
```java
static public void Main(String[] args) 
{ 
    String ipAddress = "192.168.1.100";
    long port = Pi.DefaultPort; //(Default port = 5000)
    
    //Initialize Pi object with IPAddress and port of pi
    Pi myPi = new Pi(ipAddress, port);

    //You need to specify which pins will be set as input or output
    myPi.initPin(2, "in");
    myPi.initPin(3, "out");
}
```

If you would rather provide a specific url than using an IP address and a port, you can do so like this.
```java
Pi myPi = new Pi("http://192.168.1.100:5000");
```


### Interfacing

You can get the state (0 or 1) of a given pin using this function
```java
//Returns the state of pin 2 as a string
myPi.getState(2);
```

You can also get a JSON formatted string of all the pin states using this function.

```java
//Returns a Newtonsoft.Json.Linq.JObject
myPi.getStates();
```
If you want to set the state (0 or 1) of a pin, use this function
```java
//Sets pin 2 to state 0
myPi.setState(2, 0);
```
Alternatively, you can use "toggle" to toggle the pins state
```java
//Sets pin 2 to state 0
myPi.setState(2, "toggle");
```

If you wish to set the state of all initiated pins, you can do so with the setAllStates() function.

#### Customize PiAPI

If you add any GET or POST methods to PiAPI on your Pi, you can access them with the Get and Post functions in PiAPI.Utilities.
Additionaliy, you can access the raw url of PiAPI by calling Utilities.RawUrl.

##### Example
```java
//Posts "Some Content" to PiAPI
String POSTResponse = Utilities.post(MyPi.RawUrl + "/SomePost", "Some content");

//Gets Response from PiAPI
String GETResponse = Utilities.get(MyPi.RawUrl + "/SomeGet");
```

### API Settings

This library also allwos you to interface with the PiAPI settings.

Current Settings:
* Port (Gets or sets the port that the API is running on)
* (IN DEVELOPMENT) Keys (Gets or sets keys that must be provided upon each request)

The settings will take place on server reboot.

#### Example
```java
//Changes port to 6000
myPi.setAPIPort(6000);
```

### Extensions
Responses from the server will always come return in the form of a String. You can use googles [Gson](https://github.com/google/gson) library to parse these strings into objects.

Objects can also be converted back into a JSON formatted string with this extension
* Utilities.toJSON() (Converts a valid object to a JSON formatted string)

### Helpers
Memorizing all the terminology such as "in", "out", "up", "down" can be confusing for some. That's where PiAPI.Helpers come in. These are just simple constant variables that can help you use PiAPI more easily.

#### Example

```java
import PiAPI.*;
import PiAPI.Helpers.*;
```
```java
Pi myPi = new Pi("192.168.1.100", Pi.defaultPort());

myPi.initPin(2, Pin.in(), Edge.rising());
myPi.setState(2, Pin.high());

myPi.cleanExit();
```

For updates on this project and other other entertainging coding projects, please subscribe to my YouTube channel, [Bolillo Kremer](https://youtube.com/BolilloKremer?https://www.youtube.com/BolilloKremer?sub_confirmation=1). 
