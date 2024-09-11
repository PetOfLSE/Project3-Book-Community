import axios from "axios";

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api', // 스프링 부트 api 기본 url 
    headers: {
        'Content-Type': 'application/json'
    } 
})

// 요청 인터셉터를 추가하여 토큰을 헤더에 삽입
apiClient.interceptors.request.use((config) => {
    // 로그인 및 회원가입 요청은 토큰을 추가하지 않음
    if (!config.url.includes('/auth/')) {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
    }
    return config;
  }, (error) => {
    return Promise.reject(error);
  });

export default apiClient;