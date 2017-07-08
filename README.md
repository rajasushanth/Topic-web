# Topic Web

Topic web is the presentation layer for Topic application built using boostrap and Thymeleaf - a powerful templating engine with default support for Spring framework. It consumes the REST API exposed by Topic Service and authenticated via JWT tokens.

## Getting Started

Clone the repository to your local by executing the command in git bash.

```git clone https://github.com/rajasushanth/Topic-web.git```

## Prerequisites

* JDK v1.8 or above
* Maven v3.0.5 or above

## Installing

1. Verify following conditions are met
* [Topic Config](https://rajasushanth.github.io/Topic-config/) service running
* [Topic Service](https://rajasushanth.github.io/Topic-service/) service running
2. Navigate to the project root where pom.xml resides
3. Built the WAR module by executing ```mvn clean install```

## Deployment 
**Running it in local**

1. Navigate tho the path where WAR build resides
2. Execute the command in the command line
```java -jar topic-web-0.0.1-SNAPSHOT.jar```

**Running in Pivotal cloud foundry**
1. Create an account in [Pivotal cloud foundry](https://login.run.pivotal.io/login)
2. Install the [Cloud Foundry CLI](https://docs.cloudfoundry.org/cf-cli/install-go-cli.html)
3. Get the [manifest.yml](https://github.com/rajasushanth/Topic-manifest/blob/master/topic-web/manifest.yml)
4. Execute the command ```cf push```

## Author

* **RajaSushanth** - [GitHub RajaSushanth](https://github.com/rajasushanth)
