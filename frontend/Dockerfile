#### Stage 1: Build the react application
FROM node:17 as build

# Configure the main working directory inside the docker image. 
# This is the base directory used in any further RUN, COPY, and ENTRYPOINT 
# commands.
WORKDIR /app

# Copy the package.json as well as the package-lock.json and install 
# the dependencies. This is a separate step so the dependencies 
# will be cached unless changes to one of those two files 
# are made.
COPY package.json package-lock.json ./
RUN npm install

# Copy the main application
COPY . ./

# Build the application
RUN npm run build

#### Stage 2: Serve the React application from Nginx 
FROM nginx:latest

# Remove the default Nginx configuration file
RUN rm -rf /etc/nginx/conf.d/default.conf

# Copy the react build from Stage 1 to the Nginx public directory
COPY --from=build /app/build /usr/share/nginx/html

# Copy our custom nginx configuration file
COPY nginx.conf ./nginx.conf

# Expose port 80 to the Docker host, so we can access it 
# from the outside.
EXPOSE 3000

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]