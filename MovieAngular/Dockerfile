FROM nginx:alpine

COPY dist /usr/share/nginx/html

COPY nginx.conf.template /etc/nginx/nginx.conf.template

ENV NGINX_PORT=90
ENV API_GATEWAY_HOST=host.docker.internal
ENV API_GATEWAY_PORT=8082

CMD envsubst '${NGINX_PORT} ${API_GATEWAY_HOST} ${API_GATEWAY_PORT}' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf && \
    nginx -g "daemon off;"
