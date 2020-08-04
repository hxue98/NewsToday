FROM node:14.7.0
EXPOSE 3000
WORKDIR /
ENV PATH /node_modules/.bin:$PATH
COPY . ./
RUN npm install --silent
RUN npm install react-scripts@3.4.1 -g --silent
ENTRYPOINT ["npm", "start"]