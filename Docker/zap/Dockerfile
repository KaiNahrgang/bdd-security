FROM owasp/zap2docker-stable
ENV PORT "8090"
ENV HOST "0.0.0.0"
ENV CONFIG ""
CMD zap.sh -daemon -port $PORT -host $HOST $CONFIG