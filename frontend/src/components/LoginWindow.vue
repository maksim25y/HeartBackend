<script>
import axios from 'axios';

export default {
    data() {
        return {
            formData : {
                email: 'null',
                password: 'null',
            },
            show_password: null,
        }
    },
    methods: {
        goRigister() {
            this.$router.push('/registration');
        },

        submitData() {
            console.log(this.formData)
            axios.post('http://localhost:8080/api/v1/auth/authenticate', this.formData)
            .then(response => {
                document.cookie = `authToken=${response.data.token}; path=/; SameSite=Strict;`;
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
        <label class="title">Вход в аккаунт</label>
        
        <div class="div-email-username-password">
            <label class="label-email-username-password">Электронная почта

            </label>
            <input class="input-email-username-password" type ="email" v-model="formData.email" placeholder=" Электронная почта " required/>
        </div>

        <div class="div-email-username-password">
            <label class="label-email-username-password">Пароль пользователя</label>
            <input class="input-email-username-password" :type ="show_password ? 'text' : 'password'" v-model="formData.password" placeholder=" Пароль" required>
            <label class="show-password">
                Показать пароль
                <input type="checkbox" v-model="show_password"> 
        </label>
        </div>

        <div class="register-forget">
    
            <div class="labels-forget-register">
                <label>Нет аккаунта?</label>
                <label class="label-recovery-register" @click="goRigister">Зарегистрироваться</label>
            </div>    
            <div class="labels-forget-register">
                <label>Не помните пароль?</label>
                <label class="label-recovery-register">Восстановить доступ</label>
            </div>
        </div>

        <button type="submit" class="button-login">Войти</button>
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
    height: 400px;
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

.div-email-username-password {
    display: flex;
    flex-direction: column;
    gap: 15px;
    align-items: center;
}

.label-email-username-password {
    font-size: 16px;
    font-family: Arial;
    color: rgb(0, 0, 0);
    width: 320px;
    height: 15px;
}

.input-email-username-password {
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

.register-forget {
    display: flex;
    align-items: center;
    flex-direction: column;
    gap: 10px;
}

.show-password {
    display: flex;
    flex-direction: row;
    gap: 3px;
    
}

.labels-forget-register {
    display: flex;
    flex-direction: row;
    gap: 3px;
    font-size: 13px;
    color: gray;
}

.label-recovery-register {
    font-size: 13px;
    color: rgb(0, 157, 255);
    cursor: pointer;
    text-decoration: underline;
}

.button-login {
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
