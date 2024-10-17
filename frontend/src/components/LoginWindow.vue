<script>
import axios from 'axios';

export default {
    data() {
        return {
            email: null,
            user_name: null,
            password: null,
            show_password: null,
        };
    },
    methods: {
        goRigister() {
            this.$router.push('/registration');
        },
    },
};
</script>

<template>
    <div class="web-page"> 
        <form class="main-container">
        <label class="title">Вход в аккаунт</label>
        
        <div class="div-email-username-password">
            <label class="label-email-username-password">Электронная почта или имя пользователя</label>
            <input class="input-email-username-password" type ="text" v-model="text" placeholder=" Электронная почта или имя пользователя" required/>
        </div>

        <div class="div-email-username-password">
            <label class="label-email-username-password">Пароль пользователя</label>
            <input class="input-email-username-password" :type ="show_password ? 'text' : 'password'" v-model="password" placeholder=" Пароль" required>
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

        <button class="button-login" @click = "handleClick">Войти</button>
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
