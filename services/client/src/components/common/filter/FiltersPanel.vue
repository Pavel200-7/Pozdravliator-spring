<script setup>
import SearchInput from '../../common/filter/InnerWidget/SearchInput.vue'
import SortBySelect from '../../common/filter/InnerWidget/SortBySelect.vue'
import SortDirectionSelect from '../../common/filter/InnerWidget/SortDirectionSelect.vue'
import PaginationButtons from '../../common/filter/InnerWidget/PaginationButtons.vue'

const props = defineProps({
  search: {
    type: String,
    default: ''
  },
  sortBy: {
    type: String,
    default: 'ID'
  },
  sortDirection: {
    type: String,
    default: 'ASCENDING'
  },
  pageSize: {
    type: Number,
    default: 25
  },
  page: {
    type: Number,
    default: 0
  },
  hasNext: {
    type: Boolean,
    default: false
  },
  hasPrev: {
    type: Boolean,
    default: false
  },
  total: {
    type: Number,
    default: 0
  },
  totalPages: {
    type: Number,
    default: 0
  },
  isLoading: {
    type: Boolean,
    default: false
  },
  sortByOptions: {
    type: Object,
    default: []
  },
  searchDescription: {
    type: String,
    default: ""
  }
})

const emit = defineEmits([
  'update:search',
  'update:sortBy',
  'update:sortDirection',
  'update:pageSize',
  'update:page',
  'search',
  'prev',
  'next',
  'first',
  'last',
  'refresh'
])

function onSearchUpdate(value) {
  emit('update:search', value)
  emit('search', value)
}

function onSortByUpdate(value) {
  emit('update:sortBy', value)
  emit('refresh')
}

function onSortDirectionUpdate(value) {
  emit('update:sortDirection', value)
  emit('refresh')
}

function onRefresh() {
  emit('refresh')
}

function onFirst() {
  emit('first')
}

function onPrev() {
  emit('prev')
}

function onNext() {
  emit('next')
}

function onLast() {
  emit('last')
}

function onPageUpdate(value) {
  emit('update:page', value)
}

function onPageSizeUpdate(value) {
  emit('update:pageSize', value)
  emit('refresh')
}
</script>

<template>
  <div class="filters-panel">
    <div class="filters-top">
      <div class="search-section">
        <SearchInput
          :model-value="search"
          :is-loading="isLoading"
          :placeholder="searchDescription"
          @refresh="onSearchUpdate"
        />
      </div>
    </div>

    <div class="filters-bottom">
      <div class="sorting-filters">
        <SortBySelect
          :model-value="sortBy"
          :options="sortByOptions"
          :is-loading="isLoading"
          @update:model-value="onSortByUpdate"
        />
        
        <SortDirectionSelect
          :model-value="sortDirection"
          :is-loading="isLoading"
          @update:model-value="onSortDirectionUpdate"
        />
      </div>

      <div class="pagination-section">
        <PaginationButtons
          :page="page"
          :page-size="pageSize"
          :has-next="hasNext"
          :has-prev="hasPrev"
          :total="total"
          :total-pages="totalPages"
          :is-loading="isLoading"
          :show-page-size="true"
          @update:page="onPageUpdate"
          @update:page-size="onPageSizeUpdate"
          @first="onFirst"
          @prev="onPrev"
          @next="onNext"
          @last="onLast"
          @refresh="onRefresh"
        />
      </div>
    </div>
  </div>
</template>

<style scoped>
.filters-panel {
  background: white;
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
  border: 1px solid #e2e8f0;
}

.filters-top {
  margin-bottom: 20px;
  padding-bottom: 20px;
  border-bottom: 1px solid #e2e8f0;
}

.search-section {
  max-width: 500px;
}

.filters-bottom {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 30px;
  align-items: start;
}

.sorting-filters {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 15px;
}

.pagination-section {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

@media (max-width: 1024px) {
  .filters-bottom {
    grid-template-columns: 1fr;
    gap: 20px;
  }
  
  .pagination-section {
    align-items: stretch;
  }
}

@media (max-width: 768px) {
  .sorting-filters {
    grid-template-columns: 1fr;
    gap: 15px;
  }
}

@media (max-width: 640px) {
  .filters-panel {
    padding: 15px;
  }
  
  .filters-top {
    padding-bottom: 15px;
    margin-bottom: 15px;
  }
}
</style>