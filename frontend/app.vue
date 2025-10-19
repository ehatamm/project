<template>
  <div>
    <NuxtLayout>
      <NuxtPage />
    </NuxtLayout>
    
    <!-- Simple inline error snackbar -->
    <div
      v-if="showError"
      style="position: fixed; top: 20px; right: 20px; z-index: 1000; background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; border-radius: 8px; padding: 16px; max-width: 500px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);"
    >
      <div style="display: flex; align-items: center; justify-content: space-between;">
        <span style="flex: 1; font-weight: 500;">{{ errorMessage }}</span>
        <button @click="hideError" style="background: none; border: none; color: inherit; cursor: pointer; font-size: 20px; font-weight: bold; margin-left: 12px; padding: 0;">&times;</button>
      </div>
    </div>
  </div>
</template>

<script setup>
const { errorMessage, showError, hideError, showErrorMessage } = useErrorHandler()

// Provide the error handler to all child components
provide('errorHandler', { showErrorMessage, hideError })
</script>
