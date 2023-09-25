FROM node:19

# ワーキングディレクトリの作成
RUN mkdir -p /app/vue
WORKDIR /app/vue
COPY package*.json ./
RUN npm install
COPY . .