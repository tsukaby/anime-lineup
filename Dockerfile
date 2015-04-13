FROM centos

MAINTAINER tsukaby

# Install common tools
RUN yum install -y git curl; yum upgrade -y; yum update -y

# Install java
ENV JDK_VERSION 8u40
ENV JDK_BUILD_VERSION b26
RUN curl -LO "http://download.oracle.com/otn-pub/java/jdk/$JDK_VERSION-$JDK_BUILD_VERSION/jdk-$JDK_VERSION-linux-x64.rpm" -H 'Cookie: oraclelicense=accept-securebackup-cookie' && rpm -i jdk-$JDK_VERSION-linux-x64.rpm; rm -f jdk-$JDK_VERSION-linux-x64.rpm; yum clean all
RUN yum clean all
ENV JAVA_HOME /usr/java/default

# Copy application
ADD standalone-build/target/scala-2.11/anime-lineup* /usr/local/anime-lineup.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dskinny.port=8080", "-Dskinny.env=production", "/usr/local/anime-lineup.jar"]
