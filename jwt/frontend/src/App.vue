<template>
  <MainHeader />
  <RouterView />
  <MainFooter />
</template>

<script>
import MainHeader from './components/MainHeader.vue';
import MainFooter from './components/MainFooter.vue';
import axios from 'axios';
import store from './scripts/store';
import { useRoute } from 'vue-router';
import { watch } from 'vue';

export default {
  name: 'App',
  components: {
    MainHeader,
    MainFooter
  },
  setup(){
    const accessToken = sessionStorage.getItem("accessToken");
    if(accessToken){
      store.commit("setAccount", accessToken);
    }

    const check = () =>{
      axios.get("/") //check으로 수정
      .then(({data})=>{
        console.log("/check/id = "+ data);

        if(data){
          store.commit("setAccount", data);
        }
        else{
          window.alert("로그인이 필요합니다.")
          store.commit("setAccount","");

        }
      })
    }
    
    const route = useRoute();

    watch(route, () => { //uri가 바뀔때마다 check() 실행
      check();
    })
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
