FROM java:8
MAINTAINER Metrics Data Team<metrics@spotahome.com>

WORKDIR /workshop-tagless-final

COPY certificates/spotahome-ca.crt /usr/local/share/ca-certificates/spotahome-ca.crt
COPY certificates/app.jar /workshop-tagless-final/app.jar

RUN update-ca-certificates

COPY contrib /contrib

ENV PATH ${PATH}:/contrib

CMD ["workshop-tagless-final.sh", "app"]

EXPOSE 2553
EXPOSE 9095