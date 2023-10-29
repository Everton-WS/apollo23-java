# Use the official Nginx base image
FROM nginx:latest

# Copy the custom Nginx configuration file
COPY nginx.conf /etc/nginx/nginx.conf

# Copy the index.html file to the Nginx document root
COPY index.html /usr/share/nginx/html/

# Expose port 80 for HTTP
EXPOSE 80
EXPOSE 443

# instale apk e certbot
RUN apt-get update && \
    apt-get install -y software-properties-common && \
    apt-get install -y certbot python3-certbot-nginx 

RUN certbot certificates

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]