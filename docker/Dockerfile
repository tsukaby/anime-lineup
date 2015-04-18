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
RUN mkdir /usr/local/anime-lineup
# RUN git clone https://github.com/tsukaby/anime-lineup.git /usr/local/anime-lineup
ADD anime-lineup.jar /usr/local/anime-lineup/anime-lineup.jar

WORKDIR /usr/local/anime-lineup

EXPOSE 8080

# CMD cd /usr/local/anime-lineup && git pull

ENTRYPOINT ["java", "-jar", "-Dskinny.port=8080", "-Dskinny.env=production", "anime-lineup.jar"]
