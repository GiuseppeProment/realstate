FROM java:8
MAINTAINER giuseppe.proment@gmail.com
ENV _JAVA_OPTIONS "-Dfile.encoding=UTF8"

RUN apt-get update  && \
    echo "==> Install git & helper tools..."  && \
    DEBIAN_FRONTEND=noninteractive \
        apt-get install -y -q --no-install-recommends git  && \
    \
    echo "==> Download source..."  && \
    cd /tmp  && \
    git clone https://github.com/GiuseppeProment/realstate.git  && \
    cd realstate  && \

# configure
# HTTP port
EXPOSE 8080

# Define default command.
ENTRYPOINT ["mvn", "jetty:run"]

