<template>
    <v-container>
      <v-progress-circular indeterminate color="primary"></v-progress-circular>
      <p>Processing Google login...</p>
    </v-container>
  </template>
  
  <script>
  import authService from '../service/authService';
  
  export default {
    name: 'OAuthCallback',
    async created() {
      console.log('created')
      // URL에서 토큰 추출
      const hashParams = new URLSearchParams(window.location.hash.substring(1));
      const token = hashParams.get('access_token');
      console.log(token);
      
      if (token) {
        try {
            // 서버로 Google access_token을 보내서 JWT를 받아옴
            const response = await authService.loginWithGoogle(token);
            const jwtToken = response.data;
            localStorage.setItem('token', jwtToken); // 토큰을 로컬 스토리지에 저장
            this.$router.push('/'); // 로그인 후 메인 페이지로 리다이렉트

        } catch (error) {
            console.error('Google login failed', error);
            this.$router.push('/register'); // 실패 시 회원가입 페이지로 리다이렉트
        }
      } else {
        this.$router.push('/register');
      }
    }
  };
  </script>