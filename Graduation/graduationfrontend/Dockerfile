# FROM node:15.3.0
FROM node:15-alpine
ENV NODE_ENV=development
WORKDIR /usr/src/app
COPY . .
RUN node -v
# RUN npm install -g nodemon
# RUN npm install -g express
RUN npm install
EXPOSE 80
CMD [ "npm", "start" ]