<template>
  <div>
    <header>
      <div class="header-content">
        <div class="title">
          <h1>허수</h1>
        </div>
        <nav class="header-nav">
          <ul>
            <li><a href="/book-add">책 추가</a></li>
            <li v-if="!isLoggedIn">
              <a href="/register">회원가입</a>
            </li>
            <li v-if="!isLoggedIn">
              <a href="/login">로그인</a>
            </li>
            <li v-if="isLoggedIn" class="user-info">
              <span class="username">{{ userName }}</span>
              <button @click="logout" class="logout-btn">로그아웃</button>
            </li>
          </ul>
        </nav>
      </div>
    </header>
    <main>
      <div class="main-content">
        <!-- 책 목록 컴포넌트 위치 변경 -->
        <div class="book-list">
          <h1>책 목록</h1>
          <ul>
            <li v-for="book in books" :key="book.id" class="book-item">
              <span class="book-title" @click="openModal(book)">{{ book.title }}</span>
              <span class="book-author">{{ book.author }}</span>
            </li>
          </ul>
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

        <!-- 사이드바 이동 -->
        <aside class="sidebar">
          <h3>책을 읽으면 좋은 점</h3>
          <ul>
            <li><strong>지식과 정보 습득:</strong> 책은 다양한 주제와 분야에 대한 깊이 있는 지식을 제공합니다. 새로운 정보를 배우고, 전문 분야에 대한 이해를 높이는 데 도움이 됩니다.</li>
            <li><strong>인지 능력 향상:</strong> 독서는 집중력과 사고력을 향상시키는 데 도움을 줍니다. 독서 중에는 정보를 분석하고, 내용을 이해하며, 비판적으로 사고하는 과정이 필요합니다.</li>
            <li><strong>어휘력과 문해력 향상:</strong> 다양한 책을 읽음으로써 어휘와 표현력이 풍부해집니다. 새로운 단어와 표현을 접하고 이를 실제로 사용할 수 있는 능력을 기를 수 있습니다.</li>
            <li><strong>스트레스 감소:</strong> 책을 읽는 것은 심리적 안정을 가져다줄 수 있습니다. 특히 소설이나 이야기책을 읽는 것은 현실의 스트레스에서 잠시 벗어나는 방법이 될 수 있습니다.</li>
            <li><strong>창의력과 상상력 증진:</strong> 책을 읽으면서 상상력을 자극받고 창의적인 생각을 하게 됩니다. 특히 문학 작품이나 소설은 다양한 상상의 세계를 탐험할 기회를 제공합니다.</li>
            <li><strong>문제 해결 능력 강화:</strong> 책에서 등장하는 다양한 문제와 해결책을 접하면서 문제 해결 능력을 개발할 수 있습니다. 특히 논픽션에서는 실제 사례를 통해 문제 해결 방법을 배울 수 있습니다.</li>
          </ul>
        </aside>
      </div>
    </main>
    <footer>
      <p>made in 이상훈</p>
    </footer>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useUserStore } from '../store/userStore';
import axios from 'axios';

const userStore = useUserStore();
const isLoggedIn = computed(() => userStore.isLoggedIn);
const userName = computed(() => userStore.userName);

const logout = () => {
  userStore.clearUser();
  window.location.href = '/';
};

const books = ref([]);
const isModalOpen = ref(false);
const selectedBook = ref({});

const fetchBooks = async () => {
  try {
    const response = await axios.get("http://localhost:8080/api/book/all", {
      params: {
        page: 0,
        size: 10,
        sort: 'id,desc'
      }
    });
    books.value = response.data.body;
  } catch (error) {
    console.error(error);
  }
}

const openModal = (book) => {
  selectedBook.value = book;
  isModalOpen.value = true;
}

const closeModal = () => {
  isModalOpen.value = false;
}

onMounted(() => {
  fetchBooks();
});
</script>

<style scoped>
/* 기본 스타일 */
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

/* 헤더 스타일링 */
header {
  display: flex;
  align-items: center;
  padding: 20px 40px;
  background: linear-gradient(135deg, #1e2a38, #2b3a5a);
  color: #ffffff;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
  position: sticky;
  top: 0;
  z-index: 1000;
}

.header-content {
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
}

.title {
  flex: 1;
}

.header-nav {
  flex: 2;
  display: flex;
  justify-content: flex-end; /* 오른쪽 정렬 */
  align-items: center; /* 수직 중앙 정렬 */
}

.header-nav ul {
  display: flex;
  gap: 20px; /* 메뉴 항목 사이의 간격 조정 */
  list-style: none;
  margin: 0;
  padding: 0;
}

.header-nav li {
  display: flex;
  align-items: center; /* 메뉴 항목의 세로 정렬 */
}

.header-nav a {
  color: #ecf0f1;
  text-decoration: none;
  font-size: 18px;
  font-weight: 500;
  position: relative;
  transition: color 0.3s ease;
}

.header-nav a::after {
  content: '';
  position: absolute;
  left: 0;
  bottom: -4px;
  width: 100%;
  height: 2px;
  background: #3498db;
  transform: scaleX(0);
  transition: transform 0.3s ease;
  transform-origin: bottom right;
}

.header-nav a:hover::after {
  transform: scaleX(1);
  transform-origin: bottom left;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 15px; /* 사용자 정보와 로그아웃 버튼 간격 조정 */
}

.username {
  font-size: 18px;
  font-weight: 600;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
}

.logout-btn {
  background-color: #e74c3c;
  border: none;
  color: white;
  padding: 10px 20px;
  border-radius: 8px;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.logout-btn:hover {
  background-color: #c0392b;
  transform: scale(1.05);
}

/* 메인 콘텐츠 스타일링 */
main {
  padding: 20px;
  background-color: #ecf0f1;
  min-height: calc(100vh - 80px - 50px); /* Adjust to header and footer height */
}

.main-content {
  display: flex;
  gap: 20px;
  flex-direction: column; /* 전체 콘텐츠를 세로로 배치 */
}

/* 책 리스트 컴포넌트 공간 */
.book-list {
  margin-top: 20px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s ease;
  animation: slide-in 0.5s ease-in-out;
}

@keyframes slide-in {
  from {
    transform: translateX(-50%);
    opacity: 0;
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

.book-item {
  display: flex;
  justify-content: space-between;
  align-items: center; /* 책 제목과 작가 이름을 수직 정렬 */
  margin-bottom: 10px;
  padding: 15px 10px; /* 약간의 패딩 추가 */
  border-radius: 5px; /* 책 항목에 약간의 둥근 모서리 추가 */
  transition: background-color 0.3s ease, transform 0.2s ease;
  cursor: pointer;
}

.book-item:not(:last-child) {
  margin-bottom: 15px; /* 책 항목 사이의 간격 조정 */
}

.book-item:hover {
  background-color: #f0f8ff; /* 호버 시 배경색 변경 */
  transform: scale(1.02); /* 호버 시 살짝 확대 효과 */
}

.book-title {
  font-weight: bold;
  color: #34495e;
  transition: color 0.3s ease;
}

.book-title:hover {
  color: #3498db; /* 제목이 살짝 더 눈에 띄도록 변경 */
}

.book-author {
  color: #7f8c8d;
  font-size: 14px;
  font-style: italic; /* 작가 이름을 이탤릭체로 변경 */
}

/* 사이드바 스타일링 */
.sidebar {
  background: linear-gradient(135deg, #ffffff, #f0f8ff);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
  border-radius: 8px;
  padding: 20px;
  border-left: 5px solid #3498db;
  margin-top: 20px;
  flex-shrink: 0;
}

.sidebar h3 {
  font-size: 24px;
  font-weight: 700;
  margin-bottom: 20px;
  color: #2b3a5a;
}

.sidebar ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.sidebar li {
  margin-bottom: 15px;
  line-height: 1.6;
  color: #7f8c8d;
}

.sidebar li strong {
  color: #2c3e50;
}

/* 모달 오버레이 스타일 */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  width: 80%;
  max-width: 500px;
}

.close-btn {
  background-color: #e74c3c;
  border: none;
  color: white;
  padding: 10px 20px;
  border-radius: 8px;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.close-btn:hover {
  background-color: #c0392b;
  transform: scale(1.08);
}

/* 푸터 스타일링 */
footer {
  text-align: center;
  padding: 15px;
  background-color: #1e2a38;
  color: #ffffff;
  font-size: 14px;
  font-weight: 400;
}

</style>
