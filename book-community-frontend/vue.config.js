const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  
  devServer: {
    port: 3000,  // 개발 서버의 포트를 3000으로 설정
  }
})
