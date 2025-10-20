export default defineNuxtConfig({
  compatibilityDate: '2025-10-18',
  devtools: { enabled: true },
  css: [
    '@mdi/font/css/materialdesignicons.css'
  ],
  build: {
    transpile: ['vuetify']
  },
  runtimeConfig: {
    public: {
      apiBase: process.env.API_BASE_URL || 'http://localhost:8080'
    }
  },
  typescript: {
    strict: false
  }
})