# CPU-V
## Introduce
This software is a work designed by a course in my junior year. It is made using JDK8 and JavaFX. It is a CPU parameter monitoring program modeled on CPU-Z and Windows task manager.
I found it very interesting so I wrote it to play.
![image](https://github.com/VioletQin/CPU-V/blob/main/image-20230224173132178.png)

# IDEA Start
Use maven or manually import jar dependencies
```
oshi-core-6.4.0:
https://mvnrepository.com/artifact/com.github.oshi/oshi-core

jna-5.13.0:
https://mvnrepository.com/artifact/net.java.dev.jna/jna

jna-platform-5.13.0:
https://mvnrepository.com/artifact/net.java.dev.jna/jna-platform

log4j-1.2.17
slf4j-api-1.7.25
slf4j-log4j12-1.7.25
```

The process of manually importing the jar package is as follows:

Create a lib package under the project and import the above 5 jar packages
Then click `File -> Project Structure -> Libraries` in the IDEA to configure jar package dependencies

Then click start and you'll be fine.

# At Last
Because I am a programmer from China, the comments and text in the code are basically in Chinese. If there are many stars and someone asks, I will consider changing the Chinese comments in the source code to English.
