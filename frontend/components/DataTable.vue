<template>
  <v-data-table
    :headers="headers"
    :items="items"
    :loading="loading"
    class="elevation-2 rounded-lg"
    :item-props="itemProps"
    hover
    density="comfortable"
  >
    <template v-for="(_, slot) in $slots" :key="slot" #[slot]="slotProps">
      <slot :name="slot" v-bind="slotProps"></slot>
    </template>
  </v-data-table>
</template>

<script setup lang="ts">
interface TableHeader {
  title: string
  key: string
  sortable?: boolean
  align?: 'start' | 'center' | 'end'
  width?: string | number
}

interface Props {
  headers: TableHeader[]
  items: Record<string, any>[]
  loading?: boolean
}

withDefaults(defineProps<Props>(), {
  loading: false
})

const itemProps = (item: any) => ({
  class: 'text-body-1'
})
</script>
