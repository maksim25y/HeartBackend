<script>
import axios from 'axios';

export default {
    data() {
        return {
            formData : {
                email: null,
                firstname: null,
                lastname: null,
                patronymic: null,
                password: null,
            },
            show_password: false,
        };
    },
    methods: {
        goLogin() {
            this.$router.push('/login');
        },

        submitData() {
            console.log(this.formData)
            axios.post("http://localhost:8080/api/v1/auth/register", this.formData)
            .then(response => {
                document.cookie = `authToken=${response.data.token}; path=/; SameSite=Strict;`
            })
            .catch(error => {
                alert("Ошибка ввода данных");
            });
        },

        getToken() {
            const value = `; ${document.cookie}`;
            const parts = value.split(`; authToken=`);
            if(parts.length === 2) {
                return parts.pop().split(';').shift();
            }
            return null;
        },
    },
};

</script>

<template>
    <div class="web-page"> 
        <form class="main-container" @submit.prevent="submitData">
        <label class="title">Регистрация аккаунта</label>

        <div class="name-of-info-and-input">
            <label class="label-name-of-info-and-input">Имя</label>
            <input class="input-name-of-info-and-input" type ="text" v-model="formData.firstname" placeholder=" Имя" required/>
        </div>

        <div class="name-of-info-and-input">
            <label class="label-name-of-info-and-input">Фамилия</label>
            <input class="input-name-of-info-and-input" type ="text" v-model="formData.lastname" placeholder=" Фамилия" required/>
        </div>

        <div class="name-of-info-and-input">
            <label class="label-name-of-info-and-input">Отчество</label>
            <input class="input-name-of-info-and-input" type ="text" v-model="formData.patronymic" placeholder=" Отчество" required/>
        </div>
        
        <div class="name-of-info-and-input">
            <label class="label-name-of-info-and-input">Электронная почта</label>
            <input class="input-name-of-info-and-input" type ="email" v-model="formData.email" placeholder=" Электронная почта" ref="" required/>
        </div>

        <div class="name-of-info-and-input">
            <label class="label-name-of-info-and-input">Пароль пользователя</label>
            <input class="input-name-of-info-and-input" :type ="show_password ? 'text' : 'password'" v-model="formData.password" placeholder=" Пароль" required/>
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
    height: 575px;
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
