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
      apiEndpoint: 'http://web:3000/',
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
    const formData = new FormData();
    formData.append('image', this.$refs.fileInput.files[0]);

    axios.post(this.apiEndpoint, formData)
      .then(response => {
        if (response.status == 200 || response.status == 201) {
          this.imageSrc = null;
        } else {
          alert('Ошибка отправки изображения.');
        }
      })
      .catch(error => {
        alert('Ошибка отправки изображения.');
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