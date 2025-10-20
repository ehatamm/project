<template>
  <v-snackbar
    v-model="show"
    :color="type === 'error' ? 'error' : 'success'"
    :timeout="5000"
    location="top right"
    variant="elevated"
  >
    <div class="d-flex align-center">
      <v-icon class="mr-2">
        {{ type === 'error' ? 'mdi-alert-circle' : 'mdi-check-circle' }}
      </v-icon>
      <span>{{ message }}</span>
    </div>
    
    <template #actions>
      <v-btn
        :color="type === 'error' ? 'on-error' : 'on-success'"
        variant="text"
        @click="$emit('close')"
        icon="mdi-close"
      ></v-btn>
    </template>
  </v-snackbar>
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
