<template>
  <div>
    <v-card v-if="loading" elevation="4" rounded="lg" class="pa-8 text-center">
      <v-progress-circular 
        indeterminate 
        color="primary" 
        size="64"
        width="6"
      ></v-progress-circular>
      <div class="text-h6 mt-4 on-surface">Loading projects...</div>
    </v-card>

    <v-alert
      v-else-if="isEmpty"
      type="info"
      variant="tonal"
      class="mb-4"
      rounded="lg"
      border="start"
      border-color="primary"
      border-width="4"
    >
      <template #prepend>
        <v-icon size="large" color="info">mdi-information</v-icon>
      </template>
      <div class="text-h6">{{ emptyMessage }}</div>
    </v-alert>

    <DataTable
      v-else
      :headers="headers"
      :items="items"
      :loading="loading"
    >
      <template v-for="(_, slot) in $slots" :key="slot" #[slot]="slotProps">
        <slot :name="slot" v-bind="slotProps"></slot>
      </template>
    </DataTable>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import type { Project } from '~/types/project'

interface TableHeader {
  title: string
  key: string
  sortable?: boolean
  align?: 'start' | 'center' | 'end'
  width?: string | number
}

interface Props {
  headers: TableHeader[]
  items: Project[]
  loading?: boolean
  emptyMessage?: string
}

const props = withDefaults(defineProps<Props>(), {
  loading: false,
  emptyMessage: 'No items found.'
})

const isEmpty = computed(() => props.items.length === 0)
</script>
