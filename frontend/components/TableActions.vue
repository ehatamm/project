<template>
  <div class="d-flex ga-2">
    <v-btn
      v-if="showEdit"
      color="success"
      size="small"
      variant="elevated"
      prepend-icon="mdi-pencil"
      @click="handleEdit"
    >
      Edit
    </v-btn>
    <v-btn
      v-if="showDelete"
      color="error"
      size="small"
      variant="elevated"
      prepend-icon="mdi-delete"
      :loading="isDeleting"
      @click="handleDelete"
    >
      {{ isDeleting ? 'Deleting...' : 'Delete' }}
    </v-btn>
    <slot :item="item"></slot>
  </div>
</template>

<script setup lang="ts">
import type { Project } from '~/types/project'

interface Props {
  item: Project
  showEdit?: boolean
  showDelete?: boolean
  isDeleting?: boolean
}

interface Emits {
  edit: [item: Project]
  delete: [item: Project]
}

const props = withDefaults(defineProps<Props>(), {
  showEdit: true,
  showDelete: true,
  isDeleting: false
})

const emit = defineEmits<Emits>()

const handleEdit = () => {
  emit('edit', props.item)
}

const handleDelete = () => {
  emit('delete', props.item)
}
</script>
