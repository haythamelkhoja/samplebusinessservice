FROM websphere-liberty

EXPOSE 9080

ADD target/business.war /config/dropins

ADD src/main/resources/jvm.options /config
ADD src/main/resources/server.xml /config
ADD src/main/resources/mysql /opt/ibm/wlp/usr/shared/resources/mysql
