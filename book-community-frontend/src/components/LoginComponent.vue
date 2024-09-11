<template>
    <div class="body">
        <div class="login">
            <h1>로그인</h1>
            <form @submit.prevent="handleSubmit">
                <div class="login-element">
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

                    <button type="submit" class="submit-btn">로그인</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useUserStore } from '../store/userStore';

const name = ref('');
const email = ref('');
const password = ref('');
const router = useRouter();
const userStore = useUserStore();

const handleSubmit = async () => {
    try {
        const response = await axios.post('http://localhost:8080/api/user/login', {
            user_name: name.value,
            email: email.value,
            password: password.value
        });

        alert("로그인 성공!");
        console.log(response.data);

        userStore.setUser({
            name: name.value,
            email: email.value
        });

        router.push('/'); // 메인 페이지로 이동
    } catch (error) {
        alert("로그인 실패!");
        console.error(error);
    }
};
</script>

<style scoped>
/* Header Styles */
header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    background-color: #3498db;
    color: white;
}

.title h1 {
    font-size: 24px;
    margin: 0;
}

.header-element ul {
    display: flex;
    gap: 20px;
    list-style: none;
}

.header-element a {
    color: white;
    text-decoration: none;
    font-size: 16px;
}

.header-element a:hover {
    text-decoration: underline;
}

.header-element button {
    background-color: #e74c3c;
    border: none;
    color: white;
    padding: 8px 16px;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
}

.header-element button:hover {
    background-color: #c0392b;
}

/* Body Styles */
.body {
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #ecf0f1;
}

/* Login Form Styles */
.login {
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

.login-element {
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

</style>
