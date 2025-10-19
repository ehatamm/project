<template>
  <div
    v-if="show"
    :class="[
      'snackbar',
      type === 'error' ? 'snackbar-error' : 'snackbar-success'
    ]"
  >
    <div class="snackbar-content">
      <span class="snackbar-message">{{ message }}</span>
      <button @click="$emit('close')" class="snackbar-close">
        <span>&times;</span>
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
interface Props {
  show: boolean
  message: string
  type?: 'error' | 'success'
}

defineProps<Props>()
defineEmits<{
  close: []
}>()
</script>

<style scoped>
.snackbar {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1000;
  min-width: 300px;
  max-width: 500px;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  animation: slideIn 0.3s ease-out;
}

.snackbar-error {
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
  color: #721c24;
}

.snackbar-success {
  background-color: #d4edda;
  border: 1px solid #c3e6cb;
  color: #155724;
}

.snackbar-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 16px;
}

.snackbar-message {
  flex: 1;
  font-weight: 500;
  line-height: 1.4;
}

.snackbar-close {
  background: none;
  border: none;
  color: inherit;
  cursor: pointer;
  font-size: 20px;
  font-weight: bold;
  margin-left: 12px;
  padding: 0;
  opacity: 0.7;
  transition: opacity 0.2s;
}

.snackbar-close:hover {
  opacity: 1;
}

@keyframes slideIn {
  from {
    transform: translateX(100%);
    opacity: 0;
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

/* Responsive design */
@media (max-width: 768px) {
  .snackbar {
    top: 10px;
    right: 10px;
    left: 10px;
    min-width: auto;
    max-width: none;
  }
}
</style>
