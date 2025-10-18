FROM node:20-alpine AS build
WORKDIR /app

# Copy package files first for better layer caching
COPY package*.json ./

# Install dependencies (this layer will be cached if package.json doesn't change)
RUN npm ci --only=production --silent

# Copy source code
COPY . .

# Build the application
RUN npm run build

FROM node:20-alpine
WORKDIR /app

# Create non-root user for security
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# Copy built application
COPY --from=build --chown=appuser:appgroup /app/.output ./.output

USER appuser

EXPOSE 3000
ENV HOST=0.0.0.0
CMD ["node", ".output/server/index.mjs"]
