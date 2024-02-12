FROM --platform=linux/x86_64 openjdk:17-alpine

ADD ./build/distributions/*.tar /

EXPOSE 80 443

ENTRYPOINT ["/jwt-verify-api/bin/jwt-verify-api"]