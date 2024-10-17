<template>
  <div class="container">
    <div class="drop-zone" @dragover.prevent @drop.prevent="handleDrop">
      <input type="file" @change="handleFileSelect" accept="image/*" ref="fileInput" style="display: none;" />
      <div v-if="!imageSrc" class="drop-zone__content">
        <i class="fas fa-upload"></i>
        <p style="color: #000;">Перетащите изображение сюда или нажмите, чтобы выбрать файл</p>
      </div>
      <div v-else class="drop-zone__content">
        <img :src="imageSrc" class="drop-zone__image" />
      </div>
    </div>
    <button @click="handleSubmit" :disabled="!imageSrc">Отправить</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      imageSrc: null,
      apiEndpoint: 'http://localhost:8080/api/v1/images',
      formData: {
        image: null,
        title: null,
        creation_date: null,
        description: null,
      }
    };
  },
methods: {
  handleDrop(event) {
    const file = event.dataTransfer.files[0];
    this.handleFile(file);
  },
  handleFileSelect(event) {
    this.handleFile(event.target.files[0]);
  },
  handleFile(file) {
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        this.imageSrc = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  },
  handleSubmit() {
    this.formData.image = this.imageSrc;
    this.formData.title = 'Исследование';
    this.formData.description = 'На изображении нарушений не обнаружено';
    this.formData.creation_date = '2024-10-11T16:33:49.76Z';

    axios.post(this.apiEndpoint, this.formData, {
      headers: {
        'Authorization': 'Bearer ',
      }
    })
      .then(response => {
        this.imageSrc = null;
      })
      .catch(error => {
        console.error('Ошибка отправки изображения:', error);
      })
  },
},
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: auto;
}

.drop-zone {
  width: 200px;
  height: 200px;
  border: 2px dashed #ccc;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  margin-bottom: 20px;
}

.drop-zone__content {
  text-align: center;
}

.drop-zone__image {
  max-width: 100%;
  max-height: 100%;
}

.fas {
  font-size: 3em;
  color: #ccc;
}
</style>