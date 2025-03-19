FROM node:20-alpine as build
LABEL authors="onkarpdsharma@gmail.com"



WORKDIR /app

COPY ./package.json ./package.json
COPY ./package-lock.json ./package-lock.json
COPY ./src ./src
COPY ./public ./public

ENV NODE_PATH src
ENV PATH /application/node_modules/.bin:$PATH
RUN npm config set proxy http://10.52.116.27:8080

RUN npm install 
RUN npm run build

# -------------------------- #
FROM nginx:1.16.0-alpine
COPY --from=build /application/build /usr/share/nginx/html
COPY ./config/nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]