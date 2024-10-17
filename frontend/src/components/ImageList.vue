<template>
  <div class="container">
    <a href="/">← На главную</a>
    <h1 style="margin-bottom: 20px;">Список изображений</h1>
    <div class="list">
      <div class="list-item-container" v-for="imageObj in images" :key="imageObj">
        <img class="list-image" :src="imageObj.image" alt="Изображение">
        <div>
          <h2 style="color: black">{{ imageObj.title }}</h2>
          <p style="color: gray;">{{ imageObj.description }}</p>
        </div>
        <button @click="redirectToAnalyse" class="list-item-button">Кнопка</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      images: [],
      apiEndpoint: 'http://localhost:8080/api/v1/images/all',
    };
  },
  mounted() {
    this.fetchImages();
  },
  methods: {
    fetchImages() {
      axios.get(this.apiEndpoint, {
        headers: {
          'Authorization': 'Bearer ',
        }
      })
      .then(response => {
        this.images = response.data.images;
      })
      .catch(error => {
        console.error('Ошибка получения списка изображений:', error);
      });
    },
    redirectToAnalyse() {
      this.$router.push('/');
    }
  }
}
</script>

<style>
.container {
  margin: 20px;
  color: white;

}

.list-item-container {
  border: 0px solid transparent;
  border-radius: 10px;
  display: flex;
  flex-direction: row;
  gap: 20px;
  width: 150vh;
  margin-bottom: 20px;
  background-color: white;
}

.list-image {
  width: 150px;
  height: 150px;
  border: 0px solid black;
  border-radius: 10px;
}

.list-item-button {
  border: 1px solid black;
  border-radius: 10px;
  color: black;
  margin-left: auto;
  margin-right: 20px;
  margin-top: 7vh;
  margin-bottom: 7vh;
  padding: 10px;
  background-color: transparent;
}
</style>
