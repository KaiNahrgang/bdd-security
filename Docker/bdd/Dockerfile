# Use OpenJDK 8
FROM openjdk:8-jdk

ENV URL "http://servletsearch.com/Servlet-Search/"
ENV TAGS "@app_scan"
ENV TAGS_SKIP "~@skip"
ENV PROXY_HOST "127.0.0.1"
ENV PROXY_PORT "8090"
ENV API_KEY "12345"

# Set a sensible server directory.
WORKDIR /home/bdd-security

# Add the directory
ADD ../../ .

# run gradle
RUN ./gradlew buildIt

# Execute gradle testsi
CMD sed -E -i "s,<baseUrl>.+</baseUrl>,<baseUrl>$URL</baseUrl>," config.xml && sed -E -i "s,<host>.+</host>,<host>$PROXY_HOST</host>," config.xml && sed -E -i "s,<port>.+</port>,<port>$PROXY_PORT</port>," config.xml && sed -E -i "s,<api>.+</api>,<api>$API_KEY</api>," config.xml  && ./gradlew -Dcucumber.options="--tags ${TAGS}"

