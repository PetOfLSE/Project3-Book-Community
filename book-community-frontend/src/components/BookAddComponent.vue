<template>
    <div class="container">
        <h1>책 추가</h1>
        <form @submit.prevent="bookAdd" class="form-container">
            <p>
                <label for="title">
                    <input type="text" id="title" class="input-element" v-model="title" placeholder="책 제목">
                </label>
            </p>
            <p>
                <label for="author">
                    <input type="text" id="author" class="input-element" v-model="author" placeholder="책 작가">
                </label>
            </p>
            <p>
                <label for="isbn">
                    <input type="text" id="isbn" class="input-element" v-model="isbn" placeholder="책 ISBN">
                </label>
            </p>
            <p>
                <textarea v-model="content" class="textarea-element" placeholder="책 내용을 입력하세요"></textarea>
            </p>

            <button type="submit" class="submit-btn">책 추가</button>
        </form>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const title = ref('');
const author = ref('');
const isbn = ref('');
const content = ref('');
const router = useRouter();

const bookAdd = async () => {
    try {
        const response = await axios.post("http://localhost:8080/api/book/add", {
            title: title.value,
            author: author.value,
            isbn: isbn.value,
            content: content.value
        });

        alert('책 추가 완료');
        console.log(response.data);
        router.push('/');

    } catch (error) {
        console.error(error);
    }
}
</script>

<style scoped>
.container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f5f5f5;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
}

h1 {
    font-size: 2rem;
    margin-bottom: 20px;
    color: #333;
}

.form-container {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.input-element, .textarea-element {
    width: 100%;
    padding: 10px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    transition: border-color 0.3s ease;
}

.input-element:focus, .textarea-element:focus {
    border-color: #3498db;
    outline: none;
}

.textarea-element {
    height: 150px;
    resize: none;
}

.submit-btn {
    background-color: #3498db;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    font-size: 1.1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.submit-btn:hover {
    background-color: #2980b9;
}
</style>
