<script>
import axios from 'axios';

export default {
    data() {
        return {
            first_name: null,
            last_name: null,
            patronymic: null,
            user_name: null,
            email: null,
            password: null,
            show_password: false,
        };
    },
    methods: {
        goLogin() {
            this.$router.push('/login');
        },
    },
};

</script>

<template>
    <div class="web-page"> 
        <form class="main-container">
        <label class="title">Регистрация аккаунта</label>

        <div class="name-of-info-and-input">
            <label class="label-name-of-info-and-input">Имя</label>
            <input class="input-name-of-info-and-input" type ="text" v-model="first_name" placeholder=" Имя" required/>
        </div>

        <div class="name-of-info-and-input">
            <label class="label-name-of-info-and-input">Фамилия</label>
            <input class="input-name-of-info-and-input" type ="text" v-model="last_name" placeholder=" Фамилия" required/>
        </div>

        <div class="name-of-info-and-input">
            <label class="label-name-of-info-and-input">Отчество</label>
            <input class="input-name-of-info-and-input" type ="text" v-model="patronymic" placeholder=" Отчество" required/>
        </div>

        <div class="name-of-info-and-input">
            <label class="label-name-of-info-and-input">Имя пользователя (логин)</label>
            <input class="input-name-of-info-and-input" type ="text" v-model="user_name" placeholder=" Имя пользователя" required/>
        </div>
        
        <div class="name-of-info-and-input">
            <label class="label-name-of-info-and-input">Электронная почта</label>
            <input class="input-name-of-info-and-input" type ="email" v-model="email" placeholder=" Электронная почта" ref="" required/>
        </div>

        <div class="name-of-info-and-input">
            <label class="label-name-of-info-and-input">Пароль пользователя</label>
            <input class="input-name-of-info-and-input" :type ="show_password ? 'text' : 'password'" v-model="password" placeholder=" Пароль" required/>
            <label class="show-password">
                Показать пароль
                <input type="checkbox" class="checkbox" v-model="show_password"> 
        </label>
        </div>

        <div class="labels-exist-account">
             <label>Есть аккаунт?</label>
            <label class="label-exist-account" @click="goLogin">Войти</label>
        </div>    

        <button class="button-register" type="submit" @click = "goRegister">Зарегистрироваться</button>
        </form>
    </div>
</template>

<style scoped>
.web-page {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

.main-container {
    display: flex;
    align-items: center;
    justify-content: space-evenly;
    flex-direction: column;
    height: 650px;
    width: 420px;
    background-color: white;
    border-color: white;
    border-radius: 20px;
    border-style: solid;
    font-family: sans-serif;
    margin-right: 10px;
    margin-left: 10px;
}

.title {
    font-size: 30px;
}

.name-of-info-and-input {
    display: flex;
    flex-direction: column;
    gap: 15px;
    align-items: center;
}

.label-name-of-info-and-input {
    font-size: 16px;
    font-family: Arial;
    color: rgb(0, 0, 0);
    width: 320px;
    height: 15px;
}

.input-name-of-info-and-input {
    font-size: 14px;
    width: 320px;
    border-color: #000;
    border-style: solid;
    border-radius: 7px;
    border-width: 2px;
    height: 30px;
    padding-left: 7px;
    padding-right: 7px;
}

.show-password {
    display: flex;
    flex-direction: row;
    gap: 3px;
}

.checkbox {
    cursor: pointer;
}

.labels-exist-account {
    display: flex;
    flex-direction: row;
    gap: 3px;
    font-size: 13px;
    color: gray;
}

.label-exist-account {
    font-size: 13px;
    color: rgb(0, 157, 255);
    cursor: pointer;
    text-decoration: underline;
}

.button-register {
    font-size: 14px;
    border-radius: 7px;
    width: 320px;
    height: 40px;
    color: black;
    background-color: white;
    text-align: center;
    cursor: pointer;
}

</style>
