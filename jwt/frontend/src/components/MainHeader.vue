<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-4 py-4">
        <h4> 사이트 맵 </h4>
        <ul class="list-unstyled">
          <li>
            <router-link to="/">메인 화면</router-link>
          </li> 
          <li>
            <router-link to="/login" v-if="!$store.state.account.accessToken">로그인</router-link>
            <a to="/login" @click="logout()" v-else>로그아웃</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import store from '@/scripts/store'
import router from '@/scripts/router';
//import store from '@/scripts/store';

export default{
  name:'MainHeader',
  setup(){
    const logout = () =>{
      store.commit("setAccount", ""); //스토어를 초기화 하고
      sessionStorage.clear();
      //sessionStorage.removeItem("accessToken");
      window.alert("로그아웃되었습니다.");
      router.push({path:"/"});      //main 페이지로 이동
    }
    return {logout};
  }
}
</script>

<style>

</style>