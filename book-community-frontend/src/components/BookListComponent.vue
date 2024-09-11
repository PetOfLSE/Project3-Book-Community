<template>
    <div class="container">
        <h1>책 목록</h1>
        <ul class="book-list">
            <li v-for="book in books" :key="book.id" class="book-item">
                <!-- 책 제목 클릭 시 모달을 열도록 이벤트 바인딩 -->
                <span class="book-title" @click="openModal(book)">{{ book.title }}</span> 
                <span class="book-author">{{ book.author }}</span>
            </li>
        </ul>
        
        <!-- 뒤로 가기 버튼 -->
        <button class="back-btn" @click="goBack">뒤로 가기</button>

        <!-- 모달창 (v-if로 모달 상태 제어) -->
        <div v-if="isModalOpen" class="modal-overlay" @click="closeModal">
            <div class="modal-content" @click.stop>
                <h2>{{ selectedBook.title }}</h2>
                <p><strong>Author:</strong> {{ selectedBook.author }}</p>
                <p><strong>ISBN:</strong> {{ selectedBook.isbn }}</p>
                <p><strong>Content:</strong> {{ selectedBook.content }}</p>
                <button class="close-btn" @click="closeModal">닫기</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

const books = ref([]);
const page = ref(0);
const size = ref(10);
const sort = ref('id,desc');

// 모달 상태 관리
const isModalOpen = ref(false);
const selectedBook = ref({}); // 선택된 책 정보 저장

const fetchBooks = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/book/all", {
            params: {
                page: page.value,
                size: size.value,
                sort: sort.value
            }
        });
        console.log(response.data);
        books.value = response.data.body;
    } catch (error) {
        console.error(error);
    }
}

// 모달 열기
const openModal = (book) => {
    selectedBook.value = book; // 클릭된 책 정보 저장
    isModalOpen.value = true;  // 모달 열기
}

// 모달 닫기
const closeModal = () => {
    isModalOpen.value = false;
}

const goBack = () => {
    window.location.href = "http://localhost:3000/";
}

onMounted(() => {
    fetchBooks();
});
</script>

<style scoped>
.container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h1 {
    text-align: center;
    color: #333;
    font-size: 2rem;
    margin-bottom: 20px;
}

.book-list {
    list-style: none;
    padding: 0;
    margin: 0;
}

.book-item {
    display: flex;
    justify-content: space-between;
    padding: 15px 20px;
    margin-bottom: 10px;
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 6px;
    transition: box-shadow 0.3s;
}

.book-item:hover {
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.book-title {
    font-weight: bold;
    font-size: 1.2rem;
    color: #2c3e50;
    cursor: pointer;
}

.book-author {
    font-size: 1rem;
    color: #7f8c8d;
}

.back-btn {
    display: inline-block;
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 1.1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.back-btn:hover {
    background-color: #2980b9;
}

/* 모달 스타일 */
.modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.7);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
}

.modal-content {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    max-width: 500px;
    width: 100%;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.modal-content h2 {
    margin-top: 0;
}

.close-btn {
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #e74c3c;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 1.1rem;
    cursor: pointer;
}

.close-btn:hover {
    background-color: #c0392b;
}
</style>
