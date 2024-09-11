<template>
    <div class="body">
        <div class="register">
            <h1>회원가입</h1>
            <form @submit.prevent="handleSubmit">
                <div class="register-element">
                    <p>
                        <label for="name">
                            <input type="text" id="name" class="input-field" v-model="name" placeholder="이름">
                        </label>
                    </p>

                    <p>
                        <label for="email">
                            <input type="email" id="email" class="input-field" v-model="email" placeholder="이메일">
                        </label>
                    </p>

                    <p>
                        <label for="pass">
                            <input type="password" id="pass" class="input-field" v-model="password" placeholder="비밀번호">
                        </label>
                    </p>

                    <button type="submit" class="submit-btn">회원가입</button>

                    <button type="button" class="google-btn" @click="loginWithGoogle">
                        <img src="https://www.svgrepo.com/show/355037/google.svg" alt="Google" class="google-icon">
                        Sign up with Google
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup>
import {ref} from 'vue';
import {useRouter} from 'vue-router';
import axios from 'axios';

const name = ref('');
const email = ref('');
const password = ref('');
const router = useRouter();

const loginWithGoogle = () => {
    const clientId = '693391011569-gkshbnptgoqoi0ps8crkm1bp6f54lq7i.apps.googleusercontent.com'; // 실제 Google Client ID로 변경
    const redirectUri = 'http://localhost:3000/oauth2/callback'; // OAuth 콜백 처리 URL
    const scope = 'https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile';
    const responseType = 'token';

    const authUrl = `https://accounts.google.com/o/oauth2/auth?` +
                    `client_id=${clientId}&` +
                    `redirect_uri=${redirectUri}&` +
                    `response_type=${responseType}&` +
                    `scope=${scope}`;

    window.location.href = authUrl;
}

const handleSubmit = async () => {
    try {
        const response = await axios.post("http://localhost:8080/api/user/register", {
            user_name: name.value,
            email: email.value,
            password: password.value
        });

        alert("회원가입 성공 !");
        console.log(response.data);

        router.push('/login');
    } catch (error) {
        console.error(error);
    }
}
</script>

<style scoped>
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    font-family: 'Arial', sans-serif;
}

.body {
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f5f5f5;
}

.register {
    width: 400px;
    padding: 40px;
    background-color: white;
    border-radius: 12px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
}

h1 {
    margin-bottom: 24px;
    font-size: 24px;
    color: #333;
}

.register-element {
    display: flex;
    flex-direction: column;
    gap: 16px;
}

.input-field {
    width: 100%;
    padding: 12px;
    font-size: 16px;
    border: 1px solid #ddd;
    border-radius: 6px;
    transition: border-color 0.2s;
}

.input-field:focus {
    outline: none;
    border-color: #3498db;
}

.submit-btn {
    width: 100%;
    padding: 14px;
    font-size: 16px;
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.submit-btn:hover {
    background-color: #2980b9;
}

.google-btn {
    width: 100%;
    padding: 14px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 16px;
    background-color: #db4437;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.google-btn:hover {
    background-color: #c23321;
}

.google-icon {
    width: 24px;
    margin-right: 8px;
}
</style>
